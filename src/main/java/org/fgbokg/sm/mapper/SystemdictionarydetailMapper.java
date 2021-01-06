package org.fgbokg.sm.mapper;

import org.fgbokg.basic.mapper.BaseMapper;
import org.fgbokg.sm.domain.Systemdictionarydetail;

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
