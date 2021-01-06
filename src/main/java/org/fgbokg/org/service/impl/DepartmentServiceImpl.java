package org.fgbokg.org.service.impl;

import org.fgbokg.basic.service.impl.BaseServiceImpl;
import org.fgbokg.org.domain.Department;
import org.fgbokg.org.mapper.DepartmentMapper;
import org.fgbokg.org.query.DepartmentQuery;
import org.fgbokg.org.service.IDepartmentService;
import org.fgbokg.basic.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 部门类的service接口的实现类
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Transactional
    @Override
    public void add(Department department) {
        // 添加dirPath
        this.update(department);
        departmentMapper.save(department);
    }

    @Transactional
    @Override
    public void update(Department department) {
        // 获取添加部门的id
        Long id = department.getId();
        // 给dirPath赋值
        if (department.getParent() == null || department.getParent().getId() == null) { // 没有选择上级部门 || 用户选择了之后又清除
            // 为空，就直接给dirPath赋值
            department.setDirPath("/" + id);
        } else { // 选择了上级部门
            // 获取上级部门的id
            Long parent_id = department.getParent().getId();
            // 根据上级部门的id获取上级部门的dirPath
            String parent_dirPath = departmentMapper.loadOne(parent_id).getDirPath();
            // 拼接，给dirPath赋值
            department.setDirPath(parent_dirPath + "/" + id);
        }
        // 更新
        departmentMapper.update(department);
    }

    /**
     * 批量删除
     * @param ids
     */
    @Transactional
    @Override
    public void patchDelete(List<Long> ids) {
        departmentMapper.patchDelete(ids);
    }

    /**
     * 加载部门树，查询所有的一级部门和对应的二级部门
     * @return
     */
    @Override
    public List<Department> queryDeptTree(Long id) {
        return departmentMapper.loadDeptTree(id);
    }
}
