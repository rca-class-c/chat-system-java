package client;

import client.views.View;
import utils.CommonUtil;

import java.io.*;
import java.net.Socket;

/**
 * This is the file for sending and handling request from the client to the server
 * and vice versa
 * @AUTHOR: Kobusinge Shallon
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


    public void run(Socket socket) {
        int choice = 0;
        do {
            try {
                View.WelcomeView(client, writer, reader);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
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