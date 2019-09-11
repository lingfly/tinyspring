package com.lingfly.tinyioc.beans.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.aopalliance.intercept.Interceptor;

import java.lang.reflect.Method;

/*********************************
 * <p> 文件名称: 
 * <p> 系统名称：
 * <p> 模块名称：
 * <p> 功能说明: 
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class Cglib2AopProxy extends AbstractAopProxy {

    public Cglib2AopProxy(AdvisedSupport advised) {
        super(advised);
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advised.getTargetSource().getTargetClass());
        enhancer.setInterfaces(advised.getTargetSource().getInterfaces());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        Object enhanced = enhancer.create();
        return enhanced;
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {

        private AdvisedSupport advised;

        private org.aopalliance.intercept.MethodInterceptor delegateMethodInterceptor;

        public DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
            this.delegateMethodInterceptor = advised.getMethodInterceptor();
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            if (advised.getMethodMatcher() == null ||
                    advised.getMethodMatcher().matches(method, advised.getTargetSource().getTargetClass())){
                return delegateMethodInterceptor.invoke(new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, args, proxy));
            }
            return new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, args, proxy).proceed();
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {
        MethodProxy methodProxy;
        public CglibMethodInvocation(Object target, Method method, Object[] args, MethodProxy methodProxy) {
            super(target, method, args);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(this.target, this.arguments);
        }
    }
}
