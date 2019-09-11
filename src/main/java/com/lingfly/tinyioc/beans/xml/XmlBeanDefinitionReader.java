package com.lingfly.tinyioc.beans.xml;

import com.lingfly.tinyioc.BeanReference;
import com.lingfly.tinyioc.beans.AbstractBeanDefinitionReader;

import com.lingfly.tinyioc.beans.BeanDefinition;
import com.lingfly.tinyioc.beans.PropertyValue;
import com.lingfly.tinyioc.beans.io.Resource;
import com.lingfly.tinyioc.beans.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;

/*********************************
 * <p> 文件名称: com.lingfly.tinyioc.beans.xml.XmlBeanDefinitionReader
 * <p> 系统名称：
 * <p> 模块名称：IOC
 * <p> 功能说明: 实现由类路径下的xml文件读取bean定义
 * <p> 开发人员：zhengzhongwei0@gmail.com
 * <p> 开发时间: 2019/9/3
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    //由location路径打开一个输入流
    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        Resource resource = this.getResourceLoader().getResource(location);
        InputStream inputStream = resource.getInputStream();
        doLoadBeanDefinitions(inputStream);
    }
    //由输入流获取Document对象，一个Document对象代表一个xml文件
    private void doLoadBeanDefinitions(InputStream inputStream)throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document document = docBuilder.parse(inputStream);
        registryBeanDefinitions(document);

        inputStream.close();
    }

    //获取xml文件的子节点，spring-xml里第一个子节点是beans，所有bean都在这里定义
    private void registryBeanDefinitions(Document document){
        Element root = document.getDocumentElement();
        parseBeanDefinition(root);
    }
    //解析文档中根级别的元素：“import”，“alias”，“bean”
    private void parseBeanDefinition(Element root){
        NodeList nodeList = root.getChildNodes();
        for (int i=0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if (node instanceof Element){
                Element element = (Element) node;
                processBeanDefinition(element);
            }

        }
    }

    //获取bean的名字和类型
    private void processBeanDefinition(Element element){
        String name = element.getAttribute("id");
        String className = element.getAttribute("class");
        BeanDefinition BeanDefinition = new BeanDefinition();
        BeanDefinition.setBeanClassName(className);

        processProperty(element,BeanDefinition);
        getRegistry().put(name,BeanDefinition);

    }
    //处理bean的属性，如果属性值是引用，把引用bean的信息封装到BeanReference
    private void processProperty(Element element, BeanDefinition BeanDefinition){
        NodeList properties = element.getElementsByTagName("property");
        for (int i = 0; i < properties.getLength(); i++){
            Node node = properties.item(i);
            if (node instanceof Element){
                Element property = (Element) node;
                String name = property.getAttribute("name");
                String value = property.getAttribute("value");
                if (value != null && value.length() > 0){
                    BeanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,value));
                } else {
                    String ref = property.getAttribute("ref");
                    if (ref == null || ref.length() == 0){
                        throw new RuntimeException("引用为空");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    BeanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,beanReference));
                }
            }
        }
    }

}
