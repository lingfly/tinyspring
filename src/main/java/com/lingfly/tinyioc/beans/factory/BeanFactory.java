package com.lingfly.tinyioc.beans.factory;

/*********************************
 * <p> 文件名称: com.lingfly.tinyioc.beans.factory.BeanFactory
 * <p> 系统名称：
 * <p> 模块名称：IOC
 * <p> 功能说明: BeanFactory是访问IOC容器的接口，由包含许多bean定义的对象实现，bean由String类型的name唯一标识
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间：2019/9/3
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public interface BeanFactory {
    Object getBean(String name) throws Exception;
}
