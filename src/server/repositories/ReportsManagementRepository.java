package server.repositories;

import redis.clients.jedis.Jedis;
import server.config.JedisConfig;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReportsManagementRepository {
    private String key;
    /**
     * method used to report message per day
     */
    public void insertMessageReport() {
        key = "message:"+ LocalTime.now();
        try{
            Jedis jedis = new JedisConfig().conn();
            if (jedis.exists(key)){
                jedis.incr(key);
            }else{
                jedis.set(key, String.valueOf(1));
            }

        }catch (Exception e){
            System.out.println("error occurred");
        }

    }

    /**
     * method used to report message user send per day
     *
     * @param userId
     *
     */
    public void insertMessageReport(int userId) {
        key = "Message:user:"+userId;
        long sendAt = new Timestamp(System.currentTimeMillis()).getTime();
        try{
            Jedis jedis = new JedisConfig().conn();
            if (jedis.exists(key)){
                jedis.hincrBy(key,"total",1);
            }else{
                jedis.hset(key,"total", String.valueOf(1));
                jedis.hset(key,"sentAt", String.valueOf(sendAt));
            }


        }catch (Exception e){
            System.out.println("error occurred");
        }
    }

    /**
     * method used to report number of user created a day
     */
    public void insertUserReport() {
        key = "user:"+ LocalTime.now();
        try{
            Jedis jedis = new JedisConfig().conn();
            if (jedis.exists(key)){
                jedis.incr(key);
            }else{
                jedis.set(key, String.valueOf(1));
            }

        }catch (Exception e){
            System.out.println("error occurred");
        }
    }

    /**
     * method used to p
     */
    public void insertGroupReport(){
        key = "group:"+ LocalTime.now();
        try{
            Jedis jedis = new JedisConfig().conn();
            if (jedis.exists(key)){
                jedis.incr(key);
            }else{
                jedis.set(key, String.valueOf(1));
            }

        }catch (Exception e){
            System.out.println("error occurred");
        }
    }

}
