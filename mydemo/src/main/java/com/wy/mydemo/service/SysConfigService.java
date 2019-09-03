package com.wy.mydemo.service;

import com.wy.mydemo.mapper.SysConfigMapper;
import com.wy.mydemo.model.SysConfig;
import com.wy.mydemo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
