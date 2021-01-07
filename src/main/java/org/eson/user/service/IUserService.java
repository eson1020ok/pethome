package org.eson.user.service;

import org.eson.basic.exception.CustomException;
import org.eson.basic.service.IBaseService;
import org.eson.user.domain.User;
import org.eson.user.dto.UserDto;

public interface IUserService extends IBaseService<User> {
    /**
     * 校验手机号码是否存在
     * @param phone
     * @return
     */
    void checkPhone(String phone) throws CustomException;

    /**
     * 注册用户
     * @param userDto
     */
    void register(UserDto userDto) throws CustomException;
}
