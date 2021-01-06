package org.fgbokg.sm.service;

import org.fgbokg.basic.service.IBaseService;
import org.fgbokg.sm.domain.Systemdictionarytype;

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
