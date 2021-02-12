package server.config;
import redis.clients.jedis.Jedis;

public class JedicConfig {

	public Jedis conn() {
		Jedis jedis = new Jedis("localhost");
	     return jedis;
	}
	

}
