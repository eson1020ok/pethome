package org.fgbokg.org.service;

import org.fgbokg.org.BaseTest;
import org.fgbokg.org.domain.Department;
import org.fgbokg.org.query.DepartmentQuery;
import org.fgbokg.basic.util.PageList;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 部门类的测试类
 */
public class IDepartmentServiceTest extends BaseTest {

    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void loadOne() {
        departmentService.findOne(1L);
    }

    @Test
    public void loadAll() {
        departmentService.findAll().forEach(System.out::println);
    }

    @Test
    public void add() {
        Department department = new Department();
        department.setName("弹唱部");
        departmentService.add(department);
    }

    @Test
    public void update() {
        Department department = new Department();
        department.setName("吹拉弹唱部");
        department.setId(46L);
        departmentService.update(department);
    }

    @Test
    public void delete() {
        departmentService.delete(46L);
    }

    @Test
    public void queryPage() {
        DepartmentQuery departmentQuery = new DepartmentQuery();
        PageList<Department> list = departmentService.queryPage(departmentQuery);
        list.getData().forEach(System.out::println);
    }
}