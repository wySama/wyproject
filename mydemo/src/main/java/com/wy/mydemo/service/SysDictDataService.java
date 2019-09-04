package com.wy.mydemo.service;

import com.wy.mydemo.mapper.SysDictDataMapper;
import com.wy.mydemo.model.SysDictData;
import com.wy.mydemo.model.SysDictDataExample;
import com.wy.mydemo.util.StringUtils;
import com.wy.mydemo.util.core.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictDataService {

    @Autowired
    private SysDictDataMapper dictDataMapper;


    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    public List<SysDictData> selectDictDataByType(String dictType) {
        SysDictDataExample example = new SysDictDataExample();
        example.createCriteria().andStatusEqualTo( "0" ).andDictTypeEqualTo( dictType );
        example.setOrderByClause( "dict_sort asc" );
        return dictDataMapper.selectByExample( example );
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String selectDictLabel(String dictType, String dictValue) {
        return dictDataMapper.selectDictLabel( dictType, dictValue );
    }

    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    public List<SysDictData> selectDictDataList(SysDictData dictData) {
        SysDictDataExample example =prepareExample(dictData);
        return dictDataMapper.selectByExample( example );
    }


    public SysDictDataExample prepareExample(SysDictData dictData){
        SysDictDataExample example = new SysDictDataExample();
        SysDictDataExample.Criteria  criteria= example.createCriteria();
        if (StringUtils.isNotEmpty(dictData.getDictType())){
            criteria.andDictTypeEqualTo(  dictData.getDictType());
        }
        if (StringUtils.isNotEmpty(dictData.getDictLabel())){
            criteria.andDictLabelLike("%"+  dictData.getDictLabel() + "%");
        }
        if (StringUtils.isNotEmpty(dictData.getStatus())){
            criteria.andStatusEqualTo( dictData.getStatus());
        }
        return example;
    }

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    public SysDictData selectDictDataById(Long dictCode) {
        return dictDataMapper.selectByPrimaryKey( dictCode );
    }

    /**
     * 通过字典ID删除字典数据信息
     *
     * @param dictCode 字典数据ID
     * @return 结果
     */
    public int deleteDictDataById(Long dictCode) {
        return dictDataMapper.deleteByPrimaryKey( dictCode );
    }

    /**
     * 批量删除字典数据
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    public int deleteDictDataByIds(String ids) {
        return dictDataMapper.deleteDictDataByIds( Convert.toStrArray( ids ) );
    }

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    public int insertDictData(SysDictData dictData) {
        return dictDataMapper.insertSelective( dictData );
    }

    /**
     * 修改保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    public int updateDictData(SysDictData dictData) {
        return dictDataMapper.updateByPrimaryKeySelective( dictData );
    }


}
