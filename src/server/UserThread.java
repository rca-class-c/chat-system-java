package server;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 * This thread handles connection for each connected client, so the server
 * can handle multiple clients at the same time.
 *
 */
public class UserThread extends Thread {
    private Socket socket;
    private ChatServer server;
    private PrintWriter writer;
    public UserThread(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
    }
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//            Scanner reader = new Scanner(System.in);
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);


            //ok now caught after the username is flushed server sets it good now
            printUsers();
            String userName = reader.readLine();
            server.addUserName(userName);
            String serverMessage = "New user connected: " + userName;
            //passing message and user to exclude who is the sender
            server.broadcast(serverMessage, this);
            String clientMessage;
            do {
                clientMessage = reader.readLine();
                serverMessage = "[" + userName + "]: " + clientMessage;
                server.broadcast(serverMessage, this);
            } while (!clientMessage.equals("bye"));
            server.removeUser(userName, this);
            socket.close();
            //passing message to remaining users that one has quitted
            serverMessage = userName + " has quitted.";
            server.broadcast(serverMessage, this);
        } catch (IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    /**
     * Sends a list of online users to the newly connected user.
     */
    void printUsers() {
        System.out.println(server.hasUsers());
        if (server.hasUsers()) {
            writer.println("Connected users: " + server.getUserNames());
        } else {
            writer.println("No other users connected");
        }
    }
    /**
     * Sends a message to the client.
     */
    void sendMessage(String message) {
        writer.println(message);
    }
}