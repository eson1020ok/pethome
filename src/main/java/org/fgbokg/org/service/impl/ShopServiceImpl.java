package org.fgbokg.org.service.impl;

import org.fgbokg.basic.exception.CustomException;
import org.fgbokg.basic.service.impl.BaseServiceImpl;
import org.fgbokg.basic.util.PageList;
import org.fgbokg.org.domain.Employee;
import org.fgbokg.org.domain.Shop;
import org.fgbokg.org.mapper.EmployeeMapper;
import org.fgbokg.org.mapper.ShopMapper;
import org.fgbokg.org.query.ShopQuery;
import org.fgbokg.org.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * 店铺类的service接口的实现类
 */
@Service
public class ShopServiceImpl extends BaseServiceImpl<Shop> implements IShopService {

    /** 注入店铺对象 */
    @Autowired
    private ShopMapper shopMapper;

    /** 注入员工对象 */
    @Autowired
    private EmployeeMapper employeeMapper;

    /** 注入邮件对象 */
    @Autowired
    private JavaMailSender mailSender;

    @Transactional
    @Override
    public void patchDelete(List<Long> ids) {
        shopMapper.patchDelete(ids);
    }

    @Override
    public void settlement(Shop shop) throws CustomException {
        // 校验前端传递的数据
        checkData(shop);
        // 保存店铺，但admin_id为空
        shopMapper.save(shop);
        // 获取员工对象
        Employee admin = shop.getAdmin();
        // 员工中shop_id是为空的，所以需要id把Shop对象设置进去，就能获取到shop_id
        admin.setShop(shop);
        // 保存员工
        employeeMapper.save(admin);
        // 保存员工之后，employee对象就有了，就能获取到admin_id，更新
        shopMapper.update(shop);
    }

    /**
     * 通过id查询数据，并修改店铺状态，待激活
     * @param id
     */
    @Override
    public void setStateAdopt(Long id) throws MessagingException {
        // 查询店铺对象
        Shop shop = shopMapper.loadOne(id);
        // 改变状态
        shop.setState(1);
        // 调用修改方法
        shopMapper.update(shop);

        // 根据店铺对象获取员工id
        Long employeeId = shop.getAdmin_id();
        // 获取获取员工的邮箱
        String email = employeeMapper.loadOne(employeeId).getEmail();

        // 创建复杂邮箱
        MimeMessage message = mailSender.createMimeMessage();
        // 创建一个工具类，该工具类向空邮件中写内容
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");
        // 设置发件人
        mimeMessageHelper.setFrom("522610972@qq.com");
        // 设置收件人
        mimeMessageHelper.setTo(email);
        // 设置标题
        mimeMessageHelper.setSubject("宠物之家注册激活");
        // 邮件内容
        String url = "http://localhost:8080/shop/setStateNormal/" + id;
        mimeMessageHelper.setText("<a href='"+ url +"'>请点击如下链接完成激活认证</a>", true);
        mailSender.send(message);
    }

    /**
     * 通过id查询数据，并修改店铺状态为-1，驳回
     * @param id
     */
    @Override
    public void setStateReject(Long id) {
        // 查询店铺对象
        Shop shop = shopMapper.loadOne(id);
        // 改变状态
        shop.setState(-1);
        // 调用修改方法
        shopMapper.update(shop);
    }

    @Override
    public void setStateNormal(Long id) {
        // 查询店铺对象
        Shop shop = shopMapper.loadOne(id);
        // 改变状态
        shop.setState(2);
        // 调用修改方法
        shopMapper.update(shop);
    }

    /**
     * 根据员工id查询出店铺对象
     * @param admin_id
     * @return
     */
    @Override
    public Shop getOneShopList(Long admin_id) {
        return shopMapper.loadByAdminId(admin_id);
    }

    private void checkData(Shop shop) throws CustomException {
        if (StringUtils.isEmpty(shop.getName())) {
            throw new CustomException("店铺名不能为空");
        }
        if(StringUtils.isEmpty(shop.getTel())){
            throw new CustomException("座机不能为空!");
        }
        if(StringUtils.isEmpty(shop.getAddress())){
            throw new CustomException("店铺地址不能为空!");
        }
        if(StringUtils.isEmpty(shop.getLogo())){
            throw new CustomException("附件不能为空!");
        }
        if(StringUtils.isEmpty(shop.getAdmin().getUsername())){
            throw new CustomException("账号不能为空!");
        }
        if(StringUtils.isEmpty(shop.getAdmin().getPhone())){
            throw new CustomException("手机号不能为空!");
        }
        if(StringUtils.isEmpty(shop.getAdmin().getEmail())){
            throw new CustomException("邮箱不能为空!");
        }
        if(StringUtils.isEmpty(shop.getAdmin().getPassword())){
            throw new CustomException("密码不能为空!");
        }
        // 验证店铺名是否存在
        Shop s = shopMapper.loadByName(shop.getName());
        if (s != null) {
            throw new CustomException("店铺名已存在，请重新输入！");
        }
        // 验证管理人员账号是否存在
        Employee employee = employeeMapper.loadByUsername(shop.getAdmin().getUsername());
        if (employee != null) {
            throw new CustomException("用户名已存在，请重新输入！");
        }

    }
}
