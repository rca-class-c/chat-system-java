package client;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.Scanner;

import client.interfaces.DecodeResponse;
import client.interfaces.Request;
import client.interfaces.ResponseDecoded;
import client.views.components.Component;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.AuthInput;
import server.models.User;
import utils.CommonUtil;

/**
 * This thread is responsible for reading user's input and send it
 * to the server.
 * It runs in an infinite loop until the user types 'bye' to quit.
 */
public class WriteThread extends Thread {
    private PrintWriter writer;
    private BufferedReader reader;
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
        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public  void login() throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        Component.pageTitleView("LOGIN TO CLASS_C CHAT");
        while(true){
            CommonUtil.addTabs(10, false);
            System.out.print("Your username:");

            String userName = scanner.nextLine();
            CommonUtil.addTabs(10, false);
            System.out.print("Your password:");
            String password = scanner.nextLine();
            ObjectMapper objectMapper = new ObjectMapper();
            AuthInput loginData = new AuthInput(userName,password);
            String key = "login";
            Request request = new Request(loginData,key);
            String LoginDataAsString = objectMapper.writeValueAsString(request);
            writer.println(LoginDataAsString);
            ResponseDecoded response = new DecodeResponse().decodedResponse(reader.readLine());
            if(response.isSuccess()){
                CommonUtil.addTabs(10, true);
                System.out.println("Your login was very successful\n");
                break;
            }
            else{
                CommonUtil.addTabs(10, true);
                System.out.println("Your login failed, try again\n");
            }
        }
    }
    public  void signup() throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        while(true){
            Component.pageTitleView("CREATE ACCOUNT IN CLASS_C CHAT");


            CommonUtil.addTabs(10, false);
            System.out.print("Enter your Username: ");
            String username = scanner.nextLine();


            CommonUtil.addTabs(10, false);
            System.out.print("Enter your FirstName: ");
            String firstName = scanner.nextLine();

            CommonUtil.addTabs(10, false);
            System.out.print("Enter your LastName: ");
            String lastName = scanner.nextLine();

            CommonUtil.addTabs(10, false);
            System.out.print("Enter your Email: ");
            String email = scanner.nextLine();
            String gender = "";
            do {
                CommonUtil.addTabs(10, false);
                System.out.print("Enter your Gender[male,female]: ");
                gender = scanner.nextLine();
                if(!gender.equals("male") && !gender.equals("female")){
                    CommonUtil.addTabs(10, false);
                    System.out.println(gender +"Gender not valid");
                }
            }while(!gender.equals("male") && !gender.equals("female"));


            CommonUtil.addTabs(10, false);
            System.out.print("Enter your DOB: ");
            String dob = scanner.nextLine();

            CommonUtil.addTabs(10, false);
            System.out.print("Enter your Password: ");
            String password = scanner.nextLine();
            ObjectMapper objectMapper = new ObjectMapper();
            User user = new User(firstName,lastName,password,email,dob,username,gender,1,"ACTIVE");
            String key = "register";
            Request request = new Request(user,key);
            String requestAsString = objectMapper.writeValueAsString(request);
            writer.println(requestAsString);
            ResponseDecoded response = new DecodeResponse().decodedResponse(reader.readLine());
            if(response.isSuccess()){
                CommonUtil.addTabs(10, false);
                System.out.println("Your account was created successfully\n");
                break;
            }
            else{
                CommonUtil.addTabs(10, false);
                System.out.println("Account not created, try again\n");
            }
        }
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        do {
            Component.pageTitleView("WELCOME TO CHAT SYSTEM");
            CommonUtil.addTabs(10, false);
            System.out.println("\t  1. LOGIN  \t");
            CommonUtil.addTabs(10, false);
            System.out.println("\t  2. SIGNUP \t");
            CommonUtil.addTabs(10, false);
            System.out.println("\t  3. HELP   \t");
            CommonUtil.addTabs(10, false);
            System.out.println("\t -1.QUIT   \t");
            CommonUtil.addTabs(10, false);
            System.out.println("\tYour choice:\t");
            choice  = scanner.nextInt();

            switch (choice){

                case 1:
                    try {
                        login();
                    } catch (SQLException | IOException throwables) {
                        throwables.printStackTrace();
                    }
                    CommonUtil.addTabs(10, false);
                    System.out.println("Your choice is login");
                    break;
                case 2:
                    try {
                        signup();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    CommonUtil.addTabs(10, false);
                    System.out.println("Your choice is signup");
                    break;
                case 3:
                    CommonUtil.addTabs(10, false);
                    System.out.println("Your choice is help");
                    break;
                case -1:
                    CommonUtil.addTabs(10, false);
                    System.out.println("Thank you for being with us");
                    break;
                default:
                    CommonUtil.addTabs(10, false);
                    System.out.println("Your choice null");
                    break;
            }

        } while (choice != -1);
        try {
            socket.close();
        } catch (IOException ex) {
            CommonUtil.addTabs(10, false);
            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
}