package com.heima;

import com.heima.pojo.PayBase;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

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
        String contentStr = "{\"orderAmount\":\"10.00\",\"orderNo\":\"20240310134003431MmV\",\"productCode\":\"BTPAY\",\"serverCallbackUrl\":\"http://pay.test4ldrms6f2.com:5222/pay/api/callback/XPAY243ZF/16915\",\"orderIp\":\"154.18.168.71\",\"goodsName\":\"老万\",\"userId\":\"98166119\",\"merchantNo\":\"MEMM000513VY\"}";

        System.out.println("contentStr:  "+ contentStr);

        String base64EncodedString = encodeBase64(contentStr.getBytes());
    }

    /**
     * BASE64加密
     */
    public static String encodeBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
    /**
     * AES加密
     */
    public static byte[] encryptAES(byte[] data, byte[] key) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }

    public static String md5Password(String password) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}
