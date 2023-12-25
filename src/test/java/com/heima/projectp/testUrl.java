package com.heima.projectp;
import com.heima.projectp.mapper.EmpMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class testUrl {



    @Test
    public void requestRUL(){
        try {
            // 目標網址
            String url = "http://www.taiwantestcentral.com/Toolbox.aspx?MainCategoryID=25";

            // 建立 URL 對象
            URL obj = new URL(url);

            // 建立 HttpURLConnection 對象
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            // 設置請求方法為 GET
            connection.setRequestMethod("GET");

            // 取得 HTTP 狀態碼
            int responseCode = connection.getResponseCode();
            System.out.println("HTTP Response Code: " + responseCode);

            // 讀取網頁內容
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // 關閉 BufferedReader
            reader.close();

            // 使用 Jsoup 解析 HTML 內容
            Document document = Jsoup.parse(response.toString());

            // 現在你可以使用 Jsoup 提供的方法來解析 HTML 內容
            // 例如，獲取標題
            String title = document.title();
            System.out.println("HTML Title: " + title);


            // 輸出網頁內容
            System.out.println("HTTP Response:\n" + document.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public static void main(String[] args) {
        try {
            // 目標網址
            String url = "http://www.taiwantestcentral.com/Toolbox.aspx?MainCategoryID=25";

            // 建立 URL 對象
            URL obj = new URL(url);

            // 建立 HttpURLConnection 對象
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            // 設置請求方法為 POST
            connection.setRequestMethod("POST");

            // 啟用輸出流，以便提交數據
            connection.setDoOutput(true);

            // 要提交的英文內容
            String englishContent = "apple";

            // 將英文內容進行 URL 編碼
            String encodedContent = URLEncoder.encode(englishContent, "UTF-8");

            // 構建請求參數
            String postData = "ctl00$MainContent$InputBox=" + encodedContent + "&ctl00$MainContent$OKButton=OK";

            // 獲取輸出流，並寫入數據
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.writeBytes(postData);
                wr.flush();
            }

            // 取得 HTTP 狀態碼
            int responseCode = connection.getResponseCode();
            System.out.println("HTTP Response Code: " + responseCode);

            // 讀取網頁內容
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // 關閉 BufferedReader
            reader.close();


            Document parse = Jsoup.parse(response.toString());
            // 輸出網頁內容
            System.out.println("HTTP Response:\n" + parse.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
