package org.fgbokg.user.domain;

import lombok.Data;
import org.fgbokg.basic.domain.BaseDomain;
import org.fgbokg.org.domain.Shop;

import java.util.Date;

/**
 * 驳回原因类
 */
@Data
public class Reject extends BaseDomain {

    /** 驳回原因 */
    private String content;
    /** 创建时间 */
    private Date createDate = new Date();
    /** 员工id */
    private Long shop_id;
    /** 员工对象 */
    private Shop shop;
}
