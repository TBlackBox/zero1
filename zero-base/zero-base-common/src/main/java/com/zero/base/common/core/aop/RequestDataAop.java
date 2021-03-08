
package com.zero.base.common.core.aop;

import com.zero.base.common.core.context.RequestDataHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import static com.zero.base.common.model.constants.AopSortConstants.REQUEST_DATA_AOP_SORT;

/**
 * 对控制器调用过程中,提供一种RequestData便捷调用的aop
 * <p>
 * 废弃掉了，因为requestDataConverter和FastjsonConverter都可以存RequestData
 *
 * @author fengshuonan
 * @date 2016年11月13日 下午10:15:42
 */
@Aspect
@Order(REQUEST_DATA_AOP_SORT)
public class RequestDataAop {

    @Pointcut("execution(* *..controller.*.*(..))")
    public void cutService() {
    }

    @Around("cutService()")
    public Object sessionKit(ProceedingJoinPoint point) throws Throwable {
        Object result;
        try {
            result = point.proceed();
        } finally {
            //清空 RequestDataHolder
            RequestDataHolder.remove();
        }
        return result;
    }
}
