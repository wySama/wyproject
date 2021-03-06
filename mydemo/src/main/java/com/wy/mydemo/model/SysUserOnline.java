package com.wy.mydemo.model;

import com.wy.mydemo.common.enums.OnlineStatus;

import java.io.Serializable;
import java.util.Date;

public class SysUserOnline extends BaseEntity implements Serializable {
    private String sessionid;

    private String loginName;

    private String deptName;

    private String ipaddr;

    private String loginLocation;

    private String browser;

    private String os;

    /**
     * 在线状态
     */
    private OnlineStatus status = OnlineStatus.on_line;

    private Date startTimestamp;

    private Date lastAccessTime;

    private Long expireTime;

    private static final long serialVersionUID = 1L;

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid == null ? null : sessionid.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr == null ? null : ipaddr.trim();
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation == null ? null : loginLocation.trim();
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser == null ? null : browser.trim();
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os == null ? null : os.trim();
    }

    public OnlineStatus getStatus() {
        return status;
    }

    public void setStatus(OnlineStatus status) {
        this.status = status;
    }


    public Date getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Date startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( getClass().getSimpleName() );
        sb.append( " [" );
        sb.append( "Hash = " ).append( hashCode() );
        sb.append( ", sessionid=" ).append( sessionid );
        sb.append( ", loginName=" ).append( loginName );
        sb.append( ", deptName=" ).append( deptName );
        sb.append( ", ipaddr=" ).append( ipaddr );
        sb.append( ", loginLocation=" ).append( loginLocation );
        sb.append( ", browser=" ).append( browser );
        sb.append( ", os=" ).append( os );
        sb.append( ", status=" ).append( status );
        sb.append( ", startTimestamp=" ).append( startTimestamp );
        sb.append( ", lastAccessTime=" ).append( lastAccessTime );
        sb.append( ", expireTime=" ).append( expireTime );
        sb.append( "]" );
        return sb.toString();
    }
}