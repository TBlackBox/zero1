package com.zero.base.common.i18n.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.base.common.i18n.entity.Translation;
import com.zero.base.common.i18n.model.params.TranslationParam;
import com.zero.base.common.i18n.model.result.TranslationResult;
import com.zero.base.common.pojo.page.LayuiPageInfo;

import java.util.List;

/**
 * <p>
 * 多语言表 服务类
 * </p>
 *
 * @author yongzhi
 * @since 2019-10-17
 */
public interface TranslationService extends IService<Translation> {

    /**
     * 新增
     *
     * @author yongzhi
     * @Date 2019-10-17
     */
    void add(TranslationParam param);

    /**
     * 删除
     *
     * @author yongzhi
     * @Date 2019-10-17
     */
    void delete(TranslationParam param);

    /**
     * 更新
     *
     * @author yongzhi
     * @Date 2019-10-17
     */
    void update(TranslationParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author yongzhi
     * @Date 2019-10-17
     */
    TranslationResult findBySpec(TranslationParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author yongzhi
     * @Date 2019-10-17
     */
    List<TranslationResult> findListBySpec(TranslationParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author yongzhi
     * @Date 2019-10-17
     */
     LayuiPageInfo findPageBySpec(TranslationParam param);

}
