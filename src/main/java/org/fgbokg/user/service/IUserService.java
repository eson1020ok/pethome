package org.fgbokg.user.service;

import org.fgbokg.basic.exception.CustomException;
import org.fgbokg.basic.service.IBaseService;
import org.fgbokg.user.domain.User;

/**
 * 用户类的service
 */
public interface IUserService extends IBaseService<User> {

    /**
     * 根据用户输入的电话号判断用户是否存在
     * @param phone
     */
    void checkPhone(String phone) throws CustomException;

    /**
     * 根据用户输入的邮箱判断用户是否存在
     * @param email
     */
    void checkEmail(String email) throws CustomException;
}
