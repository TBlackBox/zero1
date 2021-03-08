
package com.zero.base.common.validator.aop;


import com.zero.base.common.model.constants.AopSortConstants;
import com.zero.base.common.validator.util.CheckUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

/**
 * 参数校验的aop
 *
 * @author fngshuonan
 * @date 2018-08-07-上午9:53
 */
@Aspect
@Order(AopSortConstants.PARAM_VALIDATE_AOP_SORT)
public class ParamValidateAop {

    @Pointcut(value = "@annotation(com.zero.base.common.validator.stereotype.ParamValidator)")
    private void cutService() {

    }

    @Around("cutService()")
    public Object doInvoke(ProceedingJoinPoint point) throws Throwable {

        //获取拦截方法的参数
        Object[] methodParams = point.getArgs();

        //如果请求参数为空，直接跳过aop
        if (methodParams == null || methodParams.length <= 0) {
            return point.proceed();
        } else {

            //如果参数中，包含BaseValidatingParam的子类就开始校验参数
            CheckUtil.validateParameters(methodParams);
            return point.proceed();
        }
    }
}
