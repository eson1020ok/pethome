package org.fgbokg.user.controller;

import org.fgbokg.basic.constant.LoginConstant;
import org.fgbokg.basic.util.AjaxResult;
import org.fgbokg.org.domain.Employee;
import org.fgbokg.org.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 登录的controller
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping("/home")
    public AjaxResult login(@RequestBody Employee employee, HttpSession session) {
        try {
            // 通过用户名和密码查询员工对象
            Employee loginEmployee = employeeService.findByUsernameAndPassword(employee.getUsername(), employee.getPassword());
            // 登录成功后，把登录用户放到session对象中，因为多个界面都需要知道登录用户是谁
            session.setAttribute(LoginConstant.EMPLOYEE_IN_SESSION, loginEmployee);
            return AjaxResult.me().setSuccess(true).setResultObject(loginEmployee);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg(e.getMessage());
        }
    }

}
