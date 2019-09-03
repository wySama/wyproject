package com.wy.mydemo.service;


import com.wy.mydemo.mapper.SysLogininforMapper;
import com.wy.mydemo.model.SysLogininfor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogininforService {

    @Autowired
    private SysLogininforMapper sysLogininforMapper;

    public int insertLogininfor(SysLogininfor SysLogininfor){
        return sysLogininforMapper.insertSelective(  SysLogininfor);
    }



}
