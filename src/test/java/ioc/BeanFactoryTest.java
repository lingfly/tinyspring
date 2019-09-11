package ioc;

import com.lingfly.tinyioc.beans.BeanDefinition;
import com.lingfly.tinyioc.beans.factory.AbstractBeanFactory;
import com.lingfly.tinyioc.beans.factory.AutowireCapableBeanFactory;
import com.lingfly.tinyioc.beans.factory.BeanFactory;
import com.lingfly.tinyioc.beans.io.ResourceLoader;
import com.lingfly.tinyioc.beans.xml.XmlBeanDefinitionReader;
import com.lingfly.tinyioc.entity.HelloWorldService;
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
public class BeanFactoryTest {
    @Test
    public void getBean() throws Exception{
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinitions("tinyioc.xml");

        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> entry: reader.getRegistry().entrySet()){
            beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }

        HelloWorldService helloWorld = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorld.helloWorld();
    }
}
