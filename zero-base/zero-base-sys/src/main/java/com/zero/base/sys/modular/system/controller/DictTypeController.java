package com.zero.base.sys.modular.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zero.base.common.core.base.controller.BaseController;
import com.zero.base.common.model.response.ResponseData;
import com.zero.base.common.model.response.SuccessResponseData;
import com.zero.base.common.pojo.page.LayuiPageInfo;
import com.zero.base.sys.modular.system.entity.DictType;
import com.zero.base.sys.modular.system.model.params.DictTypeParam;
import com.zero.base.sys.modular.system.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 字典类型表控制器
 *
 * @author yongzhi
 * @Date 2019-03-13 13:53:54
 */
@Controller
@RequestMapping("/dictType")
public class DictTypeController extends BaseController {

    private String PREFIX = "/modular/system/dictType";

    @Autowired
    private DictTypeService dictTypeService;

    /**
     * 跳转到主页面
     *
     * @author yongzhi
     * @Date 2019-03-13
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/dictType.html";
    }

    /**
     * 新增页面
     *
     * @author yongzhi
     * @Date 2019-03-13
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/dictType_add.html";
    }

    /**
     * 编辑页面
     *
     * @author yongzhi
     * @Date 2019-03-13
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/dictType_edit.html";
    }

    /**
     * 新增接口
     *
     * @author yongzhi
     * @Date 2019-03-13
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(DictTypeParam dictTypeParam) {
        this.dictTypeService.add(dictTypeParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author yongzhi
     * @Date 2019-03-13
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(DictTypeParam dictTypeParam) {
        this.dictTypeService.update(dictTypeParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author yongzhi
     * @Date 2019-03-13
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(DictTypeParam dictTypeParam) {
        this.dictTypeService.delete(dictTypeParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author yongzhi
     * @Date 2019-03-13
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(DictTypeParam dictTypeParam) {
        DictType detail = this.dictTypeService.getById(dictTypeParam.getDictTypeId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author yongzhi
     * @Date 2019-03-13
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(DictTypeParam dictTypeParam) {
        return this.dictTypeService.findPageBySpec(dictTypeParam);
    }

    /**
     * 查询所有字典
     *
     * @author yongzhi
     * @Date 2019-03-13
     */
    @ResponseBody
    @RequestMapping("/listTypes")
    public ResponseData listTypes() {

        QueryWrapper<DictType> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.select("dict_type_id", "code", "name");

        List<DictType> list = this.dictTypeService.list(objectQueryWrapper);
        return new SuccessResponseData(list);
    }

}


