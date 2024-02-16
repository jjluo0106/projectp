package com.heima.projectp;

import com.heima.projectp.controller.DeptController;
import com.heima.projectp.pojo.PayBase;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootTest
public class testBean {

    @Autowired
    ApplicationContext context;
    @Autowired
    SAXReader saxReader;
    @Autowired
    PayBase paybase;



    @Test
    public void test1() throws DocumentException {
//        SAXReader saxReader = new SAXReader();
        Document document = null;

            document = saxReader.read(this.getClass().getClassLoader().getResource("1.xml"));

        Element rootElement = document.getRootElement();
        String name = rootElement.element("name").getText();
        String age = rootElement.element("age").getText();

        System.out.println(name + " : " + age);
    }

    @Test
    public void testScope() {

        for (int i = 0; i < 10; i++) {
        Object bean = context.getBean("reader");
        System.out.println("打印 : " + bean);
        }
    }

    @Test
    public void testBean(){
        String[] arr = {"a","b"};

        System.out.println(Arrays.toString(arr));
    }

}
