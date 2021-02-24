package server;

import client.views.components.Component;
import redis.clients.jedis.Jedis;
import server.config.JedisConfig;
import server.config.PostegresConfig;
import server.models.ActiveUser;
import utils.CommonUtil;
import utils.ConsoleColor;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.SQLException;
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
            CommonUtil.addTabs(10, false);
            CommonUtil.useColor(ConsoleColor.BoldColor.GREEN_BOLD);
            System.out.print("Chat Server is listening on port "  + port + " ");
            CommonUtil.resetColor();

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println();
                System.out.println();
                CommonUtil.addTabs(10, false);
                CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.WHITE_BACKGROUND_BRIGHT);
                CommonUtil.useColor(ConsoleColor.BoldColor.BLACK_BOLD);
                System.out.print(" New User Connected " );
                CommonUtil.resetColor();

                // passing socket and server to the userthread
                UserThread newUser = new UserThread(socket, this);
                if(userThreads.add(newUser)) {
                    System.out.println();
                }
                newUser.start();
            }
        } catch (IOException ex) {
            Component.showErrorMessage(ex.getMessage());

        }
    }
    
    public static void main(String[] args) throws SQLException {
     
    	//connecting to the database
        Jedis jedis = new JedisConfig().conn();

        Connection conn  = PostegresConfig.getConnection();
        if(conn != null){
            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.BoldColor.PURPLE_BOLD);
            System.out.print("Postgres connection established");
        }
        System.out.println();
        if(jedis != null ){
            CommonUtil.addTabs(10, false);
            CommonUtil.useColor(ConsoleColor.BoldColor.BLUE_BOLD);
            System.out.println("Jedis connection established");
        }
        CommonUtil.resetColor();
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
            Component.showErrorMessage("The user " + userName + " quitted");

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
