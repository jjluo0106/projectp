package com.heima;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
@SpringBootTest
public class testEncryption {


    @Test
    public void testHmacSha256() {

        try {
            String secretKey = "69c2402b994304699ce5cb73bd8dad90";
            String data = "amount=20.00&channel_id=1030&client_key=01hjtve34p9phawbxdz4py43nh&notify_url=http://pay.test4ldrms6f2.com:5222/pay/api/callback/DAYANZF/16676&out_trade_no=20240103135512675bCP";

            String hmacSha256 = calculateHmacSha256(secretKey, data);

            String expectedHmacSha256 = "ef71cc0f4cdae059c821c72b467c8cc3512ddb033c2fda86cce99d1b4c4fab28";

            System.out.println("Calculated HMAC-SHA256: " + hmacSha256);
            System.out.println("Expected HMAC-SHA256:   " + expectedHmacSha256);
            System.out.println("Match: " + hmacSha256.equals(expectedHmacSha256));

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    private static String calculateHmacSha256(String secretKey, String data)
            throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        sha256Hmac.init(secretKeySpec);

        byte[] hashBytes = sha256Hmac.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    @Test
    public void testLogStr() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n{\"merchantMemberNo\":\"82933181\",\"merchantOrderNo\":\"20240103165625719PyB\",\"paymentMethod\":1,\"amount\":\"1000\",\"timestamp\":1704272185,\"notifyUrl\":\"http://pay.ky116pay1.com:5222/pay/api/callback/NEWNoQIANBAO/155\",\"sign\":\"bc729f9f3012cf77f97e5b65fdcc3c34e375e18f06179c662da21c1898e50e5a\"}");

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '{') sb.insert(i + 1, '\n');
            else if (sb.charAt(i) == '&' || sb.charAt(i) == ',') sb.insert(i + 1, '\n');
            else if (sb.charAt(i) == '}') {
                sb.insert(i, '\n');
                i++; //避免重复计算
            }
        }

        System.out.println(sb.toString());
//        log.info(sb.toString());
    }

    @Test
    public void testSplit(){
        String s = "123";

        String[] split = s.split(",");

        System.out.println(split[0]);
    }
}
