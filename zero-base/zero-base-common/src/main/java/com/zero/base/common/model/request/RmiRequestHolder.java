
package com.zero.base.common.model.request;

/**
 * 远程调用请求参数的holder
 *
 * @author fengshuonan
 * @Date 2019/5/14 22:59
 */
public class RmiRequestHolder {

    private static final ThreadLocal<AbstractBaseRequest> spanIdContext = new ThreadLocal<>();

    public static void set(AbstractBaseRequest spanId) {
        spanIdContext.set(spanId);
    }

    public static AbstractBaseRequest get() {
        return spanIdContext.get();
    }

    public static void remove() {
        spanIdContext.remove();
    }
}
