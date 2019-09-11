package bean;

import com.lingfly.tinyioc.beans.BeanDefinitionReader;
import com.lingfly.tinyioc.beans.BeanDefinition;
import com.lingfly.tinyioc.beans.PropertyValue;
import com.lingfly.tinyioc.beans.io.Resource;
import com.lingfly.tinyioc.beans.io.ResourceLoader;
import com.lingfly.tinyioc.beans.io.UrlResource;
import com.lingfly.tinyioc.beans.xml.XmlBeanDefinitionReader;
import org.junit.Test;

import java.util.Map;

/*********************************
 * <p> 文件名称: 
 * <p> 系统名称：
 * <p> 模块名称：
 * <p> 功能说明: 
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class BeanDefinitionTest {

    @Test
    public void getBeanDefinition() throws Exception{

        BeanDefinitionReader reader = new  XmlBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinitions("beans.xml");
        Map<String,BeanDefinition> registry = ((XmlBeanDefinitionReader) reader).getRegistry();
        for (String name : registry.keySet()){
            System.out.println(name+": "+registry.get(name));
        }
    }
    @Test
    public void getBeanReference() throws Exception{
        BeanDefinitionReader reader = new  XmlBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinitions("beans.xml");
        Map<String,BeanDefinition> registry = ((XmlBeanDefinitionReader) reader).getRegistry();
        for (String name : registry.keySet()){

            BeanDefinition definition=registry.get(name);
            System.out.println(name+": "+definition);
        }
    }
}
