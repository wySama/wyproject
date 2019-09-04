package com.wy.mydemo.mapper;

import com.wy.mydemo.model.SysDictType;
import com.wy.mydemo.model.SysDictTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysDictTypeMapper {
    long countByExample(SysDictTypeExample example);

    int deleteByExample(SysDictTypeExample example);

    int deleteByPrimaryKey(Long dictId);

    int insert(SysDictType record);

    int insertSelective(SysDictType record);

    List<SysDictType> selectByExample(SysDictTypeExample example);

    SysDictType selectByPrimaryKey(Long dictId);

    int updateByExampleSelective(@Param("record") SysDictType record, @Param("example") SysDictTypeExample example);

    int updateByExample(@Param("record") SysDictType record, @Param("example") SysDictTypeExample example);

    int updateByPrimaryKeySelective(SysDictType record);

    int updateByPrimaryKey(SysDictType record);

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
     List<SysDictType> selectDictTypeList(SysDictType dictType);

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
     List<SysDictType> selectDictTypeAll();

    /**
     * 校验字典类型称是否唯一
     *
     * @param dictType 字典类型
     * @return 结果
     */
     SysDictType checkDictTypeUnique(String dictType);

}