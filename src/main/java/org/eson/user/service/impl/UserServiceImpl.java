package org.eson.user.service.impl;

import org.eson.basic.constant.PetHomeConstant;
import org.eson.basic.constant.VerificationConstant;
import org.eson.basic.exception.CustomException;
import org.eson.basic.service.impl.BaseServiceImpl;
import org.eson.basic.util.MD5Utils;
import org.eson.basic.util.StrUtils;
import org.eson.user.domain.LoginInfo;
import org.eson.user.domain.User;
import org.eson.user.dto.UserDto;
import org.eson.user.mapper.LoginInfoMapper;
import org.eson.user.mapper.UserMapper;
import org.eson.user.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginInfoMapper loginInfoMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void checkPhone(String phone) throws CustomException {
        User user = userMapper.loadByPhone(phone);
        if(user!=null){
            throw new CustomException("该手机号码已被注册!!");
        }
    }

    @Override
    public void register(UserDto userDto) throws CustomException {
        //校验前端传递数据
        checkData(userDto);
        //创建User对象
        User user = userDto2User(userDto);
        //创建LoginInfo对象
        LoginInfo loginInfo = createLoginInfo(user);
        //保存t_loginInfo表
        loginInfoMapper.save(loginInfo);
        //设置loginInfo对象
        user.setLoginInfo(loginInfo);
        //保存t_user表
        userMapper.save(user);

    }

    /**
     * 创建LoginInfo对象
     * @param user
     * @return
     */
    private LoginInfo createLoginInfo(User user) {
        LoginInfo loginInfo = new LoginInfo();
        BeanUtils.copyProperties(user,loginInfo );
        loginInfo.setType(PetHomeConstant.HOME);

        return loginInfo;
    }

    /**
     * 创建User对象
     * @param userDto
     * @return
     */
    private User userDto2User(UserDto userDto) {
        User user = new User();
        user.setPhone(userDto.getPhone());
        //盐值：动态生成的
        user.setSalt(StrUtils.getComplexRandomString(10));
        user.setPassword(MD5Utils.encrypByMd5(userDto.getPassword()+user.getSalt()));
        return user;
    }


    /**
     * 校验前端传递的数据
     * @param userDto
     * @throws CustomException
     */
    private void checkData(UserDto userDto) throws CustomException {
        if(StringUtils.isEmpty(userDto.getPhone())){
            throw new CustomException("手机号不能为空!!");
        }
        //校验手机
        checkPhone(userDto.getPhone());
        if(StringUtils.isEmpty(userDto.getCode())){
            throw new CustomException("验证码不能为空!!");
        }
        //在redis中查询验证码   78iu:34432875628375634
        String codeValue = (String) redisTemplate.opsForValue().get(userDto.getPhone()+":"+ VerificationConstant.USER_REG);
        if(StringUtils.isEmpty(codeValue)){
            throw new CustomException("验证码已经失效!请重新发送验证码!!");
        }
        if(!userDto.getCode().equals(codeValue.split(":")[0])){
            throw new CustomException("验证码错误!!");
        }
        if(StringUtils.isEmpty(userDto.getPassword())){
            throw new CustomException("密码必填!!");
        }
        if(StringUtils.isEmpty(userDto.getConfigPassword())){
            throw new CustomException("确认密码必填!!");
        }
        if(!userDto.getPassword().equals(userDto.getConfigPassword())){
            throw new CustomException("2次密码不一致!!");
        }
        LoginInfo loginInfo = loginInfoMapper.loadByPhoneAndState(userDto.getPhone(), PetHomeConstant.HOME);
        if (loginInfo != null) {
            throw new CustomException("手机号已被注册!!");
        }

    }
}
