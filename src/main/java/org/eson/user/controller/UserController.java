package org.eson.user.controller;

import org.eson.basic.util.AjaxResult;
import org.eson.user.domain.User;
import org.eson.user.dto.UserDto;
import org.eson.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 快速try catch  快捷键是      ctrl+alt+t
     * @param user
     * @return
     */
    @PostMapping("/checkPhone")
    public AjaxResult checkPhone(@RequestBody User user){
        try {
            userService.checkPhone(user.getPhone());
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, e.getMessage());
        }
    }


    /**
     * 注册用户
     * @return
     */
    @PostMapping("/register")
    public AjaxResult register(@RequestBody UserDto userDto){
        try {
            //注册用户
            userService.register(userDto);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, e.getMessage());
        }
    }


}
