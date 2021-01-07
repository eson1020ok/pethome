package org.eson.systemdictionary.domain;

import lombok.Data;
import org.eson.basic.domain.BaseDomain;

/**
 * 数据字典明细类
 */
@Data
public class Systemdictionarydetail extends BaseDomain {

    /** 名称 */
    private String name;
    /** 数据字典类型id */
    private Long types_id;
    /** 数据字典 */
    private Systemdictionarytype types;
}
