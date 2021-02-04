package messages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Date;
import java.util.*;
public class notifications {
	 public static Connection connection(){
	        Connection connection = null;
	        try {
	             connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/chat_system_db",
	                    "postgres", "nshuti");
	            //migrations.sql connection;
	            System.out.println("Connected to PostgreSQL database!");
	            
	        } catch (SQLException e) {
	            System.out.println("Error occurred");
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return connection;
	    }
	public static void main(String[] args) throws Exception{
		Set<ResultSet>  notis = new HashSet<ResultSet>();
		int user_id = 1;
		// TODO Auto-generated method stub
		Connection connect= connection();
		Statement statement = connect.createStatement();
		ResultSet gr;
		gr = statement.executeQuery("select * from user_group where user_id="+user_id);
		int size = gr.getFetchSize();
		ResultSet grm = null;
		System.out.println("the size is "+size);
		while(gr.next()) {
				
				grm = statement.executeQuery("select * from messages where group_receiver = "+gr.getInt(1)+" and isRead=false");
				System.out.println("This is grm "+grm);
				notis.add(grm);
//				while (grm.next()) {
//					int id = grm.getInt(1);
//					String cont = grm.getString(2);
//					int sender = grm.getInt(3);
//					int u_rec = grm.getInt(4);
//					int g_rec = grm.getInt(5);
//					Date sent_at = grm.getDate(7);
//					Boolean isRead = grm.getBoolean(8);
//					System.out.println(id +"\t "+cont + "\t "+sender+ "\t "+u_rec + "\t "+g_rec+ "\t "+sent_at + "\t "+isRead);
//				}
			
		}
		System.out.println("gr does not have a next\n");
		ResultSet rs;
//		statement.executeQuery("Select * from messages where user_receiver="+user_id+" union"
//		+ " select * from messages where group_receiver = "+gr.getInt(1)+" and isRead=false");
		rs = statement.executeQuery("Select * from messages where user_receiver="+user_id);
		notis.add(rs);
		System.out.println("Id\t content\t sender\t Ureceiver\t Greceiver\t Sendt\t isRead ");
//		while (rs.next()) {
//			int id = rs.getInt(1);
//			String cont = rs.getString(2);
//			int sender = rs.getInt(3);
//			int u_rec = rs.getInt(4);
//			int g_rec = rs.getInt(5);
//			Date sent_at = rs.getDate(7);
//			Boolean isRead = rs.getBoolean(8);
//			System.out.println(id +"\t "+cont + "\t "+sender+ "\t "+u_rec + "\t "+g_rec+ "\t "+sent_at + "\t "+isRead);
//		}
		
		System.out.println("\n\n This is from  the combination!\n");
		System.out.println(notis);
		Iterator<ResultSet> it = notis.iterator();
	     while(it.hasNext()){
	    	 ResultSet row = it.next();
	    	 while(row.next()) {
	    		 System.out.println("Hi");
	    		 int id = row.getInt(1);
					String cont = row.getString(2);
					int sender = row.getInt(3);
					int u_rec = row.getInt(4);
					int g_rec = row.getInt(5);
					Date sent_at = row.getDate(7);
					Boolean isRead = row.getBoolean(8);
					System.out.println(id +"\t "+cont + "\t "+sender+ "\t "+u_rec + "\t "+g_rec+ "\t "+sent_at + "\t "+isRead);
	    	 }
//	        System.out.println(row);
	     }
		

	}

}