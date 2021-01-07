package org.eson.org.mapper;

import org.eson.basic.mapper.BaseMapper;
import org.eson.org.domain.Employee;
import org.eson.org.query.EmployeeQuery;

import java.util.List;

public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 批量删除
     * @param ids
     */
    void patchDel(List<Long> ids);

    /**
     * 根据用户名查询员工对象
     * @param username
     * @return
     */
    Employee loadByUsername(String username);
}
