package begin;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
//import java.awt.*;
import java.awt.TrayIcon.MessageType;
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
	 public void displayTray() throws Exception {
	        //Obtain only one instance of the SystemTray object
	        SystemTray tray = SystemTray.getSystemTray();
	        		
	        //If the icon is a file
	        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
//	        Alternative (if the icon is on the classpath):
//	        Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

	        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
	        //Let the system resize the image if needed
	        trayIcon.setImageAutoSize(true);
	        //Set tooltip text for the tray icon
	        trayIcon.setToolTip("System tray icon demo");
	        tray.add(trayIcon);
	        Set<ResultSet> notis = notificat(3);
	        Iterator<ResultSet> it = notis.iterator();
		     while(it.hasNext()){
		    	 ResultSet row = it.next();
		    	 while(row.next()) {
//		    		 int id = row.getInt(1);
						String cont = row.getString(2);
						int sender = row.getInt(3);
						int u_rec = row.getInt(4);
						int g_rec = row.getInt(5);
//						Date sent_at = row.getDate(7);
//						Boolean isRead = row.getBoolean(8);
						String s=String.valueOf(sender);
						if(g_rec==0)
						trayIcon.displayMessage(cont, "from "+s, MessageType.NONE);
//						System.out.println(id +"\t "+cont + "\t "+sender+ "\t "+u_rec + "\t "+g_rec+ "\t "+sent_at + "\t "+isRead);
						else {
							trayIcon.displayMessage(cont, "In "+g_rec+": "+s, MessageType.INFO);
						}
		    	 }

		     }
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
		 if (SystemTray.isSupported()) {
	            trial td = new trial();
	            td.displayTray();
	        } else {
	            System.err.println("System tray not supported!");
	        }
		Set<ResultSet> notis = notificat(2);
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
