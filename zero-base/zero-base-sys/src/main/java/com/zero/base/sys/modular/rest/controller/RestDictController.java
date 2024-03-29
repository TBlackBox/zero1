package com.zero.base.sys.modular.rest.controller;

import com.zero.base.common.core.base.controller.BaseController;
import com.zero.base.common.model.response.ResponseData;
import com.zero.base.common.model.response.SuccessResponseData;
import com.zero.base.common.pojo.node.ZTreeNode;
import com.zero.base.common.pojo.page.LayuiPageInfo;
import com.zero.base.sys.modular.rest.entity.RestDict;
import com.zero.base.sys.modular.rest.service.RestDictService;
import com.zero.base.sys.modular.system.model.params.DictParam;
import com.zero.base.sys.modular.system.model.result.DictResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 基础字典控制器
 *
 * @author yongzhi
 * @Date 2019-03-13 13:53:53
 */
@RestController
@RequestMapping("/rest/dict")
public class RestDictController extends BaseController {

    @Autowired
    private RestDictService restDictService;

    /**
     * 新增接口
     *
     * @author yongzhi
     * @Date 2019-03-13
     */
    @RequestMapping("/addItem")
    public ResponseData addItem(@RequestBody DictParam dictParam) {
        this.restDictService.add(dictParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author yongzhi
     * @Date 2019-03-13
     */
    @RequestMapping("/editItem")
    public ResponseData editItem(@RequestBody DictParam dictParam) {
        this.restDictService.update(dictParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author yongzhi
     * @Date 2019-03-13
     */
    @RequestMapping("/delete")
    public ResponseData delete(@RequestBody DictParam dictParam) {
        this.restDictService.delete(dictParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author yongzhi
     * @Date 2019-03-13
     */
    @RequestMapping("/detail")
    public ResponseData detail(@RequestBody DictParam dictParam) {
        DictResult dictResult = this.restDictService.dictDetail(dictParam.getDictId());
        return ResponseData.success(dictResult);
    }

    /**
     * 查询列表
     *
     * @author yongzhi
     * @Date 2019-03-13
     */
    @RequestMapping("/list")
    public LayuiPageInfo list(@RequestBody DictParam dictParam) {
        return this.restDictService.findPageBySpec(dictParam);
    }

    /**
     * 获取某个字典类型下的所有字典
     *
     * @author yongzhi
     * @Date 2019-03-13
     */
    @RequestMapping("/listDicts")
    public ResponseData listDicts(@RequestParam("dictTypeId") Long dictTypeId) {
        List<RestDict> dicts = this.restDictService.listDicts(dictTypeId);
        return new SuccessResponseData(dicts);
    }

    /**
     * 获取某个字典类型下的所有字典
     *
     * @author yongzhi
     * @Date 2019-03-13
     */
    @RequestMapping("/listDictsByCode")
    public ResponseData listDictsByCode(@RequestParam("dictTypeCode") String dictTypeCode) {
        List<RestDict> dicts = this.restDictService.listDictsByCode(dictTypeCode);
        return new SuccessResponseData(dicts);
    }

    /**
     * 获取某个类型下字典树的列表，ztree格式
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping(value = "/ztree")
    public List<ZTreeNode> ztree(@RequestParam("dictTypeId") Long dictTypeId,
                                 @RequestParam(value = "dictId", required = false) Long dictId) {
        return this.restDictService.dictTreeList(dictTypeId, dictId);
    }

}


