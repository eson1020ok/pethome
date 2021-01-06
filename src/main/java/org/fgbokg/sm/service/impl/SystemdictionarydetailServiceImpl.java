package org.fgbokg.sm.service.impl;

import org.fgbokg.basic.service.impl.BaseServiceImpl;
import org.fgbokg.sm.domain.Systemdictionarydetail;
import org.fgbokg.sm.mapper.SystemdictionarydetailMapper;
import org.fgbokg.sm.service.ISystemdictionarydetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据字典明细service实现类
 */
@Service
public class SystemdictionarydetailServiceImpl extends BaseServiceImpl<Systemdictionarydetail> implements ISystemdictionarydetailService {

    @Autowired
    private SystemdictionarydetailMapper systemdictionarydetailMapper;

    /**
     * 批量删除
     * @param ids
     */
    @Transactional
    @Override
    public void patchDelete(List<Long> ids) {
        systemdictionarydetailMapper.patchDelete(ids);
    }
}
