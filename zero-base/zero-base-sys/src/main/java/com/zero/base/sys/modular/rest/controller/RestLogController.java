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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.base.common.core.base.controller.BaseController;
import com.zero.base.common.log.BussinessLog;
import com.zero.base.common.model.response.ResponseData;
import com.zero.base.common.pojo.page.LayuiPageFactory;
import com.zero.base.common.pojo.page.LayuiPageInfo;
import com.zero.base.sys.core.constant.state.BizLogType;
import com.zero.base.sys.modular.rest.entity.RestOperationLog;
import com.zero.base.sys.modular.rest.model.LogQueryParam;
import com.zero.base.sys.modular.rest.service.RestOperationLogService;
import com.zero.base.sys.modular.system.warpper.LogWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 日志管理的控制器
 *
 * @author fengshuonan
 * @Date 2017年4月5日 19:45:36
 */
@RestController
@RequestMapping("/rest/log")
public class RestLogController extends BaseController {

    @Autowired
    private RestOperationLogService restOperationLogService;

    /**
     * 查询操作日志列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:34 PM
     */
    @RequestMapping("/list")
    public LayuiPageInfo list(@RequestBody LogQueryParam logQueryParam) {

        //获取分页参数
        Page page = LayuiPageFactory.defaultPage();

        //根据条件查询操作日志
        List<Map<String, Object>> result = restOperationLogService.getOperationLogs(
                page, logQueryParam.getBeginTime(), logQueryParam.getEndTime(),
                logQueryParam.getLogName(), BizLogType.valueOf(logQueryParam.getLogType()));

        page.setRecords(new LogWrapper(result).wrap());

        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 查询操作日志详情
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:34 PM
     */
    @RequestMapping("/detail/{id}")
    public Map<String, Object> detail(@PathVariable Long id) {
        RestOperationLog operationLog = restOperationLogService.getById(id);
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(operationLog);
        return new LogWrapper(stringObjectMap).wrap();
    }

    /**
     * 清空日志
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:34 PM
     */
    @BussinessLog(value = "清空业务日志")
    @RequestMapping("/delLog")
    public ResponseData delLog() {
        restOperationLogService.remove(new QueryWrapper<>());
        return SUCCESS_TIP;
    }
}
