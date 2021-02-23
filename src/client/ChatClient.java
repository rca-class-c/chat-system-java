package client;
import utils.CommonUtil;
import utils.ConsoleColor;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Scanner;

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

            System.out.println();

            new client.WriteThread(socket, this).run(socket);
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }
    }

    public int getUserid() {
        return Userid;
    }

    public void setUserid(int userid) {
        Userid = userid;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("Enter your hostname");
        Scanner scanner = new Scanner(System.in);
        String hname = scanner.nextLine();
        System.out.println("Enter your port");
        int hport = scanner.nextInt();
        scanner.nextLine();
        ChatClient client = new ChatClient(hname, hport);
        client.execute();
    }
}


/**
 * ChatClient moved to the root folder*/