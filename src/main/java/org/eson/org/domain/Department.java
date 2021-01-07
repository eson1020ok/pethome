package org.eson.org.domain;

import org.eson.basic.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department extends BaseDomain{

    private String sn;          //部门的编号
    private String name;        //部门的名称
    private String dirPath;     //部门的路径
    private Integer state = 0;  //部门的状态：0 1 正常 停用

    //关联字段
    private Long manager_id;    //部门经理 - 员工
    //关联对象
    private Employee manager;

    //关联字段
    private Long parent_id;     //上一级部门 - 部门
    //关联对象
    private Department parent;

    //注意：这里属性名children要与级联框中的值一致
    //一对多的关联查询：保存当前部门下所有的二级部门
    List<Department> children = new ArrayList<>();


}
