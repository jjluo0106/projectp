package com.heima.projectp.controller;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@RestController
@Slf4j
public class TestController {


    @RequestMapping("/test")
    public void testGetRequest(HttpServletRequest request) throws Exception {
        log.info("測試");
        String method = request.getMethod();
        String contentType = request.getContentType();
        log.info("請求方法 : {}", method);
        log.info("請求格式 : {}", contentType);
        if(contentType.contains("form"))  log.info("是FORM表單格式!");

        String inputStream = getInputStream(request);


        Map<String, String> stringStringMap = parseQueryString(inputStream);
        Set<Map.Entry<String, String>> entries = stringStringMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();

    }


    public static Map<String, String> parseQueryString(String queryString) {
        // 创建 Map 用于存储解析后的键值对
        Map<String, String> params = new HashMap<>();

        // 分割请求字符串，获取键值对数组
        String[] keyValuePairs = queryString.split("&");

        // 使用 Iterator 遍历键值对数组
        for (String keyValuePair : keyValuePairs) {
            // 分割键值对，获取键和值
            String[] pair = keyValuePair.split("=");

            // 确保键值对格式正确
            if (pair.length == 2) {
                String key = pair[0];
                String value = pair[1];

                // 存储键值对到 Map
                params.put(key, value);
            }
        }

        return params;
    }


    public static String getInputStream(HttpServletRequest request) throws Exception {
        ServletInputStream stream = null;
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            stream = request.getInputStream();
            //获取响应
            reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.error(String.valueOf(e));
            throw new Exception("读取返回支付接口数据流出现异常！");
        } finally {
            reader.close();
        }
        log.info("输入流返回的内容：" + sb.toString());
        return sb.toString();
    }
}
