package org.eson.org.service.impl;

import org.eson.basic.service.impl.BaseServiceImpl;
import org.eson.basic.util.PageList;
import org.eson.org.domain.Employee;
import org.eson.org.mapper.EmployeeMapper;
import org.eson.org.query.EmployeeQuery;
import org.eson.org.service.IEmployeeService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    @Transactional
    public void patchDel(List<Long> ids) {
        employeeMapper.patchDel(ids);
    }

}
