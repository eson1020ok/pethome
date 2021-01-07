package org.eson.systemdictionary.mapper;

import org.eson.systemdictionary.domain.Systemdictionarytype;
import org.eson.basic.mapper.BaseMapper;

import java.util.List;

/**
 * mapper
 */
public interface SystemdictionarytypeMapper extends BaseMapper<Systemdictionarytype> {

    /**
     * 批量删除
     * @param ids
     */
    void patchDelete(List<Long> ids);
}
