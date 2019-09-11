package com.lingfly.tinyioc.beans.io;

import java.net.URL;

/*********************************
 * <p> 文件名称: com.lingfly.tinyioc.beans.io.ResourceLoader
 * <p> 系统名称：
 * <p> 模块名称：IOC
 * <p> 功能说明: 根据文件路径返回资源
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 2019/9/3
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class ResourceLoader {
    public Resource getResource(String location){
        URL url = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(url);
    }
}
