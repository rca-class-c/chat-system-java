package server.repositories;

import redis.clients.jedis.Jedis;
import server.config.JedisConfig;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.logging.Logger;

public class ReportsManagementRepository {
    private String key;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    Jedis jedis = null;

    /**
     * method used to report message per day
     */
    public void insertMessageReport() {
        key = "message:"+ sdf.format(timestamp);
        try{
            Jedis jedis = new JedisConfig().conn();
            if (jedis.exists(key)){
                jedis.incr(key);
            }else{
                jedis.set(key, String.valueOf(1));
            }

        }catch (Exception e){
            System.out.println("error occurred"+e);
        }
        finally {
            if(jedis != null){
                jedis.close();
            }
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
            jedis = new JedisConfig().conn();
            if (jedis.exists(key)){
                jedis.hincrBy(key,"total",1);
            }else{
                jedis.hset(key,"total", String.valueOf(1));
                jedis.hset(key,"sentAt", String.valueOf(sendAt));
            }


        }catch (Exception e){
            System.out.println("error occurred"+e.getMessage());
        }
        finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    /**
     * method used to report number of user created a day
     */
    public void insertUserReport() {
        key = "user:"+sdf.format(timestamp);

        try{
            jedis = new JedisConfig().conn();
            if (jedis.exists(key)){
                jedis.incr(key);
            }else{
                jedis.set(key, String.valueOf(1));
            }

        }catch (Exception e){
            System.out.println("error occurred"+e);
        }
        finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    /**
     * method used to p
     */
    public void insertGroupReport(){
        key = "group:"+sdf.format(timestamp);
        try{
             jedis = new JedisConfig().conn();
            if (jedis.exists(key)){
                jedis.incr(key);
            }else{
                jedis.set(key, String.valueOf(1));
            }

        }catch (Exception e){
            System.out.println("error occurred"+e);
        }
        finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }
    public List<List> getReport(String pattern){
        List<String>  keysList = new ArrayList<>();
        Set<String> redisKeys = null;
        List<String> keys = null;
        List<List> allList = new ArrayList<>();
        try {
            jedis = new JedisConfig().conn();
            redisKeys = jedis.keys(pattern+"*");
            keys = new ArrayList<>(redisKeys);
            for (String num: redisKeys) {
                keysList.add(jedis.get(num));
            }
        } catch (Exception e) {
            System.out.println("error occurred try again");
        }
        finally {
            if(jedis != null){
                jedis.close();
            }
        }
        allList.add(keys);
        allList.add(keysList);


        return allList;
    }



}
