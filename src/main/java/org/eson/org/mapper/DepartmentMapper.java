package org.eson.org.mapper;

import org.eson.basic.mapper.BaseMapper;
import org.eson.org.domain.Department;
import org.eson.org.query.DepartmentQuery;

import java.util.List;

public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     *
     * @return
     */
    List<Department> getDeptTreeForAdd();

    /**
     *
     * @param id
     * @return
     */
    List<Department> getDeptTreeForUpdate(Long id);

    /**
     * 批量删除
     * @param ids
     */
    void patchDelelete(List<Long> ids);
}
