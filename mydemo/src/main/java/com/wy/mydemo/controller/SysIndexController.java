package com.wy.mydemo.controller;

import com.wy.mydemo.config.Global;
import com.wy.mydemo.framework.shiro.util.ShiroUtils;
import com.wy.mydemo.model.SysMenu;
import com.wy.mydemo.model.SysUser;
import com.wy.mydemo.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 首页 业务处理
 *
 * @author ruoyi
 */
@Controller
public class SysIndexController {
    @Autowired
    private SysMenuService menuService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap) {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser( user );
        mmap.put( "menus", menus );
        mmap.put( "user", user );
        mmap.put( "copyrightYear", Global.getCopyrightYear() );
        mmap.put( "demoEnabled", Global.isDemoEnabled() );
        return "views/index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap) {
        mmap.put( "version", Global.getVersion() );
        return "views/main";
    }
}
