package client;

import client.views.View;
import client.views.components.Component;
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
            Component.showErrorMessage(ex.getMessage());
        }
        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            Component.showErrorMessage(ex.getMessage());
        }
    }


    public void run(Socket socket) {
        int choice = 0;
        do {
            try {
                View.WelcomeView(client, writer, reader);
            }
            catch (Exception e) {
                Component.showErrorMessage(e.getMessage());
            }
        } while (choice != -1);
        try {
            socket.close();
        } catch (IOException ex) {
            Component.showErrorMessage(ex.getMessage());
        }
    }
}