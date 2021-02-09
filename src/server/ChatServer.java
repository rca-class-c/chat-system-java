package server;

import client.views.components.Component;
import server.models.ActiveUser;
import utils.CommonUtil;
import utils.ConsoleColor;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * This the main server that runs out to connect new users and calls the user thread
 *@Author: Shallon Kobusinge
 */
public class ChatServer {
    private int port;
    private Set<ActiveUser> activeUsers = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();
    public ChatServer(int port) {
        this.port = port;
    }
    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Component.pageTitleView("SERVER");
            System.out.println();


            CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.WHITE_BACKGROUND_BRIGHT);
            CommonUtil.useColor(ConsoleColor.BoldColor.BLACK_BOLD);
            System.out.print(" Chat Server is listening on port "  + port + " ");
            CommonUtil.resetColor();

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
     
    	//connecting to the database

        
        int port = 9812;
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
    public void addUserName(int id, String userName) {
        activeUsers.add(new ActiveUser(id,userName));
    }
    /**
     * When a client is disconneted, removes the associated username and UserThread
     */
    void removeUser(String userName, int id,UserThread aUser) {
        boolean removed = activeUsers.remove(new ActiveUser(id,userName));
        if (removed) {
            userThreads.remove(aUser);
            System.out.println("The user " + userName + " quitted");
        }
    }
    Set<ActiveUser> getUserNames() {
        return this.activeUsers;
    }
    /**
     * Returns true if there are other users connected (not count the currently
     * connected user)
     */
    boolean hasUsers() {
        return !this.activeUsers.isEmpty();
    }
}
