package controllers;

import io.jsonwebtoken.Jwts;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
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

        jwtToken = Jwts.builder()
                .setSubject("Ntwari liberi")
                .setAudience("Auth")
                .claim("name","liberi")
                .claim("age",new Random().nextInt(20)+1)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
                .compact();

        return jwtToken;
    }

}

