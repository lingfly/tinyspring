package com.lingfly.tinyioc.beans;

/*********************************
 * <p> 文件名称: com.lingfly.tinyioc.beans.PropertyValue
 * <p> 系统名称：
 * <p> 模块名称：
 * <p> 功能说明: 保存bean的属性信息，value可以是对象也可以是引用
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 2019/9/3
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class PropertyValue {

    private String name;

    private Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
