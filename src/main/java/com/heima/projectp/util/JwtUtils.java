package com.heima.projectp.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String signKey = "heima";
    private static Long expire = 1 * 60 * 60 * 1000L;//一小時

    public static String generateJWT(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    public static Claims parseJWT( String jwt){
        Claims body = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return body;
    }
}
