package client;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.Scanner;

import client.interfaces.Request;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.AuthInput;

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
    public  String login() throws SQLException, JsonProcessingException {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Your username:");
            String userName = scanner.nextLine();
            System.out.print("Your password:");
            String password = scanner.nextLine();
            ObjectMapper objectMapper = new ObjectMapper();
            AuthInput loginData = new AuthInput(userName,password);
            String key = "login";
            Request request = new Request(loginData,key);
            String LoginDataAsString = objectMapper.writeValueAsString(request);
            writer.println(LoginDataAsString);
        }while(true);
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        do {
            System.out.println("\t \t WELCOME TO CHAT SYSTEM \t\t");
            System.out.println("\t  1. LOGIN  \t");
            System.out.println("\t  2. SIGNUP \t");
            System.out.println("\t  3. HELP   \t");
            System.out.println("\t  44.QUIT   \t");


            choice  = scanner.nextInt();

            switch (choice){

                case 1:
                    try {
                        login();
                    } catch (SQLException | JsonProcessingException throwables) {
                        throwables.printStackTrace();
                    }
                    System.out.println("Your choice is login");
                    break;
                case 2:
                    System.out.println("Your choice is signup");
                    break;
                case 3:
                    System.out.println("Your choice is help");
                    break;
                case 44:
                    System.out.println("Thank you for being with us");
                default:
                    System.out.println("Your choice null");
                    break;
            }

        } while (choice != 44);
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
}