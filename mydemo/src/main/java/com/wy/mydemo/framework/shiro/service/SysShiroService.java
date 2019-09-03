package com.wy.mydemo.framework.shiro.service;


import com.wy.mydemo.framework.shiro.session.OnlineSession;
import com.wy.mydemo.model.SysUserOnline;
import com.wy.mydemo.service.SysUserOnlineService;
import com.wy.mydemo.util.StringUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 会话db操作处理
 *
 * @author ruoyi
 */
@Component
public class SysShiroService {
    @Autowired
    private SysUserOnlineService onlineService;

    /**
     * 删除会话
     *
     * @param onlineSession 会话信息
     */
    public void deleteSession(OnlineSession onlineSession) {
        onlineService.deleteOnlineById( String.valueOf( onlineSession.getId() ) );
    }

    /**
     * 获取会话信息
     *
     * @param sessionId
     * @return
     */
    public Session getSession(Serializable sessionId) {
        SysUserOnline userOnline = onlineService.selectOnlineById( String.valueOf( sessionId ) );
        return StringUtils.isNull( userOnline ) ? null : createSession( userOnline );
    }

    public Session createSession(SysUserOnline userOnline) {
        OnlineSession onlineSession = new OnlineSession();
        if (StringUtils.isNotNull( userOnline )) {
            onlineSession.setId( userOnline.getSessionid() );
            onlineSession.setHost( userOnline.getIpaddr() );
            onlineSession.setBrowser( userOnline.getBrowser() );
            onlineSession.setOs( userOnline.getOs() );
            onlineSession.setDeptName( userOnline.getDeptName() );
            onlineSession.setLoginName( userOnline.getLoginName() );
            onlineSession.setStartTimestamp( userOnline.getStartTimestamp() );
            onlineSession.setLastAccessTime( userOnline.getLastAccessTime() );
            onlineSession.setTimeout( userOnline.getExpireTime() );
        }
        return onlineSession;
    }
}
