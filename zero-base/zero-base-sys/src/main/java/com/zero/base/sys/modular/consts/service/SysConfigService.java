package com.zero.base.sys.modular.consts.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.base.common.pojo.page.LayuiPageInfo;
import com.zero.base.sys.modular.consts.entity.SysConfig;
import com.zero.base.sys.modular.consts.model.params.SysConfigParam;
import com.zero.base.sys.modular.consts.model.result.SysConfigResult;

import java.util.List;

/**
 * <p>
 * 参数配置 服务类
 * </p>
 *
 * @author yongzhi
 * @since 2019-06-20
 */
public interface SysConfigService extends IService<SysConfig> {

    /**
     * 新增
     *
     * @author yongzhi
     * @Date 2019-06-20
     */
    void add(SysConfigParam param);

    /**
     * 删除
     *
     * @author yongzhi
     * @Date 2019-06-20
     */
    void delete(SysConfigParam param);

    /**
     * 更新
     *
     * @author yongzhi
     * @Date 2019-06-20
     */
    void update(SysConfigParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author yongzhi
     * @Date 2019-06-20
     */
    SysConfigResult findBySpec(SysConfigParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author yongzhi
     * @Date 2019-06-20
     */
    List<SysConfigResult> findListBySpec(SysConfigParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author yongzhi
     * @Date 2019-06-20
     */
    LayuiPageInfo findPageBySpec(SysConfigParam param);

}
