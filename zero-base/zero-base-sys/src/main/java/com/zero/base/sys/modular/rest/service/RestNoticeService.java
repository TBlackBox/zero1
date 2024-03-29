package com.zero.base.sys.modular.rest.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.base.common.pojo.page.LayuiPageFactory;
import com.zero.base.sys.modular.rest.entity.RestNotice;
import com.zero.base.sys.modular.rest.mapper.RestNoticeMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 通知表 服务实现类
 * </p>
 *
 * @author yongzhi
 * @since 2018-12-07
 */
@Service
public class RestNoticeService extends ServiceImpl<RestNoticeMapper, RestNotice> {

    /**
     * 获取通知列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:05 PM
     */
    public Page<Map<String, Object>> list(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.list(page, condition);
    }
}
