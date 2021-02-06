package client;
import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.Scanner;

/**
 * This is the chat client program.
 * Type 'bye' to terminte the program.
 *
 */

public class ChatClient {
    private String hostname;
    private int port;
    private String userName;

    public ChatClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }
    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);
            System.out.println("Connected to the chat server");

            //readthread and writethread recieves socket and client as parameters
            //new client.ReadThread(socket, this).start();
            new client.WriteThread(socket, this).start();
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }
    }
    void setUserName(String userName) {
        this.userName = userName;
    }
    String getUserName() {
        
        return this.userName;
    }


    public static void main(String[] args) throws SQLException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter your host name");
////        Scanner scanner = new Scanner(System.in);
//        String hostname = scanner.nextLine();
//        System.out.println("Enter your port number");
//        int port  = scanner.nextInt();

        ChatClient client = new ChatClient("localhost", 9812);
        client.execute();



    }
}


/**
 * ChatClient moved to the root folder*/