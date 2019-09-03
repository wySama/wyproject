package com.wy.mydemo.service;


import com.wy.mydemo.common.annotation.DataScope;
import com.wy.mydemo.common.constant.UserConstants;
import com.wy.mydemo.common.exception.business.BusinessException;
import com.wy.mydemo.framework.shiro.util.SpringUtils;
import com.wy.mydemo.mapper.SysRoleDeptMapper;
import com.wy.mydemo.mapper.SysRoleMapper;
import com.wy.mydemo.mapper.SysUserRoleMapper;
import com.wy.mydemo.model.SysRole;
import com.wy.mydemo.model.SysRoleDept;
import com.wy.mydemo.model.SysUserRole;
import com.wy.mydemo.model.SysUserRoleExample;
import com.wy.mydemo.util.StringUtils;
import com.wy.mydemo.util.core.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.*;

@Service
public class SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysRoleDeptMapper roleDeptMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectRoleKeys(Long userId) {
        List<SysRole> perms = roleMapper.selectRolesByUserId( userId );
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms) {
            if (StringUtils.isNotNull( perm )) {
                permsSet.addAll( Arrays.asList( perm.getRoleKey().trim().split( "," ) ) );
            }
        }
        return permsSet;
    }


    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    @DataScope(deptAlias = "d")
    public List<SysRole> selectRoleList(SysRole role) {
        return roleMapper.selectRoleList( role );
    }

    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    public String checkRoleNameUnique(SysRole role) {
        Long roleId = StringUtils.isNull( role.getRoleId() ) ? -1L : role.getRoleId();
        SysRole info = roleMapper.checkRoleNameUnique( role.getRoleName() );
        if (StringUtils.isNotNull( info ) && info.getRoleId().longValue() != roleId.longValue()) {
            return UserConstants.ROLE_NAME_NOT_UNIQUE;
        }
        return UserConstants.ROLE_NAME_UNIQUE;
    }

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    public String checkRoleKeyUnique(SysRole role) {
        Long roleId = StringUtils.isNull( role.getRoleId() ) ? -1L : role.getRoleId();
        SysRole info = roleMapper.checkRoleKeyUnique( role.getRoleKey() );
        if (StringUtils.isNotNull( info ) && info.getRoleId().longValue() != roleId.longValue()) {
            return UserConstants.ROLE_KEY_NOT_UNIQUE;
        }
        return UserConstants.ROLE_KEY_UNIQUE;
    }

    /**
     * 修改数据权限信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Transactional
    public int authDataScope(SysRole role) {
        // 修改角色信息
        updateRole( role );
        // 删除角色与部门关联
        roleDeptMapper.deleteRoleDeptByRoleId( role.getRoleId() );
        // 新增角色和部门信息（数据权限）
        return insertRoleDept( role );
    }

    public int insertRole(SysRole role) {
        return roleMapper.insertSelective( role );
    }


    public SysRole selectRoleById(Long roleId) {
        return roleMapper.selectByPrimaryKey( roleId );
    }


    public int updateRole(SysRole role) {
        return roleMapper.updateByPrimaryKeySelective( role );
    }


    /**
     * 新增角色部门信息(数据权限)
     *
     * @param role 角色对象
     */
    public int insertRoleDept(SysRole role) {
        int rows = 1;
        // 新增角色与部门（数据权限）管理
        List<SysRoleDept> list = new ArrayList<SysRoleDept>();
        for (Long deptId : role.getDeptIds()) {
            SysRoleDept rd = new SysRoleDept();
            rd.setRoleId( role.getRoleId() );
            rd.setDeptId( deptId );
            list.add( rd );
        }
        if (list.size() > 0) {
            rows = roleDeptMapper.batchRoleDept( list );
        }
        return rows;
    }


    /**
     * 批量删除角色信息
     *
     * @param ids 需要删除的数据ID
     * @throws Exception
     */
    public int deleteRoleByIds(String ids) throws BusinessException {
        Long[] roleIds = Convert.toLongArray( ids );
        for (Long roleId : roleIds) {
            SysRole role = selectRoleById( roleId );
            if (countUserRoleByRoleId( roleId ) > 0) {
                throw new BusinessException( String.format( "%1$s已分配,不能删除", role.getRoleName() ) );
            }
        }
        return roleMapper.deleteRoleByIds( roleIds );
    }

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return int
     */
    public Integer countUserRoleByRoleId(Long roleId) {
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andRoleIdEqualTo( roleId );
        return userRoleMapper.countByExample( example );
    }

    public int update(SysRole record) {
        return roleMapper.updateByPrimaryKeySelective( record );
    }


    /**
     * 取消授权用户角色
     *
     * @param userRole 用户和角色关联信息
     * @return 结果
     */
    public int deleteAuthUser(SysUserRole userRole) {
        return userRoleMapper.deleteUserRoleInfo( userRole );
    }

    /**
     * 批量取消授权用户角色
     *
     * @param roleId  角色ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    public int deleteAuthUsers(Long roleId, String userIds) {
        return userRoleMapper.deleteUserRoleInfos( roleId, Convert.toLongArray( userIds ) );
    }


    /**
     * 批量选择授权用户角色
     *
     * @param roleId  角色ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    public int insertAuthUsers(Long roleId, String userIds) {
        Long[] users = Convert.toLongArray( userIds );
        // 新增用户与角色管理
        List<SysUserRole> list = new ArrayList<SysUserRole>();
        for (Long userId : users) {
            SysUserRole ur = new SysUserRole();
            ur.setUserId( userId );
            ur.setRoleId( roleId );
            list.add( ur );
        }
        return userRoleMapper.batchUserRole( list );
    }

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    public List<SysRole> selectRolesByUserId(Long userId) {
        List<SysRole> userRoles = roleMapper.selectRolesByUserId( userId );
        List<SysRole> roles = selectRoleAll();
        for (SysRole role : roles) {
            for (SysRole userRole : userRoles) {
                if (role.getRoleId().longValue() == userRole.getRoleId().longValue()) {
                    role.setFlag( true );
                    break;
                }
            }
        }
        return roles;
    }

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    public List<SysRole> selectRoleAll(){
        return SpringUtils.getAopProxy(this).selectRoleList(new SysRole());
    }

}
