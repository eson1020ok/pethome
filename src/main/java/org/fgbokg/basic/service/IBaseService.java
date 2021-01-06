package org.fgbokg.basic.service;

import org.fgbokg.basic.query.BaseQuery;
import org.fgbokg.basic.util.PageList;

import java.util.List;

/**
 * 公共类
 *      基础的Service接口：抽取公共的或基础的业务接口方法
 */
public interface IBaseService<T> {

    /**
     * 根据id查询对象
     * @param id
     * @return
     */
    T findOne(Long id);

    /**
     * 查询所有对象
     * @return
     */
    List<T> findAll();

    /**
     * 添加对象
     * @param t
     */
    void add(T t);

    /**
     * 修改对象数据
     * @param t
     */
    void update(T t);

    /**
     * 删除对象
     * @param id
     */
    void delete(Long id);

    /**
     * 查询总条数和分页数据
     * @param baseQuery
     * @return
     */
    PageList<T> queryPage(BaseQuery baseQuery);
}
