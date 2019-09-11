package com.lingfly.tinyioc.beans.aop;

/*********************************
 * <p> 文件名称: com.lingfly.tinyioc.beans.aop.AbstractAopProxy
 * <p> 系统名称：
 * <p> 模块名称：AOP
 * <p> 功能说明: AOP接口的抽象类，Cglib和JDK代理的具体实现都继承这个类
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 2019/9/10
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public abstract class AbstractAopProxy implements AopProxy{
    protected AdvisedSupport advised;

    public AbstractAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }
}
