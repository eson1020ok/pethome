package org.fgbokg.basic.service.impl;

import org.fgbokg.basic.constant.VerificationConstant;
import org.fgbokg.basic.exception.CustomException;
import org.fgbokg.basic.service.IVerificationService;
import org.fgbokg.basic.util.SendMsgUtil;
import org.fgbokg.basic.util.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 验证码实现类
 */
@Service
public class VerificationServiceImpl implements IVerificationService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 产生的验证码有效期是5分钟，如果在1分钟以内点击了多次按钮，就提示用户，一分钟以内不能发送多次验证码，如果超过了1分钟，但在
     * 5分钟以内，依然使用第一次产生的验证码，如果超过了5分钟就发送全新的验证码
     * @param phone
     * @throws CustomException
     */
    @Override
    public void sendMobileCode(String phone) throws CustomException {
        // 产生随机验证码
        String code = StrUtils.getComplexRandomString(4);
        // 通过key值在中查找验证码是否存在
        String codeValue = (String) redisTemplate.opsForValue().get(phone + ":" + VerificationConstant.USER_REG);
        // 如果codeValue存在，说明key值还没过期，isEmpty判断是否为空的方法
        if (!StringUtils.isEmpty(codeValue)) {
            String beginTimer = codeValue.split(":")[1];
            //　结束毫秒时间－开始毫秒时间 < 1分钟
            if (System.currentTimeMillis() - Long.valueOf(beginTimer) < 60*1000) {
                throw new CustomException("一分钟内不能发送多次验证码，请稍后重试！");
            }
            // 大于1分钟，小于5分钟，验证码不变
            code = codeValue.split(":")[0];
        }
        // 根据用户手机号产生的不同的验证码放到redis中
        redisTemplate.opsForValue().set(phone+ ":" + VerificationConstant.USER_REG, code + ":" + System.currentTimeMillis(), 5, TimeUnit.MINUTES);
        System.out.println("你的验证码：" + code);
        String content = "尊敬的用户，你的验证码为：" + code + "请在五分钟内完成验证操作！";
        // 发送短信
        SendMsgUtil.sendMsg(phone, content);
    }
}
