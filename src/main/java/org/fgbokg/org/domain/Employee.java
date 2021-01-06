package org.fgbokg.org.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fgbokg.basic.domain.BaseDomain;

/**
 * 员工类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseDomain {

    /** 员工性名 */
    private String username;
    /** 邮箱 */
    private String email;
    /** 电话 */
    private String phone;
    /** 随机值 */
    private String salt;
    /** 密码 */
    private String password;
    /** 年龄 */
    private Integer age;
    /** 启用状态 */
    private Integer state = 1;
    /** 部门id */
    private Long department_id;
    /** 部门 */
    private Department department;
    /** 登录信息id */
    private Long logininfo_id;
    /** 店铺id */
    private Long shop_id;
    /** 店铺 */
    private Shop shop;

}
