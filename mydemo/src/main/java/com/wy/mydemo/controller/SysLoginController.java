package com.wy.mydemo.controller;

import com.wy.mydemo.model.AjaxResult;
import com.wy.mydemo.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SysLoginController {

    @GetMapping(value={"/","/login"})
    public String login(){
        return "views/login";
    }



    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken( username, password, rememberMe );
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login( token );
            return AjaxResult.success();
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty( e.getMessage() )) {
                msg = e.getMessage();
            }
            return AjaxResult.error( msg );
        }
    }


}
