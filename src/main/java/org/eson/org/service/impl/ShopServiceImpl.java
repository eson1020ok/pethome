package org.eson.org.service.impl;

import org.eson.basic.constant.PetHomeConstant;
import org.eson.basic.exception.CustomException;
import org.eson.basic.service.impl.BaseServiceImpl;
import org.eson.basic.util.MD5Utils;
import org.eson.basic.util.StrUtils;
import org.eson.org.domain.Employee;
import org.eson.org.domain.Shop;
import org.eson.org.mapper.EmployeeMapper;
import org.eson.org.mapper.ShopMapper;
import org.eson.org.service.IShopService;
import org.eson.user.domain.LoginInfo;
import org.eson.user.mapper.LoginInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class ShopServiceImpl extends BaseServiceImpl<Shop> implements IShopService {
    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private LoginInfoMapper logininfoMapper;

    @Override
    @Transactional
    public void settlement(Shop shop) throws CustomException {
        //校验前端传递的数据
        checkData(shop);
        //保存店铺
        shopMapper.save(shop);// 当保存了店铺之后， admin_id为空

        //获取员工对象
        Employee admin = shop.getAdmin();//拿到员工对象
        admin.setSalt(StrUtils.getComplexRandomString(10));
        admin.setPassword(MD5Utils.encrypByMd5(admin.getPassword() + admin.getSalt()));
        //创建LoginInfo对象
        LoginInfo loginInfo = createLoginInfo(admin);
        //保存loginInfo对象
        logininfoMapper.save(loginInfo);
        //设置登录信息
        admin.setLoginInfo(loginInfo);
        //给员工设置店铺
        admin.setShop(shop);//员工中shop是为空的，所以设置进去
        employeeMapper.save(admin);//保存员工了， 店铺就有值了
        //修改店铺
        shopMapper.update(shop);//当保存了员工之后，shop中的admin就有值了，所以要修改店铺，补全admin_id的值
    }

    private LoginInfo createLoginInfo(Employee admin) {
        LoginInfo loginInfo = new LoginInfo();
        BeanUtils.copyProperties(admin, loginInfo);
        loginInfo.setType(PetHomeConstant.ADMIN);
        return loginInfo;
    }

    private void checkData(Shop shop) throws CustomException {
        if(StringUtils.isEmpty(shop.getName())){
            throw new CustomException("店铺名不能为空!");
        }
        if(StringUtils.isEmpty(shop.getTel())){
            throw new CustomException("座机不能为空!");
        }
        if(StringUtils.isEmpty(shop.getAddress())){
            throw new CustomException("店铺地址不能为空!");
        }
        if(StringUtils.isEmpty(shop.getAdmin().getUsername())){
            throw new CustomException("店铺管理人员名不能为空!");
        }
        if(StringUtils.isEmpty(shop.getAdmin().getPhone())){
            throw new CustomException("手机不能为空!");
        }
        if(StringUtils.isEmpty(shop.getAdmin().getEmail())){
            throw new CustomException("邮箱不能为空!");
        }
        if(StringUtils.isEmpty(shop.getAdmin().getPassword())){
            throw new CustomException("密码不能为空!");
        }
        //根据店铺名查询店铺对象
        Shop s = shopMapper.loadByName(shop.getName());
        if(s!=null){
            throw new CustomException("店铺名已存在!!");
        }
        Employee employee = employeeMapper.loadByUsername(shop.getAdmin().getUsername());
        if (employee != null) {
            throw new CustomException("用户名已存在!!");
        }

        //通过手机号和状态在t_loginInfo中定位到唯一数据
        LoginInfo logininfo = logininfoMapper.loadByPhoneAndState(shop.getAdmin().getPhone(), PetHomeConstant.ADMIN);
        if (logininfo != null) {
            throw new CustomException("手机号码已被注册!!");
        }
    }


}
