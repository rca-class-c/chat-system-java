package server;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
/**
 * This is the chat server program. Press Ctrl + C to terminate the program.
 *
 */
public class ChatServer {
    private int port;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();
    public ChatServer(int port) {
        this.port = port;
    }
    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Chat Server is listening on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New user connected");
                // passing socket and server to the userthread
                UserThread newUser = new UserThread(socket, this);
                if(userThreads.add(newUser)) {
                    System.out.println();
                }
                newUser.start();
            }
        } catch (IOException ex) {
            System.out.println("Error in the server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/chat_system_db",
                    "postgres", "Didier@2021");
            //db connection;
            System.out.println("Connected to PostgreSQL database!");
            Statement statement = connection.createStatement();
            System.out.println("Reading users...");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.users");
            System.out.println(resultSet.getFetchSize());
            while (resultSet.next()) {
                System.out.printf("/n", resultSet.getString("username"), resultSet.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("Error occurred");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        if (args.length < 1) {
//            System.out.println("Syntax: java ChatServer <port-number>");
//            System.exit(0);
//        }
        int port = 4000;
        ChatServer server = new ChatServer(port);
        server.execute();
    }
    /**
     * Delivers a message from one user to others (broadcasting)
     */
    void broadcast(String message, UserThread excludeUser) {
        for (UserThread aUser : userThreads) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }
    /**
     * Stores username of the newly connected client will read.
     */
    void addUserName(String userName) {
        userNames.add(userName);
    }
    /**
     * When a client is disconneted, removes the associated username and UserThread
     */
    void removeUser(String userName, UserThread aUser) {
        boolean removed = userNames.remove(userName);
        if (removed) {
            userThreads.remove(aUser);
            System.out.println("The user " + userName + " quitted");
        }
    }
    Set<String> getUserNames() {
        return this.userNames;
    }
    /**
     * Returns true if there are other users connected (not count the currently
     * connected user)
     */
    boolean hasUsers() {
        return !this.userNames.isEmpty();
    }
}
