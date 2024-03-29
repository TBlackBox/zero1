
package com.zero.base.common.model.auth.context;


import com.zero.base.common.model.auth.AbstractLoginUser;

/**
 * <pre>
 * 当前登录用户的临时保存容器
 *
 *  说明：
 *     当OPEN_UP_FLAG标识在ThreadLocal里为true
 * </pre>
 *
 * @author fengshuonan
 * @Date 2018/7/3 下午5:29
 */
public class LoginUserHolder {

    private static final ThreadLocal<Boolean> OPEN_UP_FLAG = new ThreadLocal<>();
    private static final ThreadLocal<AbstractLoginUser> LONGIN_USER_HOLDER = new ThreadLocal<>();

    /**
     * 初始化
     *
     * @author fengshuonan
     * @Date 2018/7/4 下午12:31
     */
    public static void init() {
        OPEN_UP_FLAG.set(true);
    }

    /**
     * 这个方法如果OPEN_UP_FLAG标识没开启，则会set失效
     *
     * @author fengshuonan
     * @Date 2018/7/4 上午11:09
     */
    public static void set(AbstractLoginUser abstractLoginUser) {
        Boolean openUpFlag = OPEN_UP_FLAG.get();
        if (openUpFlag == null || openUpFlag.equals(false)) {
            return;
        } else {
            LONGIN_USER_HOLDER.set(abstractLoginUser);
        }
    }

    /**
     * 这个方法如果OPEN_UP_FLAG标识没开启，则会get值为null
     *
     * @author fengshuonan
     * @Date 2018/7/4 上午11:09
     */
    public static AbstractLoginUser get() {
        Boolean openUpFlag = OPEN_UP_FLAG.get();
        if (openUpFlag == null || openUpFlag.equals(false)) {
            return null;
        } else {
            return LONGIN_USER_HOLDER.get();
        }
    }

    /**
     * 删除保存的用户
     *
     * @author fengshuonan
     * @Date 2018/7/24 下午3:28
     */
    public static void remove() {
        OPEN_UP_FLAG.remove();
        LONGIN_USER_HOLDER.remove();
    }
}
