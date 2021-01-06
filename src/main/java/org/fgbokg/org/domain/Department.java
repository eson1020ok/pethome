package org.fgbokg.org.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fgbokg.basic.domain.BaseDomain;

import java.util.List;

/**
 * 部门类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department extends BaseDomain {

   /** 部门编号 */
   private String sn;
   /** 部门名称 */
   private String name;
   /** 部门状态 */
   private Integer state = 0;
   /** 部门路径 */
   private String dirPath;

   /** 部门经理id */
   private Long manager_id;
   /** 部门经理，关联对象 */
   private Employee manager;

   /** 上级部门id */
   private Long parent_id;
   /** 上级部门，关联对象 */
   private Department parent;

   /** 一对多关联查询，查询二级部门 */
   private List<Department> children;

}
