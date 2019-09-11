package com.lingfly.tinyioc.beans;

/*********************************
 * <p> 文件名称: com.lingfly.tinyioc.beans.BeanPostProcessor
 * <p> 系统名称：
 * <p> 模块名称：IOC
 * <p> 功能说明:工厂钩子，允许自定义修改新的bean实例，例如 检查标记接口或用代理包装它们。
 *     ApplicationContexts可以在其bean定义中自动检测BeanPostProcessor bean，并将它们应用于随后创建的任何bean。
 *     普通BeanFactory允许对后处理器进行编程注册，适用于通过该工厂创建的所有bean。
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 2019/9/3
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean,String beanName) throws Exception;
    Object postProcessAfterInitialization(Object bean,String beanName) throws Exception;
}
