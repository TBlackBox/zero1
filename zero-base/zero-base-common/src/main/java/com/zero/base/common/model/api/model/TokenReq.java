package com.zero.base.common.model.api.model;


import cn.hutool.core.util.StrUtil;
import com.zero.base.common.model.request.AbstractBaseRequest;
import lombok.Data;

/**
 * token的请求
 *
 * @author fengshuonan
 * @Date 2019/5/13 20:51
 */
@Data
public class TokenReq extends AbstractBaseRequest {

    /**
     * token
     */
    private String token;

    @Override
    public String checkParam() {
        if (StrUtil.isEmpty(token)) {
            return "请求token为空！";
        }
        return null;
    }

}
