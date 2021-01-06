package org.fgbokg.org.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fgbokg.basic.domain.BaseDomain;

import java.util.Date;
import java.util.List;

/**
 * 店铺类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop extends BaseDomain {

    /** 店铺名 */
    private String name;
    /** 电话号码 */
    private String tel;
    /** 注册时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date registerTime = new Date();
    /** 状态: 0 待审核  1待激活  2正常 -1 驳回 */
    private Integer state = 0;
    /** 地址 */
    private String address;
    /** logo标志 */
    private String logo;
    /** 管理员id */
    private Long admin_id;
    /** 管理员，店长 */
    private Employee admin;
}
