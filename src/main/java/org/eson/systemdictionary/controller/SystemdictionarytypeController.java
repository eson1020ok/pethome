package org.eson.systemdictionary.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.eson.systemdictionary.domain.Systemdictionarytype;
import org.eson.systemdictionary.query.SystemdictionarytypeQuery;
import org.eson.systemdictionary.service.ISystemdictionarytypeService;
import org.eson.basic.util.AjaxResult;
import org.eson.basic.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典类的controller
 *              前后端分离的接口
 */
@RestController
@RequestMapping("/systemdictionarytype")
@Api(tags = "数据字典接口",description = "数据字典接口详细描述")
public class SystemdictionarytypeController {

    @Autowired
    private ISystemdictionarytypeService systemdictionarytypeService;

    /**
     * 根据id查询单个对象：get localhost:8080/systemdictionarytype/1
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "查询一个数据字典对象",notes = "根据id查询")
    public Systemdictionarytype findOne(@PathVariable("id") Long id) {
        return systemdictionarytypeService.findOne(id);
    }

    /**
     * 查询所有对象：get localhost:8080/systemdictionarytype
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询所有的数据字典对象",notes = "")
    public List<Systemdictionarytype> findAll() {
        return systemdictionarytypeService.findAll();
    }

    /**
     * 添加和修改：Put localhost:8080/systemdictionarytype
     * @param systemdictionarytype
     * @return
     */
    @PutMapping
    @ApiOperation(value = "数据字典添加或修改",notes = "如果有id就是修改否则就是添加")
    public AjaxResult addOrUpdate(@RequestBody Systemdictionarytype systemdictionarytype) {
        try {
            if (systemdictionarytype.getId() == null) {
                systemdictionarytypeService.add(systemdictionarytype);
            } else {
                systemdictionarytypeService.update(systemdictionarytype);
            }
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("操作失败！");
        }
    }

    /**
     * 删除：Delete localhost:8080/systemdictionarytype
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除一个数据字典对象",notes = "")
    public AjaxResult delete(@PathVariable("id") Long id) {
        try {
            systemdictionarytypeService.delete(id);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("删除失败！");
        }
    }

    /**
     * 分页查询：post localhost:8080/systemdictionarytype
     * @param systemdictionarytypeQuery
     * @return
     */
    @PostMapping
    @ApiOperation(value = "分页查询",notes = "")
    public PageList<Systemdictionarytype> pageQuery(@RequestBody SystemdictionarytypeQuery systemdictionarytypeQuery) {
        return systemdictionarytypeService.queryPage(systemdictionarytypeQuery);
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
            systemdictionarytypeService.patchDelete(ids);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("删除失败！");
        }
    }

}