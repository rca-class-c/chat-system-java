package client;
import utils.CommonUtil;
import utils.ConsoleColor;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

/**
 * This is the main Entry
 * @Author: Hirwa Chanelle
 */

public class ChatClient {
    private String hostname;
    private int port;
    private int Userid;

    public ChatClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }
    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);
            CommonUtil.addTabs(10,true);
            CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.WHITE_BACKGROUND_BRIGHT);
            CommonUtil.useColor(ConsoleColor.BoldColor.BLACK_BOLD);
            System.out.print(" Connected to Server Successfully " );
            CommonUtil.resetColor();


            new client.WriteThread(socket, this).run(socket);
        } catch (UnknownHostException ex) {
            CommonUtil.useColor(ConsoleColor.BackgroundColor.WHITE_BACKGROUND);
            CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
            System.out.print("  Invalid Username or Password  ");
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            CommonUtil.useColor(ConsoleColor.BackgroundColor.WHITE_BACKGROUND);
            CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
            System.out.print("  Invalid Username or Password  ");
            System.out.println("Server Down!!!!! Try restarting: " + ex.getMessage());
        }
    }

    public int getUserid() {
        return Userid;
    }

    public void setUserid(int userid) {
        Userid = userid;
    }

    public static void main(String[] args) throws SQLException {
        ChatClient client = new ChatClient("localhost", 9812);
        client.execute();
    }
}


/**
 * ChatClient moved to the root folder*/