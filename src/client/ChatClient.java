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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your host name");
//        Scanner scanner = new Scanner(System.in);
        String hostname = scanner.nextLine();
        System.out.println("Enter your port number");
        int port  = scanner.nextInt();
        ChatClient client = new ChatClient(hostname, port);
        System.out.println("\t \t WELCOME TO CHAT SYSTEM \t\t");
        System.out.println("\t  1. LOGIN  \t");
        System.out.println("\t  2. SIGNUP \t");
        System.out.println("\t  3. HELP   \t");


        int choice  = scanner.nextInt();

        switch (choice){

            case 1:
                System.out.println("Your choice is login");
                break;
            case 2:
                System.out.println("Your choice is signup");
                break;
            case 3:
                System.out.println("Your choice is help");
                break;
            default:
                System.out.println("Your choice null");
                break;
        }

        client.execute();

    }
}