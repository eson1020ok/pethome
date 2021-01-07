package org.eson.org.mapper;

import org.eson.basic.mapper.BaseMapper;
import org.eson.org.domain.Shop;

public interface ShopMapper extends BaseMapper<Shop>{

    /**
     * 根据名字查询店铺对象
     * @param name
     * @return
     */
    Shop loadByName(String name);
}
