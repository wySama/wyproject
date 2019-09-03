package com.wy.mydemo.service;


import com.wy.mydemo.common.annotation.DataScope;
import com.wy.mydemo.mapper.SysUserMapper;
import com.wy.mydemo.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 通过登录名查询用户
     *
     * @param loginName 登录名
     * @return 用户对象信息
     */
    public SysUser selectUserByLoginName(String loginName) {
        return userMapper.selectUserByLoginName( loginName );
    }

    /**
     * 通过手机号码查询用户
     *
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    public SysUser selectUserByPhoneNumber(String phoneNumber) {
        return userMapper.selectUserByPhoneNumber( phoneNumber );
    }


    /**
     * 修改用户个人详细信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUserInfo(SysUser user) {
        return userMapper.updateByPrimaryKeySelective( user );
    }


    public SysUser selectByUserById(Long userId) {
        return userMapper.selectByPrimaryKey( userId );
    }


    /**
     * 根据条件分页查询已分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SysUser> selectAllocatedList(SysUser user){
        return userMapper.selectAllocatedList(user);
    }

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SysUser> selectUnallocatedList(SysUser user) {
        return userMapper.selectUnallocatedList(user);
    }

}
