package com.wy.mydemo.service;

import com.wy.mydemo.framework.shiro.session.OnlineSession;
import com.wy.mydemo.mapper.SysUserOnlineMapper;
import com.wy.mydemo.model.SysUserOnline;
import com.wy.mydemo.model.SysUserOnlineExample;
import com.wy.mydemo.util.DateUtils;
import com.wy.mydemo.util.StringUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Service
public class SysUserOnlineService {

    @Autowired
    private SysUserOnlineMapper userOnlineMapper;

    /**
     * 保存会话信息
     *
     * @param online 会话信息
     */
    public void saveOnline(SysUserOnline online) {
        userOnlineMapper.saveOnline( online );
    }

    /**
     * 删除会话
     *
     * @param onlineSession 会话信息
     */
    public void deleteSession(OnlineSession onlineSession) {
        userOnlineMapper.deleteByPrimaryKey( String.valueOf( onlineSession.getId() ) );
    }

    /**
     * 通过会话序号删除信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public void deleteOnlineById(String sessionId) {
        SysUserOnline userOnline = selectOnlineById( sessionId );
        if (StringUtils.isNotNull( userOnline )) {
            userOnlineMapper.deleteByPrimaryKey( sessionId );
        }
    }

    /**
     * 通过会话序号查询信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public SysUserOnline selectOnlineById(String sessionId) {
        return userOnlineMapper.selectByPrimaryKey( sessionId );
    }

    /**
     * 获取会话信息
     *
     * @param sessionId
     * @return
     */
    public Session getSession(Serializable sessionId) {
        SysUserOnline userOnline = userOnlineMapper.selectByPrimaryKey( String.valueOf( sessionId ) );
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


    /**
     * 查询会话集合
     *
     * @param expiredDate 失效日期
     */
    public List<SysUserOnline> selectOnlineByExpired(Date expiredDate) {
        String lastAccessTime = DateUtils.parseDateToStr( DateUtils.YYYY_MM_DD_HH_MM_SS, expiredDate );
        return userOnlineMapper.selectOnlineByExpired( lastAccessTime );
    }


    /**
     * 通过会话序号删除信息
     *
     * @param sessions 会话ID集合
     * @return 在线用户信息
     */
    public void batchDeleteOnline(List<String> sessions) {
        SysUserOnlineExample example = new SysUserOnlineExample();
        example.createCriteria().andSessionidIn( sessions );
        userOnlineMapper.deleteByExample(example);

    }

}
