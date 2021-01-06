package org.fgbokg.org.service.impl;

import org.fgbokg.basic.exception.CustomException;
import org.fgbokg.basic.service.impl.BaseServiceImpl;
import org.fgbokg.basic.util.PageList;
import org.fgbokg.org.domain.Employee;
import org.fgbokg.org.mapper.EmployeeMapper;
import org.fgbokg.org.query.EmployeeQuery;
import org.fgbokg.org.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 员工类的service接口的实现类
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Transactional
    @Override
    public void patchDelete(List<Long> ids) {
        employeeMapper.patchDelete(ids);
    }

    /**
     * 员工登陆
     * @param username
     * @param password
     * @return
     */
    @Override
    public Employee findByUsernameAndPassword(String username, String password) throws CustomException {
        // 通过用户名查询员工对象
        Employee employee = employeeMapper.loadByUsername(username);
        // 判断员工对象是否为空
        if (employee == null) {
            // 抛出异常
            throw new CustomException("用户名为空，请重新填写！");
        }
        // 用户名不为空，判断密码是否正确，如果不正确，抛出异常
        if (!employee.getPassword().equals(password)) {
            throw new CustomException("密码错误，请重新填写！");
        }
        return employee;
    }
}
