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
package com.zero.base.sys.core.beetl;

import com.zero.base.auth.context.LoginContext;
import com.zero.base.common.consts.ConstantsContext;
import com.zero.base.common.core.util.ToolUtil;
import com.zero.base.common.i18n.context.UserTranslationContext;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

/**
 * beetl拓展配置,绑定一些工具类,方便在模板中直接调用
 *
 * @author yongzhi
 * @Date 2018/2/22 21:03
 */
public class BeetlConfiguration extends BeetlGroupUtilConfiguration {

    private LoginContext loginContext;

    public BeetlConfiguration(LoginContext loginContext) {
        this.loginContext = loginContext;
    }

    @Override
    public void initOther() {
        groupTemplate.registerFunctionPackage("shiro", loginContext);
        groupTemplate.registerFunctionPackage("tool", new ToolUtil());
        groupTemplate.registerFunctionPackage("constants", new ConstantsContext());
        groupTemplate.registerFunctionPackage("lang", new UserTranslationContext());
    }
}
