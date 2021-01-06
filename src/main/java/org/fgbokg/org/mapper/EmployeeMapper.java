package org.fgbokg.org.mapper;

import org.fgbokg.basic.mapper.BaseMapper;
import org.fgbokg.org.domain.Employee;
import org.fgbokg.org.query.EmployeeQuery;

import java.util.List;

/**
 * 员工类的mapper接口
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 批量删除
     * @param ids
     */
    void patchDelete(List<Long> ids);

    /**
     * 根据员工名查询员工对象
     * @param username
     * @return
     */
    Employee loadByUsername(String username);

}
