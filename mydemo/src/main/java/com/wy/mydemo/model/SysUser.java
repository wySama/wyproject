package com.wy.mydemo.model;

import com.wy.mydemo.common.annotation.Excel;
import com.wy.mydemo.common.annotation.Excel.Type;
import com.wy.mydemo.common.annotation.Excels;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SysUser extends BaseEntity implements Serializable {
    /**
     * 这是Mybatis Generator拓展插件生成的属性(请勿删除).
     * This field corresponds to the database table sys_user
     *
     * @mbg.generated
     * @author hewei
     */
    public static final String DEL_FLAG = "1";

    @Excel(name = "用户序号", prompt = "用户编号")
    private Long userId;
    @Excel(name = "部门编号", type = Type.IMPORT)
    private Long deptId;
    @Excel(name = "登录名称")
    private String loginName;
    @Excel(name = "用户名称")
    private String userName;

    private String userType;
    @Excel(name = "用户邮箱")
    private String email;
    @Excel(name = "手机号码")
    private String phonenumber;
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=保密")
    private String sex;

    private String avatar;

    private String password;

    private String salt;
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    private String delFlag;
    @Excel(name = "最后登陆IP", type = Type.EXPORT)
    private String loginIp;
    @Excel(name = "最后登陆时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    private Date loginDate;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;
    /** 部门对象 */
    @Excels({
            @Excel(name = "部门名称", targetAttr = "deptName", type = Type.EXPORT),
            @Excel(name = "部门负责人", targetAttr = "leader", type = Type.EXPORT)
    })
    private SysDept dept;


    private List<SysRole> roles;

    /** 角色组 */
    private Long[] roleIds;

    /** 岗位组 */
    private Long[] postIds;


    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }


    public boolean isAdmin() {
        return isAdmin( this.userId );
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    public SysDept getDept() {
        if (dept == null) {
            dept = new SysDept();
        }
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public Long[] getPostIds() {
        return postIds;
    }

    public void setPostIds(Long[] postIds) {
        this.postIds = postIds;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( getClass().getSimpleName() );
        sb.append( " [" );
        sb.append( "Hash = " ).append( hashCode() );
        sb.append( ", userId=" ).append( userId );
        sb.append( ", deptId=" ).append( deptId );
        sb.append( ", loginName=" ).append( loginName );
        sb.append( ", userName=" ).append( userName );
        sb.append( ", userType=" ).append( userType );
        sb.append( ", email=" ).append( email );
        sb.append( ", phonenumber=" ).append( phonenumber );
        sb.append( ", sex=" ).append( sex );
        sb.append( ", avatar=" ).append( avatar );
        sb.append( ", password=" ).append( password );
        sb.append( ", salt=" ).append( salt );
        sb.append( ", status=" ).append( status );
        sb.append( ", delFlag=" ).append( delFlag );
        sb.append( ", loginIp=" ).append( loginIp );
        sb.append( ", loginDate=" ).append( loginDate );
        sb.append( ", createBy=" ).append( createBy );
        sb.append( ", createTime=" ).append( createTime );
        sb.append( ", updateBy=" ).append( updateBy );
        sb.append( ", updateTime=" ).append( updateTime );
        sb.append( ", remark=" ).append( remark );
        sb.append( "]" );
        return sb.toString();
    }
}