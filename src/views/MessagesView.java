package views;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import controllers.Message;
import utils.CommonMethod;
import utils.ConsoleColor;

public class MessagesView {
	private final Message messageController = new Message();
	public void printNotifications() throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		CommonMethod.addTabs(10, false);
        System.out.print("Enter The user Id: ");
        int userId = scanner.nextInt();
        
        CommonMethod.addTabs(10, true);
        CommonMethod.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
        CommonMethod.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
        
        Set<ResultSet> notis = messageController.displayUserNotifications(userId);
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
//	     System.out.println("\n\n This is from  the combination!\n");
//		 System.out.println(notis);
        
	}
	public void displayTray() throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		CommonMethod.addTabs(10, false);
        System.out.print("Enter The user Id: ");
        int userId = scanner.nextInt();
        
        SystemTray tray = SystemTray.getSystemTray();        		
        
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
       
        trayIcon.setImageAutoSize(true);        
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        Set<ResultSet> notis = messageController.displayUserNotifications(userId);
        Iterator<ResultSet> it = notis.iterator();
	     while(it.hasNext()){
	    	 ResultSet row = it.next();
	    	 while(row.next()) {
					String cont = row.getString(2);
					int sender = row.getInt(3);
					int u_rec = row.getInt(4);
					int g_rec = row.getInt(5);
					
					String s=String.valueOf(sender);
					if(g_rec==0)
					trayIcon.displayMessage(cont, "from "+s, MessageType.NONE);
					else {
						trayIcon.displayMessage(cont, "In "+g_rec+": "+s, MessageType.INFO);
					}
	    	 }

	     }
    }

}
