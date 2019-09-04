package com.wy.mydemo.mapper;

import com.wy.mydemo.model.SysNotice;
import com.wy.mydemo.model.SysNoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysNoticeMapper {
    long countByExample(SysNoticeExample example);

    int deleteByExample(SysNoticeExample example);

    int deleteByPrimaryKey(Long noticeId);

    int insert(SysNotice record);

    int insertSelective(SysNotice record);

    List<SysNotice> selectByExample(SysNoticeExample example);

    SysNotice selectByPrimaryKey(Long noticeId);

    int updateByExampleSelective(@Param("record") SysNotice record, @Param("example") SysNoticeExample example);

    int updateByExample(@Param("record") SysNotice record, @Param("example") SysNoticeExample example);

    int updateByPrimaryKeySelective(SysNotice record);

    int updateByPrimaryKey(SysNotice record);

    /**
     * 批量删除公告
     *
     * @param noticeIds 需要删除的数据ID
     * @return 结果
     */
     int deleteNoticeByIds(String[] noticeIds);
}