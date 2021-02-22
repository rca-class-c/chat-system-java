package server.config;
import redis.clients.jedis.Jedis;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.Properties;

public class JedisConfig {

	public Jedis conn() {
		Jedis conn = null;

		try (FileInputStream f = new FileInputStream("src/server/config/db.properties")) {

			// load the properties file
			Properties pros = new Properties();
			pros.load(f);

			// assign migrations.sql parameters
			String url = pros.getProperty("REDIS_URL");
			int port = Integer.parseUnsignedInt(pros.getProperty("REDIS_PORT"));

			if(port == 0)
				conn = new Jedis(url);
			else
				conn = new Jedis(url,port);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	     return conn;
	}
	

}
