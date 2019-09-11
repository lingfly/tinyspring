package com.lingfly.tinyioc.beans.aop;

import com.lingfly.tinyioc.beans.factory.BeanFactory;

/*********************************
 * <p> 文件名称: com.lingfly.tinyioc.beans.aop.BeanFactoryAware
 * <p> 系统名称：
 * <p> 模块名称：IOC
 * <p> 功能说明: 提供对BeanFactory的感知，实现这个接口可以获取BeanFactory的引用，进而获取容器中的切点对象
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 2019/9/6
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public interface BeanFactoryAware {
    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
