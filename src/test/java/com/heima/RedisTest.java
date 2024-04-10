package com.heima;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class RedisTest {

    private Jedis jedis;

    @BeforeEach
    void setUp(){
        //1. 建立連接
        jedis = new Jedis("192.168.88.88",6379);
        //2. 設置密碼
        jedis.auth("1234");
        //2. 選擇庫
        jedis.select(0);
        System.out.println("配置jedis");
    }

    @Test
    void testString(){
        String result  = jedis.set("testJson2","{\"key\":\"value\",\"key2\":\"value2\",\"key3\":\"value3\"}");
//        String result  = jedis.get("keyName");
        System.out.println("獲取結果 : " + result);

    }

    @AfterEach
    void tearDown() {
        if(jedis != null){
            jedis.close();
        }
        System.out.println("關閉jedis");
    }

}
