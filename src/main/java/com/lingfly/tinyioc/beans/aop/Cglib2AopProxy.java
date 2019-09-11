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
 * <p> 开发时间: 2019/9/10
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class Cglib2AopProxy extends AbstractAopProxy {

    public Cglib2AopProxy(AdvisedSupport advised) {
        super(advised);
    }

    @Override
    public Object getProxy() {
        //设置Cglic代理信息
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advised.getTargetSource().getTargetClass());
        enhancer.setInterfaces(advised.getTargetSource().getInterfaces());
        //callback设置为MethodInterceptor定制代理内容
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
            /**
             * 如果bean是目标类，则调用org.aopalliance.intercept.MethodInterceptor的invoke方法
             * 参数是MethodInvocation对象，包含了目标类，目标方法，入参等信息
             * 当使用AOP时，实现类只需实现MethodInvocation接口，并在invoke方法执行相应横切逻辑并调用目标方法
             * 而该方法不是需要AOP植入的方法，则使用反射机制java.lang.reflect.MethodMethodInvocation.proceed调用原始方法
             * */
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
