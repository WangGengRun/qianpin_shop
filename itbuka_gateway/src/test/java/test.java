import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class test {
    public static void main(String[] args) {
//        JwtBuilder builder = Jwts.builder().setId("888") //设置唯一编号
//                .setSubject("小白")//设置主题  可以是JSON数据
//                .setIssuedAt(new Date())//设置签发日期
//                .signWith(SignatureAlgorithm.HS256, "itcast");//设置签名 使用HS256算法，并设置SecretKey(字符串) //构建 并返回一个字符串
//        System.out.println(builder.compact());
        String compactJwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE3MjIzMDIxMzN9.zQoFmtpO1FuBjJfgY9N8UT0glAgs3HuFIFIj1If1rYQ";
        Claims claims = Jwts.parser().setSigningKey("itcast").parseClaimsJws(compactJwt).getBody();
        System.out.println(claims);

    }

}
