package org.fgbokg.user.service.impl;

import org.fgbokg.basic.exception.CustomException;
import org.fgbokg.basic.service.impl.BaseServiceImpl;
import org.fgbokg.user.domain.User;
import org.fgbokg.user.mapper.UserMapper;
import org.fgbokg.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void checkPhone(String phone) throws CustomException {
        // 根据用户输入的电话号判断用户是否存在
        User user = userMapper.loadByPhone(phone);
        // 判断电话号码是否存在
        if (user != null) {
            throw new CustomException("该电话号码已经被注册，请重新输入！");
        }
    }

    @Override
    public void checkEmail(String email) throws CustomException {
        // 根据用户输入的邮箱判断用户是否存在
        User user = userMapper.loadByEmail(email);
        // 判断邮箱是否存在
        if (user != null) {
            throw new CustomException("该邮箱已经被注册，请重新输入！");
        }
    }
}
