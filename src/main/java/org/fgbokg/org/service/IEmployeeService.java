package org.fgbokg.org.service;

import org.fgbokg.basic.exception.CustomException;
import org.fgbokg.basic.service.IBaseService;
import org.fgbokg.basic.util.PageList;
import org.fgbokg.org.domain.Employee;
import org.fgbokg.org.query.EmployeeQuery;

import java.util.List;

/**
 * 员工类的service接口
 */
public interface IEmployeeService extends IBaseService<Employee> {

    /**
     * 批量删除
     * @param ids
     */
    void patchDelete(List<Long> ids);

    /**
     * 通过用户名和密码查询员工对象
     * @param username
     * @param password
     * @return
     */
    Employee findByUsernameAndPassword(String username, String password) throws CustomException;
}
