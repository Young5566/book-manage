package com.Young.book.utils;

import com.Young.book.po.Librarian;
import com.Young.book.service.LibrarianService;
import io.jsonwebtoken.*;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWT {
    static final String SECRET_KEY = "itisacerettttsss";

    @Autowired
    static LibrarianService librarianService;

    public static String createToken(Librarian payload){
        String compactJws = Jwts.builder()
                // 设置头部
                .setHeaderParam("type", "JWT")

                // jwt 标注中的申明
                .setIssuedAt(new Date()) // 签发时间
                .setExpiration(new Date(new Date().getTime() + 1000*60*120L)) // 过期时间
                .setIssuer("Young") // jwt签发者

                .claim("id", payload.getId())
                .claim("username", payload.getUsername())

                // 签证
                .setSubject("rkapi") //设置主题
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()) // 设置算法（必须）
                .compact(); // 这是全部设置完成后拼接jwt串的方法

        return compactJws;
    }

    public static Map verifiyToken(String token){
        try {
            // 验证jwt
            Jws<Claims> claimsJws = Jwts.parser()
                    // 验证签发者字段iss 必须是 Young
                    .require("iss", "Young")
                    // 设置私钥
                    .setSigningKey(SECRET_KEY.getBytes())
                    // 解析jwt字符串
                    .parseClaimsJws(token);
            // 获取头部信息
            JwsHeader header = claimsJws.getHeader();
            Claims payload = claimsJws.getBody();
            return payload;
        } catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }
}
