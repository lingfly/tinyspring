package com.lingfly.tinyioc.beans.context;

import com.lingfly.tinyioc.beans.BeanPostProcessor;
import com.lingfly.tinyioc.beans.factory.AbstractBeanFactory;
import com.lingfly.tinyioc.beans.factory.BeanFactory;

import java.util.List;

/*********************************
 * <p> 文件名称: com.lingfly.tinyioc.beans.context.AbstractApplicationContext
 * <p> 系统名称：
 * <p> 模块名称：IOC
 * <p> 功能说明: ApplicationContext的抽象类，bean的加载方法留给子类实现
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 2019/9/6
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public abstract class AbstractApplicationContext implements ApplicationContext {
    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 1. 加载bean定义
     * 2. 注册BeanPostProcessor，在onRefresh调用之前，确保实例化时处理器已经注册
     * 3. 实例化所有单例且非懒加载的bean
     */
    public void refresh() throws Exception{
        loadBeanDefinitions(beanFactory);
        registerBeanPostProcessor(beanFactory);
        onRefresh();
    }

    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

    protected void registerBeanPostProcessor(AbstractBeanFactory beanFactory) throws Exception{
        List<BeanPostProcessor> postProcessors = beanFactory.getBeanForType(BeanPostProcessor.class);
        for (BeanPostProcessor postProcessor : postProcessors){
            beanFactory.addBeanPostProcessor(postProcessor);
        }
    }

    protected void onRefresh() throws Exception{
        beanFactory.preInstanceSingletons();
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }
}
