
package com.zero.base.common.core.page;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.base.common.core.context.RequestDataHolder;
import com.zero.base.common.core.request.RequestData;
import com.zero.base.common.core.util.HttpContext;
import com.zero.base.common.core.util.ToolUtil;
import com.zero.base.common.model.page.PageQuery;
import com.zero.base.common.model.util.ValidateUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;


/**
 * 默认分页参数构建
 *
 * @author fengshuonan
 * @date 2017年11月15日13:52:16
 */
public class PageFactory {

    /**
     * 排序，升序还是降序
     */
    private static final String ASC = "asc";

    /**
     * 每页大小的param名称
     */
    public static final String PAGE_SIZE_PARAM_NAME = "pageSize";

    /**
     * 第几页的param名称
     */
    public static final String PAGE_NO_PARAM_NAME = "pageNo";

    /**
     * 升序还是降序的param名称
     */
    public static final String SORT_PARAM_NAME = "sort";

    /**
     * 根据那个字段排序的param名称
     */
    public static final String ORDER_BY_PARAM_NAME = "orderBy";

    /**
     * 默认规则的分页
     *
     * @author fengshuonan
     * @Date 2018/7/23 下午4:11
     */
    public static <T> Page<T> defaultPage() {

        int pageSize = 20;
        int pageNo = 1;

        HttpServletRequest request = HttpContext.getRequest();

        if (request == null) {
            return new Page<>(pageNo, pageSize);
        }

        //每页条数
        String pageSizeString = getFieldValue(request, PAGE_SIZE_PARAM_NAME);
        if (ValidateUtil.isNotEmpty(pageSizeString)) {
            pageSize = Integer.parseInt(pageSizeString);
        }

        //第几页
        String pageNoString = getFieldValue(request, PAGE_NO_PARAM_NAME);
        if (ValidateUtil.isNotEmpty(pageNoString)) {
            pageNo = Integer.parseInt(pageNoString);
        }

        //获取排序字段和排序类型(asc/desc)
        String sort = getFieldValue(request, SORT_PARAM_NAME);
        String orderByField = getFieldValue(request, ORDER_BY_PARAM_NAME);

        Page<T> page = new Page<>(pageNo, pageSize);
        if (ToolUtil.isEmpty(orderByField)) {
            return page;
        }
        if (ToolUtil.isEmpty(sort)) {
            // 默认降序
            page.setDesc(orderByField);
            return page;
        }
        if (ASC.equalsIgnoreCase(sort)) {
            page.setAsc(orderByField);
        } else {
            page.setDesc(orderByField);
        }
        return page;
    }

    /**
     * 自定义参数的分页
     *
     * @author fengshuonan
     * @Date 2018/7/23 下午4:11
     */
    public static <T> Page<T> createPage(PageQuery pageQuery) {

        int pageSize = 20;
        int pageNo = 1;

        if (pageQuery != null && ValidateUtil.isNotEmpty(pageQuery.getPageSize())) {
            pageSize = pageQuery.getPageSize();
        }

        if (pageQuery != null && ValidateUtil.isNotEmpty(pageQuery.getPageNo())) {
            pageNo = pageQuery.getPageNo();
        }

        if (pageQuery == null) {
            return new Page<>(pageNo, pageSize);
        }

        Page<T> page = new Page<>(pageNo, pageSize);
        if (ToolUtil.isEmpty(pageQuery.getSort())) {
            return page;
        }
        if (ASC.equalsIgnoreCase(pageQuery.getSort())) {
            page.setAsc(pageQuery.getOrderByField());
        } else {
            page.setDesc(pageQuery.getOrderByField());
        }
        return page;
    }

    /**
     * 获取参数值，通过param或从requestBody中取
     *
     * @author fengshuonan
     * @Date 2018/7/25 下午1:12
     */
    private static String getFieldValue(HttpServletRequest request, String type) {

        // 先从requestParam中获取值，如果没有就从requestData中获取
        String parameterValue = getRequestParameter(request, type);
        if (parameterValue == null) {
            return getRequestData(type);
        } else {
            return parameterValue;
        }
    }

    /**
     * 获取请求参数的值（从param中获取）
     *
     * @author fengshuonan
     * @Date 2019-09-29 15:33
     */
    private static String getRequestParameter(HttpServletRequest request, String type) {
        if (PAGE_SIZE_PARAM_NAME.equals(type)) {
            Set<String> pageSizeFieldNames = PageFieldNamesContainer.getInstance().getPageSizeFieldNames();
            for (String fieldName : pageSizeFieldNames) {
                String pageSizeValue = request.getParameter(fieldName);
                if (ToolUtil.isNotEmpty(pageSizeValue)) {
                    return pageSizeValue;
                }
            }
            return null;
        } else if (PAGE_NO_PARAM_NAME.equals(type)) {
            Set<String> pageNoFieldNames = PageFieldNamesContainer.getInstance().getPageNoFieldNames();
            for (String fieldName : pageNoFieldNames) {
                String pageNoValue = request.getParameter(fieldName);
                if (ToolUtil.isNotEmpty(pageNoValue)) {
                    return pageNoValue;
                }
            }
            return null;
        } else if (SORT_PARAM_NAME.equals(type)) {
            Set<String> sortFieldNames = PageFieldNamesContainer.getInstance().getSortFieldNames();
            for (String fieldName : sortFieldNames) {
                String sortValue = request.getParameter(fieldName);
                if (ToolUtil.isNotEmpty(sortValue)) {
                    return sortValue;
                }
            }
            return null;
        } else if (ORDER_BY_PARAM_NAME.equals(type)) {
            Set<String> orderByFieldNames = PageFieldNamesContainer.getInstance().getOrderByFieldNames();
            for (String fieldName : orderByFieldNames) {
                String orderByValue = request.getParameter(fieldName);
                if (ToolUtil.isNotEmpty(orderByValue)) {
                    return orderByValue;
                }
            }
            return null;
        }
        return null;
    }

    /**
     * 获取请求参数的值（从requestData中获取）
     *
     * @author fengshuonan
     * @Date 2019-09-29 15:33
     */
    private static String getRequestData(String type) {
        if (PAGE_SIZE_PARAM_NAME.equals(type)) {
            Set<String> pageSizeFieldNames = PageFieldNamesContainer.getInstance().getPageSizeFieldNames();
            return getValueFromRequestData(pageSizeFieldNames);
        } else if (PAGE_NO_PARAM_NAME.equals(type)) {
            Set<String> pageNoFieldNames = PageFieldNamesContainer.getInstance().getPageNoFieldNames();
            return getValueFromRequestData(pageNoFieldNames);
        } else if (SORT_PARAM_NAME.equals(type)) {
            Set<String> sortFieldNames = PageFieldNamesContainer.getInstance().getSortFieldNames();
            return getValueFromRequestData(sortFieldNames);
        } else if (ORDER_BY_PARAM_NAME.equals(type)) {
            Set<String> orderByFieldNames = PageFieldNamesContainer.getInstance().getOrderByFieldNames();
            return getValueFromRequestData(orderByFieldNames);
        }
        return null;
    }

    /**
     * 从requestData中获取指定字段集合名称中某一个值
     *
     * @author fengshuonan
     * @Date 2019-09-29 15:40
     */
    private static String getValueFromRequestData(Set<String> fieldNames) {
        for (String fieldName : fieldNames) {
            RequestData requestData = RequestDataHolder.get();
            if (requestData == null) {
                return null;
            } else {
                Object fieldValue = requestData.get(fieldName);
                if (fieldValue != null) {
                    return fieldValue.toString();
                }
            }
        }
        return null;
    }
}
