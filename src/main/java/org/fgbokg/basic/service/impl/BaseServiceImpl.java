package org.fgbokg.basic.service.impl;

import org.fgbokg.basic.mapper.BaseMapper;
import org.fgbokg.basic.query.BaseQuery;
import org.fgbokg.basic.service.IBaseService;
import org.fgbokg.basic.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 公共类
 *      基础的Service实现类：抽取公共的或基础的业务实现方法
 * @param <T>
 */
// @Service // 千万不能写，因为T不是真正的类型，交给Spring管理的话要失败
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class BaseServiceImpl<T> implements IBaseService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public T findOne(Long id) {
        return baseMapper.loadOne(id);
    }

    @Override
    public List<T> findAll() {
        return baseMapper.loadAll();
    }

    @Transactional
    @Override
    public void add(T t) {
        baseMapper.save(t);
    }

    @Transactional
    @Override
    public void update(T t) {
        baseMapper.update(t);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        baseMapper.remove(id);
    }

    @Override
    public PageList<T> queryPage(BaseQuery baseQuery) {
        // 查询总条数
        Long totals = baseMapper.queryCount(baseQuery);
        // 判断总条数是否为空
        if (totals == null || totals == 0) {
            return new PageList<T>();
        }
        // 查询分页数据
        List<T> data = baseMapper.queryList(baseQuery);
        return new PageList<T>(totals, data);
    }
}
