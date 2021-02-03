package controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Random;
import java.util.TreeMap;
import java.util.Date;

public class Token {

    private TreeMap<String,String> payload;

    public Token(TreeMap<String,String> payload){
        this.payload = payload;
    }


    public TreeMap<String, String> getPayload() {
        return payload;
    }

    public void setPayload(TreeMap<String, String> payload) {
        this.payload = payload;
    }


    public String generateToken(){
        String jwtToken;
        Instant now = Instant.now();
        String secreteKey = "V3uZKw7RPYW85FNTwiqm8JjZ9ep/jn2bZ8xy0vl8S+1OmHjJoCSNbXLl+hb8ulmnUTkxaPtBcYKM/60PjoMyPw==";
        byte[] secrete  = Base64.getDecoder().decode(secreteKey);

        jwtToken = Jwts.builder()
                .setSubject("Ntwari liberi")
                .setAudience("Auth")
                .claim("name","liberi")
                .claim("age",new Random().nextInt(20)+1)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
                .signWith(Keys.hmacShaKeyFor(secrete))
                .compact();

        return jwtToken;
    }

}

