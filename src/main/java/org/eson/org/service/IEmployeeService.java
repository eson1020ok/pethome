package org.eson.org.service;

import org.eson.basic.service.IBaseService;
import org.eson.org.domain.Employee;

import java.util.List;

public interface IEmployeeService extends IBaseService<Employee> {

    void patchDel(List<Long> ids);
}
