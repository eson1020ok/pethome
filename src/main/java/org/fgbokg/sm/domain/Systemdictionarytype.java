package org.fgbokg.sm.domain;

import lombok.Data;
import org.fgbokg.basic.domain.BaseDomain;

/**
 * 数据字典类
 */
@Data
public class Systemdictionarytype extends BaseDomain {

    /** 数据字典名称 */
    private String name;
    /** 编号 */
    private String sn;
}
