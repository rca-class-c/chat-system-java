package utils;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.util.Set;
import server.services.MessagesService;


/**
 * Common Utilities
 * @author Divin Irakiza
 */
public class CommonUtil {
    /**
     * Use a Console Color
     *
     * @param color
     */
    public static void useColor(String color) {
        System.out.print(color);
    }

    /**
     * Reset Color to Console default
     */
    public static void resetColor() {
        System.out.print(ConsoleColor.RESET);
    }

    /**
     * Style Text
     *
     * @param tabCount
     */
    public static void addTabs(int tabCount, Boolean newLine) {
        if (newLine)
            System.out.print("\n" + "\t".repeat(Math.max(0, tabCount)));
        else
            System.out.print("" + "\t".repeat(Math.max(0, tabCount)));

    }

    /**
     * Clear Console Screen
     */
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ignored) {
        }

    }


    /**
     * Formats a request String to a map
     *
     * @param string Request String
     * @return Map<String, String>
     */
    public static Map<String, String> mapRequestString(String string) {
        Map<String, String> map = new HashMap<String, String>();
        String[] parameters = string.split("&");
        for (String parameter : parameters) {
            String[] mapping = parameter.split("=");
            map.put(mapping[0], mapping[1]);
        }
        return map;
    }

    public static void displayTray(Set<ResultSet> notis) throws Exception {
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
        Iterator<ResultSet> it = notis.iterator();
        while (it.hasNext()) {
            ResultSet row = it.next();
            while (row.next()) {
                String sname = row.getString(1);
                String cont = row.getString(3);
//                int sender = row.getInt(4);
//                int u_rec = row.getInt(5);
                int g_rec = row.getInt(6);
//                String s=String.valueOf(sender);
                if (g_rec == 0)
                    trayIcon.displayMessage(cont, "from " + sname, MessageType.NONE);
                else {
                    MessagesService msg = new MessagesService();
                    String gr_name = msg.viewGroupName(g_rec);
                    trayIcon.displayMessage(cont, "In " + gr_name + ": " + sname, MessageType.INFO);
                }
//            }
//
//        }
            }
        }
    }
}
