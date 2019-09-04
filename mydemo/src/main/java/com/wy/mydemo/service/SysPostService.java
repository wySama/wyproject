package com.wy.mydemo.service;

import com.wy.mydemo.common.constant.UserConstants;
import com.wy.mydemo.common.exception.business.BusinessException;
import com.wy.mydemo.mapper.SysPostMapper;
import com.wy.mydemo.mapper.SysUserPostMapper;
import com.wy.mydemo.model.SysPost;
import com.wy.mydemo.model.SysPostExample;
import com.wy.mydemo.model.SysUserPostExample;
import com.wy.mydemo.util.StringUtils;
import com.wy.mydemo.util.core.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SysPostService {

    @Autowired
    private SysPostMapper postMapper;
    @Autowired
    private SysUserPostMapper sysUserPostMapper;

    /**
     * 根据用户ID查询岗位
     *
     * @param userId 用户ID
     * @return 岗位列表
     */
    public List<SysPost> selectPostsByUserId(Long userId) {
        List<SysPost> userPosts = postMapper.selectPostsByUserId( userId );
        SysPostExample example = new SysPostExample();
        List<SysPost> posts = postMapper.selectByExample( example );
        for (SysPost post : posts) {
            for (SysPost userRole : userPosts) {
                if (post.getPostId().longValue() == userRole.getPostId().longValue()) {
                    post.setFlag( true );
                    break;
                }
            }
        }
        return posts;
    }


    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    public List<SysPost> selectPostAll() {
        SysPostExample example = new SysPostExample();
        return postMapper.selectByExample( example );
    }

    /**
     * 查询岗位信息集合
     *
     * @param post 岗位信息
     * @return 岗位信息集合
     */
    public List<SysPost> selectPostList(SysPost post) {
        SysPostExample example = prepareExample( post );
        return postMapper.selectByExample( example );
    }

    public SysPostExample prepareExample(SysPost post) {
        SysPostExample example = new SysPostExample();
        SysPostExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty( post.getPostCode() )) {
            criteria.andPostCodeLike( "%" + post.getPostCode() + "%" );
        }
        if (StringUtils.isNotEmpty( post.getStatus() )) {
            criteria.andStatusEqualTo( post.getStatus() );
        }
        if (StringUtils.isNotEmpty( post.getPostName() )) {
            criteria.andPostCodeLike( "%" + post.getPostName() + "%" );
        }
        return example;
    }


    /**
     * 批量删除岗位信息
     *
     * @param ids 需要删除的数据ID
     * @throws Exception
     */
    public int deletePostByIds(String ids) throws BusinessException {
        Long[] postIds = Convert.toLongArray( ids );
        for (Long postId : postIds) {
            SysPost post = postMapper.selectByPrimaryKey( postId );
            SysUserPostExample example = new SysUserPostExample();
            example.createCriteria().andPostIdEqualTo( postId );
            if (sysUserPostMapper.countByExample( example ) > 0) {
                throw new BusinessException( String.format( "%1$s已分配,不能删除", post.getPostName() ) );
            }
        }
        SysPostExample postExample = new SysPostExample();
        postExample.createCriteria().andPostIdIn( Arrays.asList( postIds ) );
        return postMapper.deleteByExample( postExample );
    }


    /**
     * 校验岗位编码是否唯一
     *
     * @param post 岗位信息
     * @return 结果
     */
    public String checkPostCodeUnique(SysPost post) {
        Long postId = StringUtils.isNull( post.getPostId() ) ? -1L : post.getPostId();
        SysPost info = postMapper.checkPostCodeUnique( post.getPostCode() );
        if (StringUtils.isNotNull( info ) && info.getPostId().longValue() != postId.longValue()) {
            return UserConstants.POST_CODE_NOT_UNIQUE;
        }
        return UserConstants.POST_CODE_UNIQUE;
    }

    /**
     * 校验岗位名称是否唯一
     *
     * @param post 岗位信息
     * @return 结果
     */
    public String checkPostNameUnique(SysPost post) {
        Long postId = StringUtils.isNull( post.getPostId() ) ? -1L : post.getPostId();
        SysPost info = postMapper.checkPostNameUnique( post.getPostName() );
        if (StringUtils.isNotNull( info ) && info.getPostId().longValue() != postId.longValue()) {
            return UserConstants.POST_NAME_NOT_UNIQUE;
        }
        return UserConstants.POST_NAME_UNIQUE;
    }


    /**
     * 修改保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    public int updatePost(SysPost post) {
        return postMapper.updateByPrimaryKeySelective( post );
    }

    /**
     * 新增保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    public int insertPost(SysPost post) {
        return postMapper.insertSelective( post );
    }


    /**
     * 通过岗位ID查询岗位信息
     *
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    public SysPost selectPostById(Long postId) {
        return postMapper.selectByPrimaryKey( postId );
    }

}
