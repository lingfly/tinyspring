package com.lingfly.tinyioc.beans.aop;

import org.aopalliance.aop.Advice;

/*********************************
 * <p> 文件名称: com.lingfly.tinyioc.beans.aop.Advisor
 * <p> 系统名称：
 * <p> 模块名称：AOP
 * <p> 功能说明: 
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 2019/9/10
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public interface Advisor {
    Advice getAdvice();
}
