package org.fgbokg.basic.mapper;

import org.fgbokg.basic.query.BaseQuery;
import java.util.List;

/**
 * 公共类
 *      基础映射器：抽取操作数据库的基础或公共方法
 * @param <T>
 */
public interface BaseMapper<T> {

    /**
     * 根据id加载对象
     * @param id
     * @return
     */
    T loadOne(Long id);

    /**
     * 加载所有对象
     * @return
     */
    List<T> loadAll();

    /**
     * 保存一个对象
     * @param t
     */
    void save(T t);

    /**
     * 修改对象数据
     * @param t
     */
    void update(T t);

    /**
     * 移除一个对象
     * @param id
     */
    void remove(Long id);

    /**
     * 分页查询-查询总条数
     * @param baseQuery
     * @return
     */
    Long queryCount(BaseQuery baseQuery);

    /**
     * 分页查询-查询每页的数据
     * @param baseQuery
     * @return
     */
    List<T> queryList(BaseQuery baseQuery);
}
