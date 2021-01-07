package org.eson.org.controller;

import org.eson.basic.util.AjaxResult;
import org.eson.basic.util.PageList;
import org.eson.org.domain.Department;
import org.eson.org.query.DepartmentQuery;
import org.eson.org.service.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门接口类
 */
@RestController
@RequestMapping("/department")
@Api(tags = "部门接口",description = "部门接口详细描述")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    //查询单个对象接口：get localhost:8080/department/1

    @GetMapping("/{id}")
    @ApiOperation(value = "查询单个对象",notes = "根据id查询")
    public Department findOne(@PathVariable("id")Long id){
        return departmentService.findOne(id);
    }

    //查询所有接口：get localhost:8080/department
    @ApiOperation(value = "查询所有",notes = "")
    @GetMapping
    public List<Department> findAll(){
        return departmentService.findAll();
    }

    //加载部门树 = 一级部门和所属的二级部门
    @ApiOperation(value = "加载部门树",notes = "")
    @GetMapping("/deptTree/{id}")
    public List<Department> getDeptTree(@PathVariable("id")Long id){
        return departmentService.getDeptTree(id);
    }

    //删除接口：Delete localhost:8080/department/1
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除",notes = "根据id删除")
    public AjaxResult delete(@PathVariable("id")Long id){
        try {
            departmentService.delete(id);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("删除失败");
        }
    }

    //添加和修改接口 Put localhost:8080/department
    //从请求体中获取数据：@RequestBody
    @ApiOperation(value = "部门添加或修改",notes = "如果有id就是修改否则就是添加")
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Department department){
        try {
            if(department.getId() == null){
                departmentService.add(department);
            }else{
                departmentService.update(department);
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
    public PageList<Department> queryPage(@RequestBody DepartmentQuery departmentQuery){
        return departmentService.queryPage(departmentQuery);
    }

    //批量删除
    @ApiOperation(value = "批量删除",notes = "")
    @PatchMapping
    //如果前端传递的数据是json数组 - 后端可以用数组或集合都可以接受
    public AjaxResult patchDel(@RequestBody List<Long> ids){
        try {
            departmentService.patchDelete(ids);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("操作失败");
        }
    }


}
