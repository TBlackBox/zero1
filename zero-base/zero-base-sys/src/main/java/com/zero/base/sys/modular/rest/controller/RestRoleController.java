/**
 * Copyright 2018-2020 yongzhi & fengshuonan (https://gitee.com/yongzhi)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zero.base.sys.modular.rest.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.base.common.core.base.controller.BaseController;
import com.zero.base.common.core.util.ToolUtil;
import com.zero.base.common.log.BussinessLog;
import com.zero.base.common.model.exception.ServiceException;
import com.zero.base.common.model.response.ResponseData;
import com.zero.base.common.pojo.node.ZTreeNode;
import com.zero.base.common.pojo.page.LayuiPageFactory;
import com.zero.base.common.pojo.page.LayuiPageInfo;
import com.zero.base.sys.core.constant.dictmap.DeleteDict;
import com.zero.base.sys.core.constant.dictmap.RoleDict;
import com.zero.base.sys.core.constant.factory.ConstantFactory;
import com.zero.base.sys.core.exception.enums.BizExceptionEnum;
import com.zero.base.sys.modular.rest.entity.RestRole;
import com.zero.base.sys.modular.rest.entity.RestUser;
import com.zero.base.sys.modular.rest.service.RestMenuService;
import com.zero.base.sys.modular.rest.service.RestRoleService;
import com.zero.base.sys.modular.rest.service.RestUserService;
import com.zero.base.sys.modular.system.model.RoleDto;
import com.zero.base.sys.modular.system.warpper.RoleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色控制器
 *
 * @author fengshuonan
 * @Date 2017年2月12日21:59:14
 */
@RestController
@RequestMapping("/rest/role")
public class RestRoleController extends BaseController {

    @Autowired
    private RestUserService restUserService;

    @Autowired
    private RestRoleService restRoleService;

    @Autowired
    private RestMenuService restMenuService;

    /**
     * 获取角色列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/list")
    public LayuiPageInfo list(@RequestParam(value = "roleName", required = false) String roleName) {
        Page<Map<String, Object>> roles = this.restRoleService.selectRoles(roleName);
        Page<Map<String, Object>> wrap = new RoleWrapper(roles).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 角色新增
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/add")
    @BussinessLog(value = "添加角色", key = "name", dict = RoleDict.class)
    public ResponseData add(@RequestBody RestRole restRole) {
        this.restRoleService.addRole(restRole);
        return SUCCESS_TIP;
    }

    /**
     * 角色修改
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/edit")
    @BussinessLog(value = "修改角色", key = "name", dict = RoleDict.class)
    public ResponseData edit(@RequestBody RoleDto roleDto) {
        this.restRoleService.editRole(roleDto);
        return SUCCESS_TIP;
    }

    /**
     * 删除角色
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/remove")
    @BussinessLog(value = "删除角色", key = "roleId", dict = DeleteDict.class)
    public ResponseData remove(@RequestParam Long roleId) {
        this.restRoleService.delRoleById(roleId);
        return SUCCESS_TIP;
    }

    /**
     * 查看角色
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/view/{roleId}")
    public ResponseData view(@PathVariable Long roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        RestRole role = this.restRoleService.getById(roleId);
        Map<String, Object> roleMap = BeanUtil.beanToMap(role);

        Long pid = role.getPid();
        String pName = ConstantFactory.me().getSingleRoleName(pid);
        roleMap.put("pName", pName);

        //获取角色绑定的菜单
        List<Long> menuIds = this.restMenuService.getMenuIdsByRoleId(roleId);
        List<String> menuNames = this.restMenuService.getBaseMapper().getMenuNamesByRoleId(roleId);
        if (ToolUtil.isNotEmpty(menuIds)) {
            roleMap.put("menuIds", menuIds);
            roleMap.put("menuNames", menuNames);
        } else {
            roleMap.put("menuIds", new ArrayList<>());
            roleMap.put("menuNames", new ArrayList<>());
        }

        return ResponseData.success(roleMap);
    }

    /**
     * 配置权限
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping("/setAuthority")
    @BussinessLog(value = "配置权限", key = "roleId,ids", dict = RoleDict.class)
    public ResponseData setAuthority(@RequestParam("roleId") Long roleId, @RequestParam("ids") String ids) {
        if (ToolUtil.isOneEmpty(roleId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.restRoleService.setAuthority(roleId, ids);
        return SUCCESS_TIP;
    }

    /**
     * 获取角色列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/roleTreeList")
    public List<ZTreeNode> roleTreeList() {
        List<ZTreeNode> roleTreeList = this.restRoleService.roleTreeList();
        roleTreeList.add(ZTreeNode.createParent());
        return roleTreeList;
    }

    /**
     * 获取角色列表，通过用户id
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/roleTreeListByUserId/{userId}")
    public List<ZTreeNode> roleTreeListByUserId(@PathVariable Long userId) {
        RestUser theUser = this.restUserService.getById(userId);
        String roleId = theUser.getRoleId();
        if (ToolUtil.isEmpty(roleId)) {
            return this.restRoleService.roleTreeList();
        } else {

            String[] strArray = roleId.split(",");

            //转化成Long[]
            Long[] longArray = new Long[strArray.length];
            for (int i = 0; i < strArray.length; i++) {
                longArray[i] = Long.valueOf(strArray[i]);
            }

            return this.restRoleService.roleTreeListByRoleId(longArray);
        }
    }

}
