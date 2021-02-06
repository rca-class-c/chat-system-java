package client;
import java.net.*;
import java.io.*;
import java.sql.*;
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
            System.out.println("Connected to the chat server");
            new client.WriteThread(socket, this).start();
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
        ChatClient client = new ChatClient("localhost", 9812);
        client.execute();

    }
}


/**
 * ChatClient moved to the root folder*/