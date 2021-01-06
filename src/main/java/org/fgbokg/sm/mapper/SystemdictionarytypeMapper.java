package org.fgbokg.sm.mapper;

import org.fgbokg.basic.mapper.BaseMapper;
import org.fgbokg.sm.domain.Systemdictionarytype;

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
