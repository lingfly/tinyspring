package com.lingfly.tinyioc.beans.factory;

import com.lingfly.tinyioc.beans.BeanDefinition;
import com.lingfly.tinyioc.beans.BeanPostProcessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*********************************
 * <p> 文件名称: 
 * <p> 系统名称：
 * <p> 模块名称：
 * <p> 功能说明: 
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public abstract class AbstractBeanFactory implements BeanFactory{

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private final List<String> beanDefinitionNames = new ArrayList<>();

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    //获取bean
    @Override
    public Object getBean(String name) throws Exception{
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);

        if (beanDefinition == null){
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        Object bean = beanDefinition.getBean();
        if (bean == null){
            bean = doCreateBean(beanDefinition);
            bean = initializeBean(bean,name);
            beanDefinition.setBean(bean);
        }
        return bean;
    }
    //创建bean
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception{
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        applyPropertyValues(bean,beanDefinition);
        return bean;

    }
    //创建bean实例
    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception{
        return beanDefinition.getBeanClass().newInstance();
    }
    /**
     * 初始化bean，如果容器中有BeanPostProcessor，那么在初始化时会调用BeanPostProcessor的两个方法
     * 正是在 postProcessorAfterInitialization 方法中，使用动态代理的方式，返回一个对象的代理对象
     * 实现AOP的植入
    */
    protected Object initializeBean(Object bean, String name) throws Exception{
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors){
            bean = beanPostProcessor.postProcessBeforeInitialization(bean,name);
        }
        // TODO:call initialize method
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
        }
        return bean;
    }
    //给bean属性赋值，留给子类实现
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception{

    }
    //添加BeanPostProcessor
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        beanPostProcessors.add(beanPostProcessor);
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception{
        beanDefinitionNames.add(name);
        beanDefinitionMap.put(name, beanDefinition);
    }
    public void preInstanceSingletons() throws Exception{
        for (Iterator it=beanDefinitionNames.iterator(); it.hasNext();){
            String name = (String) it.next();
            getBean(name);
        }
    }

    public List getBeanForType(Class type) throws Exception{
        List beans = new ArrayList<>();
        for (String name : beanDefinitionNames){
            if (beanDefinitionMap.get(name).getBeanClass() == type){
                beans.add(getBean(name));
            }
        }
        return beans;
    }
}
