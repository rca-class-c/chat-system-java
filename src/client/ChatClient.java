package client;
import client.views.components.Component;
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
            Component.alertSuccessErrorMessage(10,"Connected to Server Successfully");
            System.out.println();

            new client.WriteThread(socket, this).run(socket);
        } catch (UnknownHostException ex) {
            Component.alertDangerErrorMessage(10, "Server not found");
        } catch (IOException ex) {
            Component.alertDangerErrorMessage(10, ex.getMessage());
        }
    }

    public int getUserid() {
        return Userid;
    }

    public void setUserid(int userid) {
        Userid = userid;
    }

    public static void main(String[] args) throws SQLException {
        try {
            CommonUtil.addTabs(10, false);
            System.out.print("Enter your hostname: ");
            Scanner scanner = new Scanner(System.in);
            String hname = scanner.nextLine();
            CommonUtil.addTabs(10, false);
            System.out.print("Enter your port: ");
            int hport = scanner.nextInt();
            scanner.nextLine();
            ChatClient client = new ChatClient(hname, hport);
            client.execute();
        } catch (Exception e) {
            Component.alertDangerErrorMessage(10, "Server not found");
        }
    }
}


/**
 * ChatClient moved to the root folder*/