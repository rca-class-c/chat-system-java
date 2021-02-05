package client;

import server.models.ActiveUser;
import server.models.AuthInput;

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
    public String login() throws IOException, ClassNotFoundException {
        OutputStream outputStream = this.socket.getOutputStream();
        InputStream input = socket.getInputStream();
        // create an object output stream from the output stream so we can send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        Scanner scanner = new Scanner(System.in);
        String userName  = "";
            System.out.print("Your username:");
            userName = scanner.nextLine();
            System.out.print("Your password:");
            String password = scanner.nextLine();

        AuthInput loginData = new AuthInput(userName,password);
        objectOutputStream.writeObject(loginData);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String response = reader.readLine();
        System.out.println(response);
        if(response.equals("true")){
            System.out.println("Getting db logging results");
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            ActiveUser activeUser = (ActiveUser) objectInputStream.readObject();
        }
        System.out.println("\n" + response);

        return "Data sent";


    }



    public void run() {
        PrintWriter out = null;
        try {
            out = new PrintWriter(this.socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
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
                        out.println("LOGIN_REQUEST");
                        out.flush();
                        login();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Your choice is login");
                    break;
                case 2:
                    out.println("SIGNUP_REQUEST");
                    //your methods;
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