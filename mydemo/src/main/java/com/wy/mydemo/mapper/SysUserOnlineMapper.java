package com.wy.mydemo.mapper;

import com.wy.mydemo.model.SysUserOnline;
import com.wy.mydemo.model.SysUserOnlineExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysUserOnlineMapper {
    long countByExample(SysUserOnlineExample example);

    int deleteByExample(SysUserOnlineExample example);

    int deleteByPrimaryKey(String sessionid);

    int insert(SysUserOnline record);

    int insertSelective(SysUserOnline record);

    List<SysUserOnline> selectByExample(SysUserOnlineExample example);

    SysUserOnline selectByPrimaryKey(String sessionid);

    int updateByExampleSelective(@Param("record") SysUserOnline record, @Param("example") SysUserOnlineExample example);

    int updateByExample(@Param("record") SysUserOnline record, @Param("example") SysUserOnlineExample example);

    int updateByPrimaryKeySelective(SysUserOnline record);

    int updateByPrimaryKey(SysUserOnline record);

    /**
     * 查询过期会话集合
     *
     * @param lastAccessTime 过期时间
     * @return 会话集合
     */
     List<SysUserOnline> selectOnlineByExpired(String lastAccessTime);

    /**
     * 保存会话信息
     *
     * @param online 会话信息
     * @return 结果
     */
     int saveOnline(SysUserOnline online);
}