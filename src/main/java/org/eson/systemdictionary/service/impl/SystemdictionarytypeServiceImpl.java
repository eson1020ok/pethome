package org.eson.systemdictionary.service.impl;

import org.eson.systemdictionary.domain.Systemdictionarytype;
import org.eson.systemdictionary.mapper.SystemdictionarytypeMapper;
import org.eson.systemdictionary.service.ISystemdictionarytypeService;
import org.eson.basic.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据字典明细service实现类
 */
@Service
public class SystemdictionarytypeServiceImpl extends BaseServiceImpl<Systemdictionarytype> implements ISystemdictionarytypeService {

    @Autowired
    private SystemdictionarytypeMapper systemdictionarytypeMapper;

    /**
     * 批量删除
     * @param ids
     */
    @Transactional
    @Override
    public void patchDelete(List<Long> ids) {
        systemdictionarytypeMapper.patchDelete(ids);
    }
}
