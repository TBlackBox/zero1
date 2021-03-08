
package com.zero.base.common.model.api.model;


import com.zero.base.common.model.auth.AbstractLoginUser;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;


/**
 * 当前用户的登录信息
 *
 * @author fengshuonan
 * @Date 2018/8/22 下午6:19
 */
@SuppressWarnings("ALL")
@Data
public class BaseLoginUser implements AbstractLoginUser, Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 角色id集合
     */
    private Set<Long> roleIds;

    /**
     * 角色名称集合
     */
    private Set<String> roleNames;

    /**
     * 可用资源集合
     */
    private Set<String> resourceUrls;

    /**
     * 部门id
     */
    private Long deptId;

    @Override
    public Long getUserUniqueId() {
        return userId;
    }

    @Override
    public Long getAppId() {
        return appId;
    }

    @Override
    public Set<Long> getRoleIds() {
        return roleIds;
    }

    @Override
    public Set<String> getRoleCodes() {
        return roleNames;
    }

    @Override
    public Set<String> getResourceUrls() {
        return resourceUrls;
    }
}
