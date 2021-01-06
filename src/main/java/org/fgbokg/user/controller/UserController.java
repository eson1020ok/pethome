package org.fgbokg.user.controller;

import org.fgbokg.basic.util.AjaxResult;
import org.fgbokg.user.domain.User;
import org.fgbokg.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户类的controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 判断手机号是否被注册
     * @param user
     * @return
     */
    @PostMapping("/checkPhone")
    public AjaxResult checkPhone(@RequestBody User user) {
        try {
            userService.checkPhone(user.getPhone());
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg(e.getMessage());
        }
    }

    /**
     * 判断邮箱是否被注册
     * @param user
     * @return
     */
    @PostMapping("/checkEmail")
    public AjaxResult checkEmail(@RequestBody User user) {
        try {
            userService.checkEmail(user.getEmail());
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg(e.getMessage());
        }
    }
}
