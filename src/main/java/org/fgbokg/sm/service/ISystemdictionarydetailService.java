package org.fgbokg.sm.service;

import org.fgbokg.basic.service.IBaseService;
import org.fgbokg.sm.domain.Systemdictionarydetail;

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
