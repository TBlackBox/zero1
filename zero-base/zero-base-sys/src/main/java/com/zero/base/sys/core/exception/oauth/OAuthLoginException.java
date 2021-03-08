package com.zero.base.sys.core.exception.oauth;


import com.zero.base.common.model.exception.AbstractBaseExceptionEnum;
import com.zero.base.common.model.exception.ServiceException;

/**
 * 第三方登录异常
 *
 * @author fengshuonan
 * @Date 2019/6/9 18:43
 */
public class OAuthLoginException extends ServiceException {

    public OAuthLoginException(AbstractBaseExceptionEnum exception) {
        super(exception);
    }

}
