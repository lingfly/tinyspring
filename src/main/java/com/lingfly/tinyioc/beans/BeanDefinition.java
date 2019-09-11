package com.lingfly.tinyioc.beans;

/*********************************
 * <p> 文件名称: com.lingfly.tinyioc.beans.BeanDefinition
 * <p> 系统名称：
 * <p> 模块名称：
 * <p> 功能说明: 保存bean的定义
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 2019/9/3
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class BeanDefinition {

    private String beanClassName;

    private Class beanClass;

    private Object bean;

    private PropertyValues propertyValues = new PropertyValues();

    public BeanDefinition() {
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    //方便测试用
    @Override
    public String toString() {
        String s= "BeanDefinition{" +
                "beanClassName='" + beanClassName + '\'' +
                ", beanClass=" + beanClass +
                ", bean=" + bean +"\n";

        for (PropertyValue propertyValue : propertyValues.getPropertyValueList()){
            s+=propertyValue.getName()+": "+propertyValue.getValue()+" ";
        }
        s+='}';
        return s;
    }
}
