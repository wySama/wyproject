package com.wy.mydemo.controller.tool;

import com.wy.mydemo.common.base.controller.BaseController;
import com.wy.mydemo.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * swagger 接口
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/tool/swagger")
public class SwaggerController extends BaseController {

    @RequiresPermissions("tool:swagger:view")
    @GetMapping()
    public String index() {
        return redirect( "/swagger-ui.html" );
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format( "redirect:{}", url );
    }
}
