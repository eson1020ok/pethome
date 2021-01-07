package org.eson.systemdictionary.service.impl;

import org.eson.systemdictionary.domain.Systemdictionarydetail;
import org.eson.systemdictionary.mapper.SystemdictionarydetailMapper;
import org.eson.systemdictionary.service.ISystemdictionarydetailService;
import org.eson.basic.service.impl.BaseServiceImpl;
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
