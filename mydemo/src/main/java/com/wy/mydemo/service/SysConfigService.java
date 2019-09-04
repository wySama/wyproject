package com.wy.mydemo.service;

import com.wy.mydemo.common.constant.UserConstants;
import com.wy.mydemo.mapper.SysConfigMapper;
import com.wy.mydemo.model.SysConfig;
import com.wy.mydemo.util.StringUtils;
import com.wy.mydemo.util.core.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysConfigService {

    @Autowired
    private SysConfigMapper configMapper;

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数key
     * @return 参数键值
     */
    public String selectConfigByKey(String configKey) {
        SysConfig config = new SysConfig();
        config.setConfigKey( configKey );
        SysConfig retConfig = null;
        try {
            retConfig = configMapper.selectConfig( config );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.isNotNull( retConfig ) ? retConfig.getConfigValue() : "";
    }


    /**
     * 查询参数配置信息
     *
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    public SysConfig selectConfigById(Long configId) {
        SysConfig config = new SysConfig();
        config.setConfigId( configId );
        return configMapper.selectConfig( config );
    }


    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    public List<SysConfig> selectConfigList(SysConfig config) {
        return configMapper.selectConfigList( config );
    }

    /**
     * 新增参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    public int insertConfig(SysConfig config) {
        return configMapper.insertSelective( config );
    }

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    public int updateConfig(SysConfig config) {
        return configMapper.updateByPrimaryKeySelective( config );
    }

    /**
     * 批量删除参数配置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigByIds(String ids) {
        return configMapper.deleteConfigByIds( Convert.toStrArray( ids ) );
    }

    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数配置信息
     * @return 结果
     */
    public String checkConfigKeyUnique(SysConfig config) {
        Long configId = StringUtils.isNull( config.getConfigId() ) ? -1L : config.getConfigId();
        SysConfig info = configMapper.checkConfigKeyUnique( config.getConfigKey() );
        if (StringUtils.isNotNull( info ) && info.getConfigId().longValue() != configId.longValue()) {
            return UserConstants.CONFIG_KEY_NOT_UNIQUE;
        }
        return UserConstants.CONFIG_KEY_UNIQUE;
    }


}
