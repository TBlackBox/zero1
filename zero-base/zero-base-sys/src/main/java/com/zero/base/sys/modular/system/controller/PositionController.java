package com.zero.base.sys.modular.system.controller;

import com.zero.base.common.core.base.controller.BaseController;
import com.zero.base.common.core.util.ToolUtil;
import com.zero.base.common.enums.CommonStatus;
import com.zero.base.common.model.exception.RequestEmptyException;
import com.zero.base.common.model.response.ResponseData;
import com.zero.base.common.model.response.SuccessResponseData;
import com.zero.base.common.pojo.page.LayuiPageInfo;
import com.zero.base.sys.modular.system.entity.Position;
import com.zero.base.sys.modular.system.model.params.PositionParam;
import com.zero.base.sys.modular.system.service.PositionService;
import com.zero.base.sys.modular.system.service.UserPosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 职位表控制器
 *
 * @author yongzhi
 * @Date 2019-06-27 21:33:47
 */
@Controller
@RequestMapping("/position")
public class PositionController extends BaseController {

    private String PREFIX = "/modular/system/position";

    @Autowired
    private PositionService positionService;

    @Autowired
    private UserPosService userPosService;

    /**
     * 跳转到主页面
     *
     * @author yongzhi
     * @Date 2019-06-27
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/position.html";
    }

    /**
     * 新增页面
     *
     * @author yongzhi
     * @Date 2019-06-27
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/position_add.html";
    }

    /**
     * 编辑页面
     *
     * @author yongzhi
     * @Date 2019-06-27
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/position_edit.html";
    }

    /**
     * 新增接口
     *
     * @author yongzhi
     * @Date 2019-06-27
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(PositionParam positionParam) {
        this.positionService.add(positionParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author yongzhi
     * @Date 2019-06-27
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(PositionParam positionParam) {
        this.positionService.update(positionParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author yongzhi
     * @Date 2019-06-27
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(PositionParam positionParam) {
        this.positionService.delete(positionParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author yongzhi
     * @Date 2019-06-27
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(PositionParam positionParam) {
        Position detail = this.positionService.getById(positionParam.getPositionId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author yongzhi
     * @Date 2019-06-27
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(@RequestParam(value = "condition", required = false) String condition) {

        PositionParam positionParam = new PositionParam();
        if (ToolUtil.isNotEmpty(condition)) {
            positionParam.setCode(condition);
            positionParam.setName(condition);
        }

        return this.positionService.findPageBySpec(positionParam);
    }

    /**
     * 修改状态
     *
     * @author yongzhi
     * @Date 2019-06-27
     */
    @ResponseBody
    @RequestMapping("/changeStatus")
    public ResponseData changeStatus(@RequestParam("positionId") String positionId,
                                     @RequestParam("status") Boolean status) {

        Position position = this.positionService.getById(positionId);
        if (position == null) {
            throw new RequestEmptyException();
        }

        if (status) {
            position.setStatus(CommonStatus.ENABLE.getCode());
        } else {
            position.setStatus(CommonStatus.DISABLE.getCode());
        }

        this.positionService.updateById(position);

        return new SuccessResponseData();
    }

    /**
     * 查询所有职位
     *
     * @author yongzhi
     * @Date 2019-03-13
     */
    @ResponseBody
    @RequestMapping("/listPositions")
    public LayuiPageInfo listlistPositionsTypes(@RequestParam(value = "userId", required = false) Long userId) {
        return this.positionService.listPositions(userId);
    }


}


