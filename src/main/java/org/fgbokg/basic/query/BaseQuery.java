package org.fgbokg.basic.query;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共类，封装公共查询条件
 *                  分页查询
 */
@Data
public class BaseQuery {

    /** 当前页 */
    private Integer currentPage = 1;
    /** 每页显示的条数 */
    private Integer pageSize = 5;
    /** 高级查询的关键字 */
    private String keyword = null;

    /** 数据字典id */
    private Long types_id = 0L;

    /**
     * 计算当前页的起始下标begin      limit a,b
     * @return
     */
    public Integer getBegin() {
        return (this.currentPage - 1)*this.pageSize;
    }
}
