package com.wy.mydemo.controller.system;

import com.wy.mydemo.common.annotation.Log;
import com.wy.mydemo.common.base.controller.BaseController;
import com.wy.mydemo.common.base.page.TableDataInfo;
import com.wy.mydemo.common.constant.UserConstants;
import com.wy.mydemo.common.enums.BusinessType;
import com.wy.mydemo.framework.shiro.util.ShiroUtils;
import com.wy.mydemo.model.AjaxResult;
import com.wy.mydemo.model.SysDictType;
import com.wy.mydemo.service.SysDictTypeService;
import com.wy.mydemo.util.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典信息
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/dict")
public class SysDictTypeController extends BaseController {
    private String prefix = "views/system/dict/type";

    @Autowired
    private SysDictTypeService dictTypeService;

    @RequiresPermissions("system:dict:view")
    @GetMapping()
    public String dictType() {
        return prefix + "/type";
    }

    @PostMapping("/list")
    @RequiresPermissions("system:dict:list")
    @ResponseBody
    public TableDataInfo list(SysDictType dictType) {
        startPage();
        List<SysDictType> list = dictTypeService.selectDictTypeList( dictType );
        return getDataTable( list );
    }

    @Log(title = "字典类型", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dict:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysDictType dictType) {

        List<SysDictType> list = dictTypeService.selectDictTypeList( dictType );
        ExcelUtil<SysDictType> util = new ExcelUtil<SysDictType>( SysDictType.class );
        return util.exportExcel( list, "字典类型" );
    }

    /**
     * 新增字典类型
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dict:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysDictType dict) {
        if (UserConstants.DICT_TYPE_NOT_UNIQUE.equals( dictTypeService.checkDictTypeUnique( dict ) )) {
            return error( "新增字典'" + dict.getDictName() + "'失败，字典类型已存在" );
        }
        dict.setCreateBy( ShiroUtils.getLoginName() );
        return toAjax( dictTypeService.insertDictType( dict ) );
    }

    /**
     * 修改字典类型
     */
    @GetMapping("/edit/{dictId}")
    public String edit(@PathVariable("dictId") Long dictId, ModelMap mmap) {
        mmap.put( "dict", dictTypeService.selectDictTypeById( dictId ) );
        return prefix + "/edit";
    }

    /**
     * 修改保存字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dict:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysDictType dict) {
        if (UserConstants.DICT_TYPE_NOT_UNIQUE.equals( dictTypeService.checkDictTypeUnique( dict ) )) {
            return error( "修改字典'" + dict.getDictName() + "'失败，字典类型已存在" );
        }
        dict.setUpdateBy( ShiroUtils.getLoginName() );
        return toAjax( dictTypeService.updateDictType( dict ) );
    }

    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dict:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax( dictTypeService.deleteDictTypeByIds( ids ) );
        } catch (Exception e) {
            return error( e.getMessage() );
        }
    }

    /**
     * 查询字典详细
     */
    @RequiresPermissions("system:dict:list")
    @GetMapping("/detail/{dictId}")
    public String detail(@PathVariable("dictId") Long dictId, ModelMap mmap) {
        mmap.put( "dict", dictTypeService.selectDictTypeById( dictId ) );
        mmap.put( "dictList", dictTypeService.selectDictTypeAll() );
        return "views/system/dict/data/data";
    }

    /**
     * 校验字典类型
     */
    @PostMapping("/checkDictTypeUnique")
    @ResponseBody
    public String checkDictTypeUnique(SysDictType dictType) {
        return dictTypeService.checkDictTypeUnique( dictType );
    }
}
