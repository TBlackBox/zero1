package com.zero.base.sys.modular.rest.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.base.common.core.util.ToolUtil;
import com.zero.base.common.pojo.page.LayuiPageFactory;
import com.zero.base.common.pojo.page.LayuiPageInfo;
import com.zero.base.sys.modular.rest.entity.RestUserPos;
import com.zero.base.sys.modular.rest.mapper.RestUserPosMapper;
import com.zero.base.sys.modular.rest.service.RestUserPosService;
import com.zero.base.sys.modular.system.model.params.UserPosParam;
import com.zero.base.sys.modular.system.model.result.UserPosResult;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户职位关联表 服务实现类
 * </p>
 *
 * @author yongzhi
 * @since 2019-06-28
 */
@Service
public class RestUserPosServiceImpl extends ServiceImpl<RestUserPosMapper, RestUserPos> implements RestUserPosService {

    @Override
    public void add(UserPosParam param) {
        RestUserPos entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(UserPosParam param) {
        this.removeById(getKey(param));
    }

    @Override
    public void update(UserPosParam param) {
        RestUserPos oldEntity = getOldEntity(param);
        RestUserPos newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public UserPosResult findBySpec(UserPosParam param) {
        return null;
    }

    @Override
    public List<UserPosResult> findListBySpec(UserPosParam param) {
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(UserPosParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(UserPosParam param) {
        return param.getUserPosId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private RestUserPos getOldEntity(UserPosParam param) {
        return this.getById(getKey(param));
    }

    private RestUserPos getEntity(UserPosParam param) {
        RestUserPos entity = new RestUserPos();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
