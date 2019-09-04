package com.wy.mydemo.mapper;

import com.wy.mydemo.model.SysPost;
import com.wy.mydemo.model.SysPostExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysPostMapper {
    long countByExample(SysPostExample example);

    int deleteByExample(SysPostExample example);

    int deleteByPrimaryKey(Long postId);

    int insert(SysPost record);

    int insertSelective(SysPost record);

    List<SysPost> selectByExample(SysPostExample example);

    SysPost selectByPrimaryKey(Long postId);

    int updateByExampleSelective(@Param("record") SysPost record, @Param("example") SysPostExample example);

    int updateByExample(@Param("record") SysPost record, @Param("example") SysPostExample example);

    int updateByPrimaryKeySelective(SysPost record);

    int updateByPrimaryKey(SysPost record);

    /**
     * 根据用户ID查询岗位
     *
     * @param userId 用户ID
     * @return 岗位列表
     */
     List<SysPost> selectPostsByUserId(Long userId);


    /**
     * 校验岗位名称
     *
     * @param postName 岗位名称
     * @return 结果
     */
     SysPost checkPostNameUnique(String postName);

    /**
     * 校验岗位编码
     *
     * @param postCode 岗位编码
     * @return 结果
     */
     SysPost checkPostCodeUnique(String postCode);

}