package com.lingfly.tinyioc.beans.aop;

import org.aopalliance.aop.Advice;

/*********************************
 * <p> 文件名称: 
 * <p> 系统名称：
 * <p> 模块名称：
 * <p> 功能说明: 
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    private Advice advice;

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression){

    }
}
