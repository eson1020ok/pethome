package org.eson.org.controller;

import org.eson.basic.util.AjaxResult;
import org.eson.org.domain.Shop;
import org.eson.org.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private IShopService shopService;

    @PostMapping("/settlement")
    public AjaxResult settlement(@RequestBody Shop shop){
        try {
            //商家入驻
            shopService.settlement(shop);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg(e.getMessage());
        }
    }
}
