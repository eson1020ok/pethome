package org.eson.org.service.impl;

import org.eson.basic.service.impl.BaseServiceImpl;
import org.eson.basic.util.PageList;
import org.eson.org.domain.Department;
import org.eson.org.mapper.DepartmentMapper;
import org.eson.org.query.DepartmentQuery;
import org.eson.org.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Transactional
    @Override
    public void add(Department department) {
        departmentMapper.save(department);
//        int i = 1/0;//测试事务
        this.update(department);
    }
    @Transactional
    @Override
    public void update(Department department) {
        if(department.getParent()==null || department.getParent().getId()==null){
            //将自增的id加到dirPath
            department.setDirPath("/"+department.getId());
        }else{//选择了上级部门
            //获取上级部门的id
            Long parent_id = department.getParent().getId();
            //根据上级部门的id查询其dirPath
            String parent_dirPath =  departmentMapper.loadOne(parent_id).getDirPath();
            //设置当前对象的dirPath
            department.setDirPath(parent_dirPath+"/"+department.getId());
        }
        departmentMapper.update(department);
    }
    @Transactional
    @Override
    public void patchDelete(List<Long> ids) {
        departmentMapper.patchDelelete(ids);
    }

    @Override
    public List<Department> getDeptTree(Long id) {
        if(id==0){
            return departmentMapper.getDeptTreeForAdd();
        }else{
            return departmentMapper.getDeptTreeForUpdate(id);
        }
    }
}
