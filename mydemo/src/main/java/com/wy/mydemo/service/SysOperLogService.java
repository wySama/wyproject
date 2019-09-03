package com.wy.mydemo.service;

import com.wy.mydemo.mapper.SysOperLogMapper;
import com.wy.mydemo.model.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysOperLogService {

    @Autowired
    private SysOperLogMapper operLogMapper;
    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    public void insertOperlog(SysOperLog operLog)
    {
        operLogMapper.insertSelective(operLog);
    }
}
