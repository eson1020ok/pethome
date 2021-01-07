package org.eson.org.service;

import org.eson.basic.exception.CustomException;
import org.eson.basic.service.IBaseService;
import org.eson.org.domain.Shop;

/**
 * 店铺service
 */
public interface IShopService extends IBaseService<Shop> {

    /**
     * 店铺入驻
     * @param shop 店铺对象
     */
    void settlement(Shop shop) throws CustomException;
}
