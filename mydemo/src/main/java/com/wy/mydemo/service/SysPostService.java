package com.wy.mydemo.service;

import com.wy.mydemo.mapper.SysPostMapper;
import com.wy.mydemo.model.SysPost;
import com.wy.mydemo.model.SysPostExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPostService {

    @Autowired
    private SysPostMapper postMapper;

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
        return postMapper.selectByExample(example);
    }
}
