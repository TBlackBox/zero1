
package com.zero.base.common.core.context;


import com.zero.base.common.core.request.RequestData;

/**
 * 请求数据的临时容器
 *
 * @author fengshuonan
 * @date 2018-05-04 11:33
 */
public class RequestDataHolder {

    private static ThreadLocal<RequestData> holder = new ThreadLocal<>();

    public static void put(RequestData s) {
        if (holder.get() == null) {
            holder.set(s);
        }
    }

    public static RequestData get() {
        return holder.get();
    }

    public static void remove() {
        holder.remove();
    }
}
