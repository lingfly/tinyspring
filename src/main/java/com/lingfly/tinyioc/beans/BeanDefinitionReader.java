package com.lingfly.tinyioc.beans;

/*********************************
 * <p> 文件名称: com.lingfly.tinyioc.beans.BeanDefinitionReader
 * <p> 系统名称：
 * <p> 模块名称：IOC
 * <p> 功能说明: 用于读取bean定义的接口
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 2019/9/3
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws Exception;
}
