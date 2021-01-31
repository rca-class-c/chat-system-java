package messages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Date;
import java.util.*;
public class trial {
	 public static Connection connection(){
	        Connection connection = null;
	        try {
	             connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/chat_system_db",
	                    "postgres", "nshuti");
	            //db connection;
	            System.out.println("Connected to PostgreSQL database!");
	            
	        } catch (SQLException e) {
	            System.out.println("Error occurred");
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return connection;
	    }
	public static Set<ResultSet> notificat(int user_id)throws Exception{
		Set<ResultSet>  notis = new HashSet<ResultSet>();
		Connection connect= connection();
		Statement statement = connect.createStatement();
		ResultSet gr;
		gr = statement.executeQuery("select * from user_group where user_id="+user_id);
		ResultSet grm = null;
		while(gr.next()) {				
				grm = statement.executeQuery("select * from messages where group_receiver = "+gr.getInt(1)+" and isRead=false and sender!="+user_id);
				notis.add(grm);
		}
		ResultSet rs;
		rs = statement.executeQuery("Select * from messages where user_receiver="+user_id+" and isRead=false and sender!="+user_id);
		notis.add(rs);
		return notis;
	}
	public static void main(String[] args) throws Exception{
		Set<ResultSet> notis = notificat(3);
		System.out.println("Id\t content\t sender\t Ureceiver\t Greceiver\t Sendt\t isRead ");
		
		
		Iterator<ResultSet> it = notis.iterator();
	     while(it.hasNext()){
	    	 ResultSet row = it.next();
	    	 while(row.next()) {
	    		 int id = row.getInt(1);
					String cont = row.getString(2);
					int sender = row.getInt(3);
					int u_rec = row.getInt(4);
					int g_rec = row.getInt(5);
					Date sent_at = row.getDate(7);
					Boolean isRead = row.getBoolean(8);
					System.out.println(id +"\t "+cont + "\t "+sender+ "\t "+u_rec + "\t "+g_rec+ "\t "+sent_at + "\t "+isRead);
	    	 }

	     }
	     System.out.println("\n\n This is from  the combination!\n");
		 System.out.println(notis);

	}

}