package com.heima;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;


import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Date;
import java.util.HashMap;

//@SpringBootTest
class testClass {
//    @Autowired
//    EmpMapper empMapper;


    @Test
    void contextLoads() {
        System.out.println("测试");
    }


//    @Test
//    public void selectAll(){
//        empMapper.EmpSelectAll();
//    123334444444444444444444444444444444444444444444444444444445496   221}


    @Test
    public void test() throws JsonProcessingException {
        String json = "{\"payChannelName\":\"宾利支付_9030\",\"payDefaultAmount\":\"100,300,1000\",\"enterSelf\":\"1\",\"payChannelId\":1016991519,\"isEnable\":\"1\",\"isRecommend\":\"0\",\"discountProportion\":null,\"discountDamaMultiple\":null,\"maxDiscountAmount\":null,\"orderNum\":10,\"minAmount\":\"10\",\"maxAmount\":\"5000\",\"payChannelDesc\":\"宾利支付_9030\",\"remark\":\"\",\"memberLevelIds\":[1,2,3,4,5,22,23,24,25,26,27,28,29,30,33,35,37,38,39,41,42,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,71,72,73,74,75,76,77,78,79],\"memberVipIds\":[\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\",\"10\",\"15\",\"16\",\"29\",\"30\",\"31\",\"58\",\"59\",\"60\",\"64\",\"73\",\"74\"],\"device\":[\"1\",\"2\",\"3\",\"4\"],\"payTypeId\":2}";

        // 使用Jackson库中的ObjectMapper进行转换
        ObjectMapper objectMapper = new ObjectMapper();
        Object obj = objectMapper.readValue(json, Object.class);
        String convertedJson = objectMapper.writeValueAsString(obj);

        // 转换后的JSON输出
        System.out.println(convertedJson);
    }

    @Test
    public void ttest() {

        String s = "0.1.2.3.4.5.6.7.8.9";

        int i = s.lastIndexOf(".");

        System.out.println(s.substring(i));

    }

//令牌加密
    @Test
    public void testJwt() {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "tom");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "secretKey123")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
        System.out.println(jwt);
    }
//令牌解密
    @Test
    public void parseJwt() {
        Claims body = Jwts.parser().
                setSigningKey("heima").
                parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6InBhc3N3b3JkIiwibmFtZSI6IlRvbTIyIiwiZXhwIjoxNzAyMjEzMjQ3fQ.OTdNatdGrlZTmiXACDBoFMtdjXuFnMXpqvjGLcrDHtI")
                .getBody();

        System.out.println(body);
        System.out.println("現在時間戳 :" + System.currentTimeMillis());

        System.out.println( "剩餘時間 : " + ((body.getExpiration().getTime()) - System.currentTimeMillis())/1000 + "秒" );
    }

    @Test
    public void tttt() throws ScriptException {
        System.out.println(new ScriptEngineManager().getEngineByName("nashorn").eval("1+2"));
    }
}
