package com.lingfly.tinyioc.beans.factory;

import com.lingfly.tinyioc.BeanReference;
import com.lingfly.tinyioc.beans.BeanDefinition;
import com.lingfly.tinyioc.beans.PropertyValue;
import com.lingfly.tinyioc.beans.aop.BeanFactoryAware;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*********************************
 * <p> 文件名称: com.lingfly.tinyioc.beans.factory.AutowireCapableBeanFactory
 * <p> 系统名称：
 * <p> 模块名称：IOC
 * <p> 功能说明: 通过动态代理自动识别bean中的setter方法去设置属性
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 2019/9/3
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception{
        if (bean instanceof BeanFactoryAware){
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }

        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()){
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference){
                BeanReference reference = (BeanReference) value;
                value = getBean(reference.getName());
            }

            try {
                Method declaredMethod = bean.getClass().getDeclaredMethod(
                        "set" + propertyValue.getName().substring(0,1).toUpperCase()
                        + propertyValue.getName().substring(1),value.getClass());

                declaredMethod.setAccessible(true);
                declaredMethod.invoke(bean,value);
            } catch (NoSuchMethodException e){
                //如果没有setter方法，则直接对字段设置
                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean,value);
            }
        }
    }
}
