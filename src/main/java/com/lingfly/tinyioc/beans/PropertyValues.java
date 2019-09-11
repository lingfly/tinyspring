package com.lingfly.tinyioc.beans;

import java.util.ArrayList;
import java.util.List;

/*********************************
 * <p> 文件名称: com.lingfly.tinyioc.beans.PropertyValues
 * <p> 系统名称：
 * <p> 模块名称：
 * <p> 功能说明: 用链表保存一个bean的所有属性
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 2019/9/3
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public PropertyValues() {
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }
    public void addPropertyValue(PropertyValue propertyValue){
        propertyValueList.add(propertyValue);
    }
}
