package org.eson.user.mapper;

import org.eson.basic.mapper.BaseMapper;
import org.eson.user.domain.User;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过手机号码查询用户对象
     * @param phone
     * @return
     */
    User loadByPhone(String phone);
}
