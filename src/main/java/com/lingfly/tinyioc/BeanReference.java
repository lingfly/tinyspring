package com.lingfly.tinyioc;

/*********************************
 * <p> 文件名称: com.lingfly.tinyioc.BeanReference
 * <p> 系统名称：
 * <p> 模块名称：IOC
 * <p> 功能说明: 用于保存引用属性的信息
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 2019/9/3
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class BeanReference {

    private String name;

    private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
