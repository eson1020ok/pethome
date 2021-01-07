package org.eson.systemdictionary.service;

import org.eson.systemdictionary.domain.Systemdictionarytype;
import org.eson.basic.service.IBaseService;

import java.util.List;

/**
 * service
 */
public interface ISystemdictionarytypeService extends IBaseService<Systemdictionarytype> {

    /**
     * 批量删除
     * @param ids
     */
    void patchDelete(List<Long> ids);
}
