package org.fgbokg.user.mapper;

import org.fgbokg.basic.mapper.BaseMapper;
import org.fgbokg.user.domain.User;

/**
 * 用户类的mapper接口
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户输入的电话号查询用户
     * @param phone
     * @return
     */
    User loadByPhone(String phone);

    /**
     * 根据用户输入的邮箱查询用户
     * @param email
     * @return
     */
    User loadByEmail(String email);
}
