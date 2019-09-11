package com.lingfly.tinyioc.beans.io;

import java.io.IOException;
import java.io.InputStream;

/*********************************
 * <p> 文件名称: com.lingfly.tinyioc.beans.io.Resource
 * <p> 系统名称：
 * <p> 模块名称：IOC
 * <p> 功能说明: Resource是Spring定位资源的接口
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间：2019/9/3
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public interface Resource {
    InputStream getInputStream() throws IOException;
}
