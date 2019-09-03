package com.wy.mydemo.mapper;

import com.wy.mydemo.model.SysRoleDept;
import com.wy.mydemo.model.SysRoleDeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysRoleDeptMapper {
    long countByExample(SysRoleDeptExample example);

    int deleteByExample(SysRoleDeptExample example);

    int deleteByPrimaryKey(@Param("roleId") Long roleId, @Param("deptId") Long deptId);

    int insert(SysRoleDept record);

    int insertSelective(SysRoleDept record);

    List<SysRoleDept> selectByExample(SysRoleDeptExample example);

    int updateByExampleSelective(@Param("record") SysRoleDept record, @Param("example") SysRoleDeptExample example);

    int updateByExample(@Param("record") SysRoleDept record, @Param("example") SysRoleDeptExample example);

    /**
     * 通过角色ID删除角色和部门关联
     *
     * @param roleId 角色ID
     * @return 结果
     */
     int deleteRoleDeptByRoleId(Long roleId);

    /**
     * 批量新增角色部门信息
     *
     * @param roleDeptList 角色部门列表
     * @return 结果
     */
     int batchRoleDept(List<SysRoleDept> roleDeptList);
}