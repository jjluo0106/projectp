package com.heima.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heima.pojo.CallbackBean;
import com.heima.pojo.PayBase;
import com.heima.pojo.Result;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付流程测试
 */
@RestController
@Slf4j
public class PayTestController {


    @Autowired
    PayBase payBase;
    String domainAddr = "9bea-154-18-168-71.ngrok-free.app"; //ngrok 臨時域名
    String merchantCode = "1234";
    String key = "abcd";
    String callBackSuffix = "/callBack";
    String status = "666";
    private static HttpClient httpClient = null;

    /**
     * 接收下單**************************************
     */
    @PostMapping("/placeAnOrder")
    public Result placeAnOrder(HttpServletRequest request) throws IOException {


        String inputStream = null;
        try {
            inputStream = getInputStream(request); // 此法无法顺利解析 curl请求
        } catch (Exception e) {
            throw new RuntimeException("输入流读取错误");
        }

        String contentType = request.getContentType();
        System.out.println("接獲下單接口請求...");
        System.out.println("contentType : " + contentType);
        System.out.println("获取数据 : " + inputStream);
        JSONObject jsonInput = new JSONObject(inputStream);
        if (contentType.contains("form")) {
            System.out.println("form请求");
        }
        if (contentType.contains("json")) {
            System.out.println("json请求");
        }
        dealRequest(inputStream, jsonInput);

        System.out.println("处里完下单接口后的payBase对象 : " + payBase);

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("url", "https://" + domainAddr + callBackSuffix);
        //返回跳轉地址(這邊直接調用回調接口)
        // {"status":"success","code":200,"message":"Request successful","data":{"url":"https://408d-154-18-168-67.ngrok-free.app/callBack"}}
        return Result.success(responseMap);
    }

    private void dealRequest(String inputStream, JSONObject jsonInput) {
        System.out.println("原始数据 : " + inputStream);

        String returnUrl = jsonInput.get("returnUrl").toString();
        System.out.println("下單接口獲取回調地址參數 returnUrl : " + returnUrl);

        String platformOrderNOQ = jsonInput.get("platformOrderNOQ").toString();
        System.out.println("下單接口獲取編碼 platformOrderNOQ : " + platformOrderNOQ);

        String amountQ = jsonInput.get("amountQ").toString();
        System.out.println("下單接口獲取金額 amountQ : " + amountQ);

        System.out.println("將請求參數儲存在payBase...");
        payBase.setCallBackUrl(returnUrl);
        payBase.setPlatformCode(platformOrderNOQ);
        payBase.setAmountQ(amountQ);
    }

    /**
     * 處理回調"get"請求************************************
     * 用拉起的頁面模擬回調，所以顯示在頁面的響應結果
     */
    @GetMapping("/callBack")
    public Result callBackSuffix(HttpServletRequest request) throws IOException, InterruptedException {
//        Thread.sleep(1000);

        String callBackUrl = payBase.getCallBackUrl();


        System.out.println("接獲回調接口請求...");
        System.out.println("讀取預存payBase的回調地址数据 : " + callBackUrl);

        CallbackBean bean = new CallbackBean();

        bean.setMerchantCodeB(merchantCode); //商戶號
        bean.setResult_code(status); // 成功狀態碼
        bean.setPlatformOrderNOB(payBase.getPlatformCode());
        bean.setAmountB(payBase.getAmountQ());

        String notencryptionStr ="amountB=" + bean.getAmountB() +
                                 "&merchantCodeB=" + bean.getMerchantCodeB() +
                                 "&platformOrderNOB=" + bean.getPlatformOrderNOB() +
                                 "&result_code=" + bean.getResult_code() +
                                 "&key=" + key;
        System.out.println("加密前字串 : " + notencryptionStr);
        String md5 = getMD5(notencryptionStr);
        System.out.println("加密後sign : " + md5);
        bean.setSignB(md5);


        StringBuffer response = dealRequest(callBackUrl, bean);

        System.out.println("响应数据 : " + response);


        if("GetCallBackSuccess".equals(response)){
            return Result.success("成功收到返回顯示"); // 響應
        }
        return Result.error("出錯");
    }

    /**
     * 查詢接口*************************************
     */
    @PostMapping("/query")
    public Result getQueryRequest(HttpServletRequest request){
        System.out.println("收到查詢請求...");

        String inputStream = null;
        try {
            inputStream = getInputStream(request);
        } catch (Exception e) {
            throw new RuntimeException("输入流读取错误");
        }
        System.out.println("接收數據 :\n" + inputStream);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("statusQ","1");

        return Result.success("測試中",map);
    }


    private static StringBuffer dealRequest(String callBackUrl, CallbackBean bean) throws IOException {
        URL url = new URL(callBackUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());

        ObjectMapper mapper = new ObjectMapper();


        String callBackBeanStr = mapper.writeValueAsString(bean); // 将bean对象转换成json格式的str
        System.out.println("回調請求參數 : " + callBackBeanStr);

        StringBuffer response = null;
        try {
            out.writeBytes(callBackBeanStr); // 回調數據
            out.flush();
            out.close();
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e + "\n组装回调响应失败!!");
        }
        return response;
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
            throw new Exception("回調数据流出现异常！");
        } finally {
            reader.close();
        }
        return sb.toString();
    }



    public static String getMD5(String input) {
        try {
            // 创建 MessageDigest 实例，指定使用 MD5 算法
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算消息摘要
            byte[] messageDigest = md.digest(input.getBytes());
            // 将字节数组转换为十六进制数
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
