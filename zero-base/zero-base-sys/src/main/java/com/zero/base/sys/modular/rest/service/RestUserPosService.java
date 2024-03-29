package com.zero.base.sys.modular.rest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.base.common.pojo.page.LayuiPageInfo;
import com.zero.base.sys.modular.rest.entity.RestUserPos;
import com.zero.base.sys.modular.system.model.params.UserPosParam;
import com.zero.base.sys.modular.system.model.result.UserPosResult;

import java.util.List;

/**
 * <p>
 * 用户职位关联表 服务类
 * </p>
 *
 * @author yongzhi
 * @since 2019-06-28
 */
public interface RestUserPosService extends IService<RestUserPos> {

    /**
     * 新增
     *
     * @author yongzhi
     * @Date 2019-06-28
     */
    void add(UserPosParam param);

    /**
     * 删除
     *
     * @author yongzhi
     * @Date 2019-06-28
     */
    void delete(UserPosParam param);

    /**
     * 更新
     *
     * @author yongzhi
     * @Date 2019-06-28
     */
    void update(UserPosParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author yongzhi
     * @Date 2019-06-28
     */
    UserPosResult findBySpec(UserPosParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author yongzhi
     * @Date 2019-06-28
     */
    List<UserPosResult> findListBySpec(UserPosParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author yongzhi
     * @Date 2019-06-28
     */
    LayuiPageInfo findPageBySpec(UserPosParam param);

}
