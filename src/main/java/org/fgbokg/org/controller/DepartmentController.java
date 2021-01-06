package org.fgbokg.org.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fgbokg.basic.util.AjaxResult;
import org.fgbokg.basic.util.PageList;
import org.fgbokg.org.domain.Department;
import org.fgbokg.org.query.DepartmentQuery;
import org.fgbokg.org.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门类的controller
 *              前后端分离的接口
 */
@RestController
@RequestMapping("/department")
@Api(tags = "部门接口",description = "部门接口详细描述")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    /**
     * 根据id查询单个对象：get localhost:8080/department/1
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "查询一个部门对象",notes = "根据id查询")
    public Department findOne(@PathVariable("id") Long id) {
        return departmentService.findOne(id);
    }

    /**
     * 查询所有对象：get localhost:8080/department
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询所有的部门对象",notes = "")
    public List<Department> findAll() {
        return departmentService.findAll();
    }

    /**
     * 加载部门树，查询所有的一级部门和对应的二级部门
     * @return
     */
    @GetMapping("/deptTree/{id}")
    @ApiOperation(value = "查询部门树",notes = "")
    public List<Department> getDeptTreeUpdate(@PathVariable("id") Long id) {
        return departmentService.queryDeptTree(id);
    }

    /**
     * 加载部门树，查询所有的一级部门和对应的二级部门
     * @return
     */
    @GetMapping("/deptTree")
    @ApiOperation(value = "查询部门树",notes = "")
    public List<Department> getDeptTreeAdd(Long id) {
        return departmentService.queryDeptTree(id);
    }

    /**
     * 添加和修改：Put localhost:8080/department
     * @param department
     * @return
     */
    @PutMapping
    @ApiOperation(value = "部门添加或修改",notes = "如果有id就是修改否则就是添加")
    public AjaxResult addOrUpdate(@RequestBody Department department) {
        try {
            if (department.getId() == null) {
                departmentService.add(department);
            } else {
                departmentService.update(department);
            }
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("操作失败！");
        }
    }

    /**
     * 删除：Delete localhost:8080/department
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除一个部门对象",notes = "")
    public AjaxResult delete(@PathVariable("id") Long id) {
        try {
            departmentService.delete(id);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("删除失败！");
        }
    }

    /**
     * 分页查询：post localhost:8080/department
     * @param departmentQuery
     * @return
     */
    @PostMapping
    @ApiOperation(value = "分页查询",notes = "")
    public PageList<Department> pageQuery(@RequestBody DepartmentQuery departmentQuery) {
        return departmentService.queryPage(departmentQuery);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PatchMapping
    @ApiOperation(value = "批量删除",notes = "")
    // 如果前端传过来的是join数组，后台可以用数组和集合接收
    public AjaxResult patchDelete(@RequestBody List<Long> ids) {
        try {
            departmentService.patchDelete(ids);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("删除失败！");
        }
    }

}