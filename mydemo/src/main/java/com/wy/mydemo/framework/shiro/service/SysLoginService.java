package com.wy.mydemo.framework.shiro.service;


import com.wy.mydemo.common.constant.Constants;
import com.wy.mydemo.common.constant.ShiroConstants;
import com.wy.mydemo.common.constant.UserConstants;
import com.wy.mydemo.common.enums.UserStatus;
import com.wy.mydemo.common.exception.user.*;
import com.wy.mydemo.framework.manager.AsyncManager;
import com.wy.mydemo.framework.manager.factory.AsyncFactory;
import com.wy.mydemo.framework.shiro.util.MessageUtils;
import com.wy.mydemo.framework.shiro.util.ShiroUtils;
import com.wy.mydemo.model.SysUser;
import com.wy.mydemo.service.SysUserService;
import com.wy.mydemo.util.DateUtils;
import com.wy.mydemo.framework.shiro.util.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SysLoginService {

    @Autowired
    private SysUserService userService;
    @Autowired
    private SysPasswordService passwordService;

    /**
     * 登录
     */
    public SysUser login(String username, String password) {
        // 验证码校验
        if (!StringUtils.isEmpty( ServletUtils.getRequest().getAttribute( ShiroConstants.CURRENT_CAPTCHA ) )) {
            AsyncManager.me().execute( AsyncFactory.recordLogininfor( username, Constants.LOGIN_FAIL, MessageUtils.message( "user.jcaptcha.error" ) ) );
            throw new CaptchaException();
        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty( username ) || StringUtils.isEmpty( password )) {
            AsyncManager.me().execute( AsyncFactory.recordLogininfor( username, Constants.LOGIN_FAIL, MessageUtils.message( "not.null" ) ) );
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            AsyncManager.me().execute( AsyncFactory.recordLogininfor( username, Constants.LOGIN_FAIL, MessageUtils.message( "user.password.not.match" ) ) );
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            AsyncManager.me().execute( AsyncFactory.recordLogininfor( username, Constants.LOGIN_FAIL, MessageUtils.message( "user.password.not.match" ) ) );
            throw new UserPasswordNotMatchException();
        }

        // 查询用户信息
        SysUser user = userService.selectUserByLoginName( username );

        if (user == null && maybeMobilePhoneNumber( username )) {
            user = userService.selectUserByPhoneNumber( username );
        }

//        if (user == null && maybeEmail( username )) {
//            user = userService.selectUserByEmail( username );
//        }

        if (user == null) {
            AsyncManager.me().execute( AsyncFactory.recordLogininfor( username, Constants.LOGIN_FAIL, MessageUtils.message( "user.not.exists" ) ) );
            throw new UserNotExistsException();
        }

        if (UserStatus.DELETED.getCode().equals( user.getDelFlag() )) {
            AsyncManager.me().execute( AsyncFactory.recordLogininfor( username, Constants.LOGIN_FAIL, MessageUtils.message( "user.password.delete" ) ) );
            throw new UserDeleteException();
        }

        if (UserStatus.DISABLE.getCode().equals( user.getStatus() )) {
            AsyncManager.me().execute( AsyncFactory.recordLogininfor( username, Constants.LOGIN_FAIL, MessageUtils.message( "user.blocked", user.getRemark() ) ) );
            throw new UserBlockedException();
        }

        passwordService.validate( user, password );

        AsyncManager.me().execute(
                AsyncFactory.recordLogininfor(
                        username, Constants.LOGIN_SUCCESS, MessageUtils.message( "user.login.success" )
                ) );
        recordLoginInfo( user );
        return user;
    }


    private boolean maybeMobilePhoneNumber(String username) {
        if (!username.matches( UserConstants.MOBILE_PHONE_NUMBER_PATTERN )) {
            return false;
        }
        return true;
    }
    /**
     * 记录登录信息
     */
    public void recordLoginInfo(SysUser user) {
        user.setLoginIp( ShiroUtils.getIp() );
        user.setLoginDate( DateUtils.getNowDate() );
        userService.updateUserInfo( user );
    }

}
