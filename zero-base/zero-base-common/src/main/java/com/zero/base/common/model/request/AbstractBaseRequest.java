
package com.zero.base.common.model.request;

import com.zero.base.common.model.validator.BaseValidatingParam;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 远程服务的参数的基类
 *
 * @author fengshuonan
 * @date 2018-08-06-下午4:22
 */
@Getter
@Setter
public abstract class AbstractBaseRequest implements BaseValidatingParam, Serializable {

    /**
     * 唯一请求号
     */
    private String requestNo;

    /**
     * 业务节点id
     */
    private String spanId;

    /**
     * 当前登录用户的token
     */
    private String token;

}
