package org.eson.systemdictionary.mapper;

import org.eson.systemdictionary.domain.Systemdictionarydetail;
import org.eson.basic.mapper.BaseMapper;

import java.util.List;

/**
 * mapper
 */
public interface SystemdictionarydetailMapper extends BaseMapper<Systemdictionarydetail> {

    /**
     * 批量删除
     * @param ids
     */
    void patchDelete(List<Long> ids);
}
