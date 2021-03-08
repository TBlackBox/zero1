
package com.zero.base.common.model.request;

import com.zero.base.common.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础请求参数封装，用于控制器中的接口
 *
 * @author fengshuonan
 * @Date 2019-09-28 18:09
 */
@Data
public class BaseRequest implements BaseValidatingParam, Serializable {

    /**
     * 每页显示数量
     */
    private Long pageSize;

    /**
     * 第几页
     */
    private Long pageNo;

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 正序或者倒序排列（asc 或 desc）
     */
    private String sort;

}
