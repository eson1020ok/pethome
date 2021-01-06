package org.fgbokg.org.mapper;

import org.fgbokg.basic.mapper.BaseMapper;
import org.fgbokg.org.domain.Shop;
import org.fgbokg.org.query.ShopQuery;

import java.util.List;

/**
 * 店铺类的mapper接口
 */
public interface ShopMapper extends BaseMapper<Shop> {

    /**
     * 批量删除
     * @param ids
     */
    void patchDelete(List<Long> ids);

    /**
     * 根据店铺名查询对象
     * @param name
     * @return
     */
    Shop loadByName(String name);

    /**
     * 根据员工id查询出店铺对象
     * @param admin_id
     * @return
     */
    Shop loadByAdminId(Long admin_id);
}
