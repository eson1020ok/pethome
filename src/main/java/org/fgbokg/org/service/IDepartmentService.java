package org.fgbokg.org.service;

import org.apache.ibatis.annotations.Param;
import org.fgbokg.basic.service.IBaseService;
import org.fgbokg.org.domain.Department;
import org.fgbokg.org.query.DepartmentQuery;
import org.fgbokg.basic.util.PageList;

import java.util.List;

/**
 * 部门类的service接口
 */
public interface IDepartmentService extends IBaseService<Department> {

    /**
     * 批量删除
     * @param ids
     */
    void patchDelete(List<Long> ids);

    /**
     * 加载部门树，查询所有的一级部门和对应的二级部门
     * @return
     */
    List<Department> queryDeptTree(Long id);
}
