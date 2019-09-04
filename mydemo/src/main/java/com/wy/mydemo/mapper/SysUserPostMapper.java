package com.wy.mydemo.mapper;

import com.wy.mydemo.model.SysUserPost;
import com.wy.mydemo.model.SysUserPostExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysUserPostMapper {
    int countByExample(SysUserPostExample example);

    int deleteByExample(SysUserPostExample example);

    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("postId") Long postId);

    int insert(SysUserPost record);

    int insertSelective(SysUserPost record);

    List<SysUserPost> selectByExample(SysUserPostExample example);

    int updateByExampleSelective(@Param("record") SysUserPost record, @Param("example") SysUserPostExample example);

    int updateByExample(@Param("record") SysUserPost record, @Param("example") SysUserPostExample example);

    /**
     * 批量新增用户岗位信息
     *
     * @param userPostList 用户角色列表
     * @return 结果
     */
     int batchUserPost(List<SysUserPost> userPostList);
}