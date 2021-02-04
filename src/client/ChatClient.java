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
            new client.ReadThread(socket, this).start();
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
    public static Connection connection(){
        Connection connection = null;
        try {
             connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/chat_system_db",
                    "postgres", "Didier@2021");
            //migrations.sql connection;
            System.out.println("Connected to PostgreSQL database!");
            //a.getClass().getName();
//            Statement statement = connection.createStatement();
//            System.out.println("Reading users...");
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.users");
//            System.out.println(resultSet.getFetchSize());
//            while (resultSet.next()) {
//                System.out.printf("/n", resultSet.getString("username"), resultSet.getString("email"));
//            }
        } catch (SQLException e) {
            System.out.println("Error occurred");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    public  static String login() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ResultSet resultSet = null;
        String userName  = "";
        do {
            System.out.print("Your username:");
            userName = scanner.nextLine();
            System.out.print("Your password:");
            String password = scanner.nextLine();
            Connection connection = connection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM public.users");
            if (resultSet.getFetchSize() < 1) {
                System.out.println("Invalid credentials");
            }
        }while(resultSet.getFetchSize() < 1);
        return userName;
    }
    public static void main(String[] args) throws SQLException {
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
                login();
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


/**
 * ChatClient moved to the root folder*/