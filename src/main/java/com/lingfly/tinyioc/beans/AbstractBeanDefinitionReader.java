package com.lingfly.tinyioc.beans;

import com.lingfly.tinyioc.beans.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/*********************************
 * <p> 文件名称: com.lingfly.tinyioc.beans.AbstractBeanDefinitionReader
 * <p> 系统名称：
 * <p> 模块名称：IOC
 * <p> 功能说明: 用于读取bean定义的抽象类，具体获取方式由子类实现
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 2019/9/3
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private Map<String, BeanDefinition> registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
