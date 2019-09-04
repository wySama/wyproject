package com.wy.mydemo.service;


import com.wy.mydemo.mapper.SysNoticeMapper;
import com.wy.mydemo.model.SysNotice;
import com.wy.mydemo.model.SysNoticeExample;
import com.wy.mydemo.util.StringUtil;
import com.wy.mydemo.util.StringUtils;
import com.wy.mydemo.util.core.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysNoticeService {

    @Autowired
    private SysNoticeMapper noticeMapper;

    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    public SysNotice selectNoticeById(Long noticeId) {
        return noticeMapper.selectByPrimaryKey( noticeId );
    }

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<SysNotice> selectNoticeList(SysNotice notice) {
        SysNoticeExample example = prepareExample(notice);
        return   noticeMapper.selectByExample(example);
    }

    public SysNoticeExample  prepareExample(SysNotice notice) {
        SysNoticeExample example = new SysNoticeExample();
        SysNoticeExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty( notice.getNoticeTitle() )){
            criteria.andNoticeTitleLike( "%"+notice.getNoticeTitle() +"%" );
        }
        if (StringUtils.isNotEmpty( notice.getNoticeType() )){
            criteria.andNoticeTypeEqualTo( notice.getNoticeType() );
        }
        if (StringUtils.isNotEmpty( notice.getCreateBy() )){
            criteria.andNoticeTitleLike( "%"+notice.getCreateBy() +"%" );
        }
        return example;
    }

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(SysNotice notice) {
        return noticeMapper.insertSelective( notice );
    }

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    public int updateNotice(SysNotice notice) {
        return noticeMapper.updateByPrimaryKeySelective( notice );
    }

    /**
     * 删除公告对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteNoticeByIds(String ids) {
        return noticeMapper.deleteNoticeByIds( Convert.toStrArray( ids ) );
    }


}
