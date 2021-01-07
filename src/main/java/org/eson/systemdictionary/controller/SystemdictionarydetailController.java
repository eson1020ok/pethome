package org.eson.systemdictionary.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.eson.systemdictionary.domain.Systemdictionarydetail;
import org.eson.systemdictionary.query.SystemdictionarydetailQuery;
import org.eson.systemdictionary.service.ISystemdictionarydetailService;
import org.eson.basic.util.AjaxResult;
import org.eson.basic.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典明细类的controller
 *              前后端分离的接口
 */
@RestController
@RequestMapping("/systemdictionarydetail")
@Api(tags = "数据字典明细接口",description = "数据字典明细接口详细描述")
public class SystemdictionarydetailController {

    @Autowired
    private ISystemdictionarydetailService systemdictionarydetailService;

    /**
     * 根据id查询单个对象：get localhost:8080/systemdictionarydetail/1
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "查询一个数据字典明细对象",notes = "根据id查询")
    public Systemdictionarydetail findOne(@PathVariable("id") Long id) {
        return systemdictionarydetailService.findOne(id);
    }

    /**
     * 查询所有对象：get localhost:8080/systemdictionarydetail
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询所有的数据字典明细对象",notes = "")
    public List<Systemdictionarydetail> findAll() {
        return systemdictionarydetailService.findAll();
    }

    /**
     * 添加和修改：Put localhost:8080/systemdictionarydetail
     * @param systemdictionarydetail
     * @return
     */
    @PutMapping
    @ApiOperation(value = "数据字典明细添加或修改",notes = "如果有id就是修改否则就是添加")
    public AjaxResult addOrUpdate(@RequestBody Systemdictionarydetail systemdictionarydetail) {
        try {
            if (systemdictionarydetail.getId() == null) {
                systemdictionarydetailService.add(systemdictionarydetail);
            } else {
                systemdictionarydetailService.update(systemdictionarydetail);
            }
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("操作失败！");
        }
    }

    /**
     * 删除：Delete localhost:8080/systemdictionarydetail
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除一个数据字典明细对象",notes = "")
    public AjaxResult delete(@PathVariable("id") Long id) {
        try {
            systemdictionarydetailService.delete(id);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("删除失败！");
        }
    }

    /**
     * 分页查询：post localhost:8080/systemdictionarydetail
     * @param systemdictionarydetailQuery
     * @return
     */
    @PostMapping
    @ApiOperation(value = "分页查询",notes = "")
    public PageList<Systemdictionarydetail> pageQuery(@RequestBody SystemdictionarydetailQuery systemdictionarydetailQuery) {
        return systemdictionarydetailService.queryPage(systemdictionarydetailQuery);
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
            systemdictionarydetailService.patchDelete(ids);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("删除失败！");
        }
    }

}