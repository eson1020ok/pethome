package org.fgbokg.org.mapper;

import org.apache.ibatis.annotations.Param;
import org.fgbokg.basic.mapper.BaseMapper;
import org.fgbokg.org.domain.Department;
import org.fgbokg.org.query.DepartmentQuery;

import java.util.List;

/**
 * 部门类的mapper接口
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 批量删除
     * @param ids
     */
    void patchDelete(List<Long> ids);

    /**
     * 加载部门树，查询所有的一级部门和对应的二级部门
     * @return
     */
    List<Department> loadDeptTree(@Param("id") Long id);
}
