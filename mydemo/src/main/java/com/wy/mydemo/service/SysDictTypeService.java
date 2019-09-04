package com.wy.mydemo.service;

import com.wy.mydemo.common.constant.UserConstants;
import com.wy.mydemo.common.exception.business.BusinessException;
import com.wy.mydemo.mapper.SysDictDataMapper;
import com.wy.mydemo.mapper.SysDictTypeMapper;
import com.wy.mydemo.model.SysDictDataExample;
import com.wy.mydemo.model.SysDictType;
import com.wy.mydemo.model.SysDictTypeExample;
import com.wy.mydemo.util.StringUtil;
import com.wy.mydemo.util.StringUtils;
import com.wy.mydemo.util.core.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class SysDictTypeService {
    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    public List<SysDictType> selectDictTypeList(SysDictType dictType) {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    public SysDictTypeExample preExample(SysDictType dictType){
        SysDictTypeExample example = new SysDictTypeExample();
        SysDictTypeExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty( dictType.getDictName() )){
            criteria.andDictNameLike(dictType.getDictName());
        }
        if (StringUtils.isNotEmpty( dictType.getStatus() )){
            criteria.andStatusEqualTo(dictType.getStatus());
        }
        if (StringUtils.isNotEmpty( dictType.getDictType() )){
            criteria.andDictNameLike(dictType.getDictType());
        }
        /*if (null !=  dictType.getCreateTime()){
            criteria.andCreateByBetween(  )
        }*/

        return example;
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    public List<SysDictType> selectDictTypeAll() {
        return dictTypeMapper.selectDictTypeAll();
    }

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    public SysDictType selectDictTypeById(Long dictId) {
        return dictTypeMapper.selectByPrimaryKey( dictId );
    }

    /**
     * 通过字典ID删除字典信息
     *
     * @param dictId 字典ID
     * @return 结果
     */
    public int deleteDictTypeById(Long dictId) {
        return dictTypeMapper.deleteByPrimaryKey( dictId );
    }

    /**
     * 批量删除字典类型
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    public int deleteDictTypeByIds(String ids) throws BusinessException {
        Long[] dictIds = Convert.toLongArray( ids );
        for (Long dictId : dictIds) {
            SysDictType dictType = selectDictTypeById( dictId );
            SysDictDataExample example = new SysDictDataExample();
            example.createCriteria().andDictTypeEqualTo( dictType.getDictType() );
            if (dictDataMapper.countByExample(example) > 0) {
                throw new BusinessException( String.format( "%1$s已分配,不能删除", dictType.getDictName() ) );
            }
        }
        SysDictTypeExample sysDictTypeExample = new SysDictTypeExample();
        sysDictTypeExample.createCriteria().andDictIdIn( Arrays.asList(dictIds));
        return  dictTypeMapper.deleteByExample(sysDictTypeExample);

    }

    /**
     * 新增保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int insertDictType(SysDictType dictType) {
        return dictTypeMapper.insertSelective( dictType );
    }

    /**
     * 修改保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Transactional
    public int updateDictType(SysDictType dictType) {
        SysDictType oldDict = dictTypeMapper.selectByPrimaryKey( dictType.getDictId() );
        dictDataMapper.updateDictDataType( oldDict.getDictType(), dictType.getDictType() );
        return dictTypeMapper.updateByPrimaryKeySelective( dictType );
    }

    /**
     * 校验字典类型称是否唯一
     *
     * @param dict 字典类型
     * @return 结果
     */
    public String checkDictTypeUnique(SysDictType dict) {
        Long dictId = StringUtils.isNull( dict.getDictId() ) ? -1L : dict.getDictId();

        SysDictType dictType = dictTypeMapper.checkDictTypeUnique( dict.getDictType() );
        if (StringUtils.isNotNull( dictType ) && dictType.getDictId().longValue() != dictId.longValue()) {
            return UserConstants.DICT_TYPE_NOT_UNIQUE;
        }
        return UserConstants.DICT_TYPE_UNIQUE;
    }

}
