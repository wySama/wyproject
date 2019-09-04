package com.wy.mydemo.mapper;

import com.wy.mydemo.model.SysDictData;
import com.wy.mydemo.model.SysDictDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysDictDataMapper {
    long countByExample(SysDictDataExample example);

    int deleteByExample(SysDictDataExample example);

    int deleteByPrimaryKey(Long dictCode);

    int insert(SysDictData record);

    int insertSelective(SysDictData record);

    List<SysDictData> selectByExample(SysDictDataExample example);

    SysDictData selectByPrimaryKey(Long dictCode);

    int updateByExampleSelective(@Param("record") SysDictData record, @Param("example") SysDictDataExample example);

    int updateByExample(@Param("record") SysDictData record, @Param("example") SysDictDataExample example);

    int updateByPrimaryKeySelective(SysDictData record);

    int updateByPrimaryKey(SysDictData record);

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
     String selectDictLabel(String dictType, String dictValue);

    /**
     * 同步修改字典类型
     *
     * @param oldDictType 旧字典类型
     * @param newDictType 新旧字典类型
     * @return 结果
     */
     int updateDictDataType(@Param("oldDictType") String oldDictType, @Param("newDictType") String newDictType);

    /**
     * 批量删除字典数据
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
     int deleteDictDataByIds(String[] ids);
}