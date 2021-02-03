package controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;
import io.jsonwebtoken.security.SignatureException;


/**
 * Token controller
 *
 * @author Ntwari Clarance Liberiste
 * @since 1.0
 */
public class Token {

    private TreeMap<String,String> payload;
    private final String secretKey = "V3uZKw7RPYW85FNTwiqm8JjZ9ep/jn2bZ8xy0vl8S+1OmHjJoCSNbXLl+hb8ulmnUTkxaPtBcYKM/60PjoMyPw==";


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
        byte[] secrete  = Base64.getDecoder().decode(this.secretKey);

        jwtToken = Jwts.builder()
                .setSubject("Ntwari liberi")
                .setAudience("Auth")
                .claim("name","liberi")
                .claim("age",new Random().nextInt(20)+1)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(timeToLast, unit)))
                .signWith(Keys.hmacShaKeyFor(secrete))
                .compact();

        this.decodeToken(jwtToken);

        return jwtToken;
    }


    /**
     *
     * Decoding jwt token created by Token class, make sure that you
     * decode token created by Token Class so that it will match secret Key
     * unless you will get an io.jsonwebtoken.security.SignatureException exception
     *
     * @param jwt jwt token, of String type
     *
     * @return decoded string, of String type
     *
     * @throws SignatureException invalid signature exception
     */
    public String decodeToken(String jwt) throws SignatureException{
        byte[] secrete  = Base64.getDecoder().decode(this.secretKey);
        String results = "";

        try{
            results = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(secrete))
                    .parseClaimsJws(jwt)
                    .toString();

        }catch(SignatureException e){
            System.out.println(e.getMessage());
        }

        return results;
    }

}

