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
package com.zero.manager.modular.demos.controller;

import com.zero.base.common.core.base.controller.BaseController;
import com.zero.base.common.model.response.SuccessResponseData;
import com.zero.manager.modular.demos.service.TranTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试多数据源回滚
 *
 * @author yongzhi
 * @Date 2018/7/20 23:39
 */
@RestController
@RequestMapping("/tran/multi")
public class TestMultiTranController extends BaseController {

    @Autowired
    private TranTestService testMultiDbService;

    @RequestMapping("/success")
    public Object testSuccess() {
        testMultiDbService.beginTest();
        return SuccessResponseData.success();
    }

    @RequestMapping("/fail")
    public Object testFail() {
        testMultiDbService.beginTestFail();
        return SuccessResponseData.success();
    }

}

