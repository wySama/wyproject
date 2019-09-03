package com.wy.mydemo.mapper;

import com.wy.mydemo.model.SysDept;
import com.wy.mydemo.model.SysDeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysDeptMapper {
    long countByExample(SysDeptExample example);

    int deleteByExample(SysDeptExample example);

    int deleteByPrimaryKey(Long deptId);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    List<SysDept> selectByExample(SysDeptExample example);

    SysDept selectByPrimaryKey(Long deptId);

    int updateByExampleSelective(@Param("record") SysDept record, @Param("example") SysDeptExample example);

    int updateByExample(@Param("record") SysDept record, @Param("example") SysDeptExample example);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table sys_dept
     *
     * @mbg.generated
     * @author hewei
     */
    int logicalDeleteByExample(SysDeptExample example);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table sys_dept
     *
     * @mbg.generated
     * @author hewei
     */
    int logicalDeleteByPrimaryKey(Long deptId);

    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
     List<SysDept> selectDeptList(SysDept dept);

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
     SysDept selectDeptById(Long deptId);
    /**
     * 校验部门名称是否唯一
     *
     * @param deptName 部门名称
     * @param parentId 父部门ID
     * @return 结果
     */
     SysDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

    /**
     * 根据ID查询所有子部门
     * @param deptId 部门ID
     * @return 部门列表
     */
     List<SysDept> selectChildrenDeptById(Long deptId);

    /**
     * 修改子元素关系
     *
     * @param depts 子元素
     * @return 结果
     */
     int updateDeptChildren(@Param("depts") List<SysDept> depts);

    /**
     * 修改所在部门的父级部门状态
     *
     * @param dept 部门
     */
     void updateDeptStatus(SysDept dept);

    /**
     * 查询部门人数
     *
     * @param dept 部门信息
     * @return 结果
     */
     int selectDeptCount(SysDept dept);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果
     */
     int checkDeptExistUser(Long deptId);

    /**
     * 根据角色ID查询部门
     *
     * @param roleId 角色ID
     * @return 部门列表
     */
     List<String> selectRoleDeptTree(Long roleId);


}