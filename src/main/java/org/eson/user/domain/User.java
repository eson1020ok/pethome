package org.eson.user.domain;

import org.eson.basic.domain.BaseDomain;
import lombok.Data;

import java.util.Date;

@Data
public class User extends BaseDomain {
    //用户名
    private String username;
    //邮箱
    private String email;
    //电话
    private String phone;
    //盐值
    private String salt;
    //密码
    private String password;
    //状态  0待激活(已注册)  1已激活  -1 冻结
    private Integer state = 1;
    //年龄
    private Integer age;
    private Date createtime = new Date();
    //头像
    private String headImg;
    //登录信息对象
    private LoginInfo loginInfo;

}
