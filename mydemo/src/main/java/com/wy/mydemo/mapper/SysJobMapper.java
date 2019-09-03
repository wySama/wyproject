package com.wy.mydemo.mapper;

import com.wy.mydemo.model.SysJob;
import com.wy.mydemo.model.SysJobExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysJobMapper {
    long countByExample(SysJobExample example);

    int deleteByExample(SysJobExample example);

    int deleteByPrimaryKey(@Param("jobId") Long jobId, @Param("jobName") String jobName, @Param("jobGroup") String jobGroup);

    int insert(SysJob record);

    int insertSelective(SysJob record);

    List<SysJob> selectByExample(SysJobExample example);

    SysJob selectByPrimaryKey(@Param("jobId") Long jobId, @Param("jobName") String jobName, @Param("jobGroup") String jobGroup);

    int updateByExampleSelective(@Param("record") SysJob record, @Param("example") SysJobExample example);

    int updateByExample(@Param("record") SysJob record, @Param("example") SysJobExample example);

    int updateByPrimaryKeySelective(SysJob record);

    int updateByPrimaryKey(SysJob record);
}