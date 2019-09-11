package com.lingfly.tinyioc.entity;


import com.lingfly.tinyioc.beans.aop.BeanFactoryAware;
import com.lingfly.tinyioc.beans.factory.BeanFactory;

/**
 * @author yihua.huang@dianping.com
 */
public class HelloWorldServiceImpl implements HelloWorldService {

    private String text;

    private OutputService outputService;

    private BeanFactory beanFactory;

    @Override
    public void helloWorld(){
        outputService.output(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }

}
