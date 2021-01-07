package org.eson.org.controller;

import org.eson.basic.util.AjaxResult;
import org.eson.basic.util.PageList;
import org.eson.org.domain.Employee;
import org.eson.org.query.EmployeeQuery;
import org.eson.org.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工接口类
 */
@RestController
@RequestMapping("/employee")
@Api(tags = "员工接口",description = "员工接口详细描述")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    //查询单个对象接口：get localhost:8080/employee/1

    @GetMapping("/{id}")
    @ApiOperation(value = "查询单个对象",notes = "根据id查询")
    public Employee findOne(@PathVariable("id")Long id){
        return employeeService.findOne(id);
    }

    //查询所有接口：get localhost:8080/employee
    @ApiOperation(value = "查询所有",notes = "")
    @GetMapping
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    //删除接口：Delete localhost:8080/employee/1
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除",notes = "根据id删除")
    public AjaxResult delete(@PathVariable("id")Long id){
        try {
            employeeService.delete(id);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("删除失败");
        }
    }

    //添加和修改接口 Put localhost:8080/employee
    //从请求体中获取数据：@RequestBody
    @ApiOperation(value = "员工添加或修改",notes = "如果有id就是修改否则就是添加")
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Employee employee){
        try {
            if(employee.getId() == null){
                employeeService.add(employee);
            }else{
                employeeService.update(employee);
            }
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("操作失败");
        }
    }

    //分页查询接口
    @ApiOperation(value = "分页查询",notes = "")
    @PostMapping
    public PageList<Employee> queryPage(@RequestBody EmployeeQuery employeeQuery){
        return employeeService.queryPage(employeeQuery);
    }

    //批量删除
    @ApiOperation(value = "批量删除",notes = "")
    @PatchMapping
    //如果前端传递的数据是json数组 - 后端可以用数组或集合都可以接受
    public AjaxResult patchDel(@RequestBody List<Long> ids){
        try {
            employeeService.patchDel(ids);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("操作失败");
        }
    }


}
