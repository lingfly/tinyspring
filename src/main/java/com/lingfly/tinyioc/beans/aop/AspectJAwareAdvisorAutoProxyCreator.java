package com.lingfly.tinyioc.beans.aop;

import com.lingfly.tinyioc.beans.BeanPostProcessor;
import com.lingfly.tinyioc.beans.factory.AbstractBeanFactory;
import com.lingfly.tinyioc.beans.factory.BeanFactory;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

/*********************************
 * <p> 文件名称: 
 * <p> 系统名称：
 * <p> 模块名称：
 * <p> 功能说明: 这是一个AOP核心类，实现AOP注入的两大接口，其中BeanPostProcessor的postProcessAfterInitialization
 *              判断当前bean是否被代理，如果是则封装被代理对象的信息，生成一个代理类并替换掉原始类返回
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 2019/9/10
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class AspectJAwareAdvisorAutoProxyCreator implements BeanFactoryAware, BeanPostProcessor {

    private AbstractBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {

        if (bean instanceof AspectJExpressionPointcutAdvisor){
            return bean;
        }

        if (bean instanceof MethodInterceptor){
            return bean;
        }

        List<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeanForType(AspectJExpressionPointcutAdvisor.class);
        for (AspectJExpressionPointcutAdvisor advisor : advisors){
            if (advisor.getPointcut().getClassFilter().matches(bean.getClass())){
                ProxyFactory advisedSupport = new ProxyFactory();
                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
                TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
                advisedSupport.setTargetSource(targetSource);
                return advisedSupport.getProxy();
            }
        }
        return bean;

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.beanFactory = (AbstractBeanFactory) beanFactory;
    }
}
