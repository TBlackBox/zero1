
package com.zero.base.sys.modular.system.warpper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.base.common.core.base.warpper.BaseControllerWrapper;
import com.zero.base.common.model.page.PageResult;
import com.zero.base.sys.core.constant.factory.ConstantFactory;
import com.zero.base.sys.core.util.DecimalUtil;

import java.util.List;
import java.util.Map;

/**
 * 部门列表的包装
 *
 * @author fengshuonan
 * @date 2017年4月25日 18:10:31
 */
public class NoticeWrapper extends BaseControllerWrapper {

    public NoticeWrapper(Map<String, Object> single) {
        super(single);
    }

    public NoticeWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public NoticeWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public NoticeWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        Long creater = DecimalUtil.getLong(map.get("createUser"));
        map.put("createrName", ConstantFactory.me().getUserNameById(creater));
    }
}
