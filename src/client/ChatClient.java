package client;

import java.net.*;
import java.io.*;
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
            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();
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
    public static void main(String[] args) {
        System.out.println("Enter your host name");
        Scanner scanner = new Scanner(System.in);
        String hostname = scanner.nextLine();
        System.out.println("Enter your host name");
        int port  = scanner.nextInt();
        ChatClient client = new ChatClient(hostname, port);
        client.execute();
    }
}