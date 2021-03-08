package com.zero.base.common.tenant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.base.common.pojo.page.LayuiPageInfo;
import com.zero.base.common.tenant.entity.TenantInfo;
import com.zero.base.common.tenant.model.params.TenantInfoParam;
import com.zero.base.common.tenant.model.result.TenantInfoResult;

import java.util.List;

/**
 * <p>
 * 租户表 服务类
 * </p>
 *
 * @author yongzhi
 * @since 2019-06-16
 */
public interface TenantInfoService extends IService<TenantInfo> {

    /**
     * 新增
     *
     * @author yongzhi
     * @Date 2019-06-16
     */
    void add(TenantInfoParam param);

    /**
     * 删除
     *
     * @author yongzhi
     * @Date 2019-06-16
     */
    void delete(TenantInfoParam param);

    /**
     * 更新
     *
     * @author yongzhi
     * @Date 2019-06-16
     */
    void update(TenantInfoParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author yongzhi
     * @Date 2019-06-16
     */
    TenantInfoResult findBySpec(TenantInfoParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author yongzhi
     * @Date 2019-06-16
     */
    List<TenantInfoResult> findListBySpec(TenantInfoParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author yongzhi
     * @Date 2019-06-16
     */
    LayuiPageInfo findPageBySpec(TenantInfoParam param);

    /**
     * 获取租户信息，通过code
     *
     * @author fengshuonan
     * @Date 2019-06-19 14:17
     */
    TenantInfo getByCode(String code);

}
