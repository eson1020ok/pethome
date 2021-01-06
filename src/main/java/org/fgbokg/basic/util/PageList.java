package org.fgbokg.basic.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装返回前端的参数
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageList<T> {
    /** 总条数 */
    private Long totals = 0L;
    /** 每页展示的数据 */
    private List<T> data = new  ArrayList<>();
}
