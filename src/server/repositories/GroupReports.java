//package server.repositories;
//import redis.clients.jedis.Jedis;
//import server.config.JedisConfig;
//
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.*;
//import java.util.logging.Logger;
//public class GroupReports {
//    private String key;
//    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//    Jedis jedis = null;
//
//    public void AddGroupReport(int userId) {
//        key = "Group:user:" + userId;
//        long createdAt = new Timestamp(System.currentTimeMillis()).getTime();
//        try {
//            jedis = new JedisConfig().conn();
//            if (jedis.exists(key)) {
//                jedis.hincrBy(key, "total", 1);
//            } else {
//                jedis.hset(key, "total", String.valueOf(1));
//                jedis.hset(key, "createdAt", String.valueOf(createdAt));
////            }
//        } catch (Exception e) {
//            System.out.println("Error occurred" + e.getMessage());
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//    }
//
//}