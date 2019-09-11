package com.lingfly.tinyioc.beans.context;

import com.lingfly.tinyioc.beans.BeanDefinition;
import com.lingfly.tinyioc.beans.factory.AbstractBeanFactory;
import com.lingfly.tinyioc.beans.factory.AutowireCapableBeanFactory;
import com.lingfly.tinyioc.beans.factory.BeanFactory;
import com.lingfly.tinyioc.beans.io.ResourceLoader;
import com.lingfly.tinyioc.beans.xml.XmlBeanDefinitionReader;

import java.util.Map;

/*********************************
 * <p> 文件名称: com.lingfly.tinyioc.beans.context.ClassPathXmlApplicationContext
 * <p> 系统名称：
 * <p> 模块名称：IOC
 * <p> 功能说明: 实现由类路径下的xml文件获取bean
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 2019/9/6
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{
    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception{
        this(configLocation, new AutowireCapableBeanFactory());
    }
    //ApplicationContext在初始化容器时就实例化所有bean
    private ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception{
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinitions(configLocation);
        for (Map.Entry<String, BeanDefinition> entry : reader.getRegistry().entrySet()){
            beanFactory.registerBeanDefinition(entry.getKey(),entry.getValue());
        }
    }
}
