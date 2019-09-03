package com.wy.mydemo.mapper;

import com.wy.mydemo.model.SysUser;
import com.wy.mydemo.model.SysUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Long userId);

    SysUser selectUserByLoginName(String loginName);

    SysUser selectUserByPhoneNumber(String phoneNumber);

    SysUser checkEmailUnique(String email);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated
     * @author hewei
     */
    int logicalDeleteByExample(SysUserExample example);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated
     * @author hewei
     */
    int logicalDeleteByPrimaryKey(Long userId);

    /**
     * 根据条件分页查询未已配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
     List<SysUser> selectAllocatedList(SysUser user);
    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
     List<SysUser> selectUnallocatedList(SysUser user);


    /**
     * 根据条件分页查询用户列表
     *
     * @param sysUser 用户信息
     * @return 用户信息集合信息
     */
     List<SysUser> selectUserList(SysUser sysUser);

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteUserByIds(Long[] ids);



}