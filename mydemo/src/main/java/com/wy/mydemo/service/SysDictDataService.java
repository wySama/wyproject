package com.wy.mydemo.service;

import com.wy.mydemo.mapper.SysDictDataMapper;
import com.wy.mydemo.model.SysDictData;
import com.wy.mydemo.model.SysDictDataExample;
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

}
