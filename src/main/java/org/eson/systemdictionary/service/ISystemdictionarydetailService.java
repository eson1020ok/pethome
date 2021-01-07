package org.eson.systemdictionary.service;

import org.eson.systemdictionary.domain.Systemdictionarydetail;
import org.eson.basic.service.IBaseService;

import java.util.List;

/**
 * service
 */
public interface ISystemdictionarydetailService extends IBaseService<Systemdictionarydetail> {

    /**
     * 批量删除
     * @param ids
     */
    void patchDelete(List<Long> ids);
}
