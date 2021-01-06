package org.fgbokg.user.domain;

import lombok.Data;
import org.fgbokg.basic.domain.BaseDomain;

import java.util.Date;

/**
 * 用户类
 */
@Data
public class User extends BaseDomain {

    /** 用户名 */
    private String username;
    /** 邮箱 */
    private String email;
    /** 电话 */
    private String phone;
    /** 盐值 */
    private String salt;
    /** 密码 */
    private String password;
    /** 状态 */
    private Integer state;
    /** 年龄 */
    private Integer age;
    /** 注册时间 */
    private Date createtime = new Date();
    /** 登陆信息 */
    private Long logininfo_id;
    /**  */
    private String headImg;
}
