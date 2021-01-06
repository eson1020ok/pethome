package org.fgbokg.org.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fgbokg.basic.util.AjaxResult;
import org.fgbokg.basic.util.PageList;
import org.fgbokg.org.domain.Employee;
import org.fgbokg.org.query.EmployeeQuery;
import org.fgbokg.org.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工类的controller
 *              前后端分离的接口
 */
@RestController
@RequestMapping("/employee")
@Api(tags = "员工接口",description = "员工接口详细描述")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    /**
     * 根据id查询单个对象：get localhost:8080/employee/1
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "查询一个员工对象",notes = "根据id查询")
    public Employee findOne(@PathVariable("id") Long id) {
        return employeeService.findOne(id);
    }

    /**
     * 查询所有对象：get localhost:8080/employee
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询所有的员工对象",notes = "")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    /**
     * 添加和修改：Put localhost:8080/employee
     * @param employee
     * @return
     */
    @PutMapping
    @ApiOperation(value = "员工添加或修改",notes = "如果有id就是修改否则就是添加")
    public AjaxResult addOrUpdate(@RequestBody Employee employee) {
        try {
            if (employee.getId() == null) {
                employeeService.add(employee);
            } else {
                employeeService.update(employee);
            }
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("操作失败！");
        }
    }

    /**
     * 删除：Delete localhost:8080/employee
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除一个员工对象",notes = "")
    public AjaxResult delete(@PathVariable("id") Long id) {
        try {
            employeeService.delete(id);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("删除失败！");
        }
    }

    /**
     * 分页查询：post localhost:8080/employee
     * @param employeeQuery
     * @return
     */
    @PostMapping
    @ApiOperation(value = "分页查询",notes = "")
    public PageList<Employee> pageQuery(@RequestBody EmployeeQuery employeeQuery) {
        return employeeService.queryPage(employeeQuery);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PatchMapping
    @ApiOperation(value = "批量删除员工",notes = "")
    // 如果前端传过来的是join数组，后台可以用数组和集合接收
    public AjaxResult patchDelete(@RequestBody List<Long> ids) {
        try {
            employeeService.patchDelete(ids);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("删除失败！");
        }
    }

}