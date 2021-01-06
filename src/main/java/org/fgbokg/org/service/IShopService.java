package org.fgbokg.org.service;

import org.fgbokg.basic.exception.CustomException;
import org.fgbokg.basic.service.IBaseService;
import org.fgbokg.basic.util.PageList;
import org.fgbokg.org.domain.Shop;
import org.fgbokg.org.query.ShopQuery;

import javax.mail.MessagingException;
import java.util.List;

/**
 * 店铺类的service接口
 */
public interface IShopService extends IBaseService<Shop> {

    /**
     * 批量删除
     * @param ids
     */
    void patchDelete(List<Long> ids);

    /**
     * 店铺入驻
     * @param shop
     */
    void settlement(Shop shop) throws CustomException;

    /**
     * 通过id查询数据，并修改店铺状态1，待激活
     * @param id
     */
    void setStateAdopt(Long id) throws MessagingException;

    /**
     * 通过id查询数据，并修改店铺状态为-1，驳回
     * @param id
     */
    void setStateReject(Long id) throws MessagingException;

    /**
     * 通过id查询数据，并修改店铺状态为2，正常
     * @param id
     */
    void setStateNormal(Long id);

    /**
     * 根据员工id查询出店铺对象
     * @param admin_id
     * @return
     */
    Shop getOneShopList(Long admin_id);
}
