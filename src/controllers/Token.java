package controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Base64;
import java.util.Random;
import java.util.TreeMap;
import java.util.Date;


/**
 * Token controller
 *
 * @author Ntwari Clarance Liberiste
 * @since 1.0
 */
public class Token {

    private TreeMap<String,String> payload;


    /**
     * Token controller
     *
     * @param payload Data to be included in the jwt token
     */
    public Token(TreeMap<String,String> payload){
        this.payload = payload;
    }


    public TreeMap<String, String> getPayload() {
        return payload;
    }

    public void setPayload(TreeMap<String, String> payload) {
        this.payload = payload;
    }


    /**
     * Generates jwt based token basing on given data in class initialisation,
     * given data in initialization will be used as claims so they will appear
     * in the token
     *
     * @param timeToLast Time that it will take for token to be active, of type long
     * @param unit Unit of the time, of java.time.temporal.TemporalUnit type
     *
     * @return token, of String type
     */
    public String generateToken(long timeToLast, TemporalUnit unit){
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
                .setExpiration(Date.from(now.plus(timeToLast, unit)))
                .signWith(Keys.hmacShaKeyFor(secrete))
                .compact();

        return jwtToken;
    }

}

