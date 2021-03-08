package com.zero.base.auth.exception;

import com.zero.base.auth.exception.enums.AuthExceptionEnum;
import com.zero.base.common.model.exception.AbstractBaseExceptionEnum;
import lombok.Data;


/**
 * 没有访问权限
 *
 * @author fengshuonan
 * @Date 2019/7/18 22:18
 */
@Data
public class PermissionException extends RuntimeException {

    private Integer code;
    private String errorMessage;

    public PermissionException() {
        super(AuthExceptionEnum.NO_PERMISSION.getMessage());
        this.code = AuthExceptionEnum.NO_PERMISSION.getCode();
        this.errorMessage = AuthExceptionEnum.NO_PERMISSION.getMessage();
    }

    public PermissionException(AbstractBaseExceptionEnum exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }

}
