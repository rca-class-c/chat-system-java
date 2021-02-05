package client;

import server.interfaces.LoginData;

import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This thread is responsible for reading user's input and send it
 * to the server.
 * It runs in an infinite loop until the user types 'bye' to quit.
 */
public class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private ChatClient client;
    public WriteThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public String login() throws IOException {
        OutputStream outputStream = this.socket.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        Scanner scanner = new Scanner(System.in);
        String userName  = "";
            System.out.print("Your username:");
            userName = scanner.nextLine();
            System.out.print("Your password:");
            String password = scanner.nextLine();
        LoginData loginData = new LoginData(userName,password);
        objectOutputStream.writeObject(loginData);

        return "Data sent";


    }


    public void run() {
        Scanner scanner = new Scanner(System.in);
        String userName = "";
        String text="";
        do {
            System.out.println("\t \t WELCOME TO CHAT SYSTEM \t\t");
            System.out.println("\t  1. LOGIN  \t");
            System.out.println("\t  2. SIGNUP \t");
            System.out.println("\t  3. HELP   \t");

            int choice  = scanner.nextInt();

            switch (choice){

                case 1:
                    try {
                        login();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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

        } while (!text.equals("bye"));
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
}