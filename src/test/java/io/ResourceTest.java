package io;

import com.lingfly.tinyioc.beans.io.Resource;
import com.lingfly.tinyioc.beans.io.ResourceLoader;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/*********************************
 * <p> 文件名称: 
 * <p> 系统名称：
 * <p> 模块名称：
 * <p> 功能说明: 
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class ResourceTest {


    @Test
    public void getResource()throws IOException {
        ResourceLoader resourceLoader=new ResourceLoader();
        Resource resource = resourceLoader.getResource("tinyioc.xml");
        Assert.assertNotNull(resource.getInputStream());
    }

}
