package server.config;
import redis.clients.jedis.Jedis;

public class JedicConfig {

	public Jedis conn() {
		Jedis jedis = new Jedis("localhost"); 
	     System.out.println("Connection to server sucessfully"); 
	     
	     return jedis;
	}
	

}
