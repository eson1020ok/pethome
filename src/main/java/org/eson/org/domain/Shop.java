package org.eson.org.domain;

import org.eson.basic.domain.BaseDomain;
import lombok.Data;

import java.util.Date;

/**
 * 快捷键： ctrl+shift+回车，自动补全;
 */
@Data
public class Shop extends BaseDomain {
    //店铺名
    private String name;
    //店铺电话
    private String tel;
    //注册时间
    private Date registerTime = new Date();
    //状态: 0 待审核  1待激活   2正常 -1 驳回
    private Integer state = 0;
    //店铺地址
    private String address;
    //公司logo
    private String logo;
    //店长
    private Employee admin;
}
