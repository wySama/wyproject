package com.wy.mydemo.mapper;

import com.wy.mydemo.model.SysJobLog;
import com.wy.mydemo.model.SysJobLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysJobLogMapper {
    long countByExample(SysJobLogExample example);

    int deleteByExample(SysJobLogExample example);

    int deleteByPrimaryKey(Long jobLogId);

    int insert(SysJobLog record);

    int insertSelective(SysJobLog record);

    List<SysJobLog> selectByExample(SysJobLogExample example);

    SysJobLog selectByPrimaryKey(Long jobLogId);

    int updateByExampleSelective(@Param("record") SysJobLog record, @Param("example") SysJobLogExample example);

    int updateByExample(@Param("record") SysJobLog record, @Param("example") SysJobLogExample example);

    int updateByPrimaryKeySelective(SysJobLog record);

    int updateByPrimaryKey(SysJobLog record);
}