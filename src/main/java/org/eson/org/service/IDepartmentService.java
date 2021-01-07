package org.eson.org.service;

import org.eson.basic.service.IBaseService;
import org.eson.org.domain.Department;

import java.util.List;

public interface IDepartmentService extends IBaseService<Department> {

    /**
     * 批量删除
     * @param ids
     */
    void patchDelete(List<Long> ids);

    /**
     * 获取部门树
     * @return
     */
    List<Department> getDeptTree(Long id);
}
