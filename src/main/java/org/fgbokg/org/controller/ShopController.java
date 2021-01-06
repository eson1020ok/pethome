package org.fgbokg.org.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fgbokg.basic.constant.LoginConstant;
import org.fgbokg.basic.util.AjaxResult;
import org.fgbokg.basic.util.PageList;
import org.fgbokg.org.domain.Employee;
import org.fgbokg.org.domain.Shop;
import org.fgbokg.org.query.ShopQuery;
import org.fgbokg.org.service.IShopService;
import org.fgbokg.user.domain.Reject;
import org.fgbokg.user.service.IRejectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 店铺店铺类的controller
 *              前后端分离的接口
 */
@RestController
@RequestMapping("/shop")
@Api(tags = "店铺接口",description = "店铺接口详细描述")
public class ShopController {

    @Autowired
    private IShopService shopService;

    @Autowired
    private IRejectService rejectService;

    /**
     * 根据id查询单个对象：get localhost:8080/shop/1
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "查询一个店铺对象",notes = "根据id查询")
    public Shop findOne(@PathVariable("id") Long id) {
        return shopService.findOne(id);
    }

    /**
     * 查询所有对象：get localhost:8080/shop
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询所有的店铺对象",notes = "")
    public List<Shop> findAll() {
        return shopService.findAll();
    }

    /**
     * 添加和修改：Put localhost:8080/shop
     * @param shop
     * @return
     */
    @PutMapping
    @ApiOperation(value = "店铺添加或修改",notes = "如果有id就是修改否则就是添加")
    public AjaxResult addOrUpdate(@RequestBody Shop shop) {
        try {
            if (shop.getId() == null) {
                shopService.add(shop);
            } else {
                shopService.update(shop);
            }
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("操作失败！");
        }
    }

    /**
     * 删除：Delete localhost:8080/shop
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除一个店铺对象",notes = "")
    public AjaxResult delete(@PathVariable("id") Long id) {
        try {
            shopService.delete(id);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("删除失败！");
        }
    }

    /**
     * 分页查询：post localhost:8080/shop
     * @param shopQuery
     * @return
     */
    @PostMapping
    @ApiOperation(value = "分页查询",notes = "")
    public PageList<Shop> pageQuery(@RequestBody ShopQuery shopQuery) {
        return shopService.queryPage(shopQuery);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PatchMapping
    @ApiOperation(value = "批量删除店铺",notes = "")
    // 如果前端传过来的是join数组，后台可以用数组和集合接收
    public AjaxResult patchDelete(@RequestBody List<Long> ids) {
        try {
            shopService.patchDelete(ids);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("删除失败！");
        }
    }

    /**
     * 店铺入驻
     * @return
     */
    @PostMapping("/settlement")
    public AjaxResult settlement(@RequestBody Shop shop) {
        try {
            shopService.settlement(shop);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg(e.getMessage());
        }
    }

    /**
     * 通过id查询数据，并修改店铺状态1，待激活
     * @param shop
     * @return
     */
    @PostMapping("/setStateAdopt")
    public AjaxResult setStateAdopt(@RequestBody Shop shop) {
        try {
            shopService.setStateAdopt(shop.getId());
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg(e.getMessage());
        }
    }

    /**
     * 通过id查询数据，并修改店铺状态为-1，驳回
     * @param shop
     * @return
     */
    @PostMapping("/setStateReject")
    public AjaxResult setStateReject(@RequestBody Reject reject) {
        try {
            shopService.setStateReject(reject.getShop_id());
            rejectService.add(reject);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg(e.getMessage());
        }
    }

    /**
     * 通过id查询数据，并修改店铺状态为2，正常
     * @param id
     * @return
     */
    @GetMapping("/setStateNormal/{id}")
    public String setStateNormal(@PathVariable("id") Long id) {
        try {
            shopService.setStateNormal(id);
            return "恭喜，激活成功，请马上登录！";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PostMapping("/getOneShopList")
    public Shop getOneShopList(HttpServletRequest req) {
        // 获取session对象
        HttpSession session = req.getSession();
        // 获取当前登陆用户
        Employee employee = (Employee) session.getAttribute(LoginConstant.EMPLOYEE_IN_SESSION);
        System.out.println(employee);
        return shopService.getOneShopList(employee.getId());
    }

}