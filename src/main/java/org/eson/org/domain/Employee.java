package org.eson.org.domain;

import org.eson.basic.domain.BaseDomain;
import org.eson.user.domain.LoginInfo;
import lombok.Data;

@Data
public class Employee extends BaseDomain{
    //用户名
    private String username;
    //邮箱
    private String email;
    //手机
    private String phone;
    //盐值(先不管)
    private String salt;
    //密码
    private String password;
    //年龄
    private Integer age;
    //状态   1 正常    -1 禁用
    private Integer state = 1;
    private Long department_id;
    private Long logininfo_id;
    private Long shop_id;
    //部门
    private Department department;
    //店铺
    private Shop shop;
    //登录信息对象
    private LoginInfo loginInfo;
}
