package client;

import server.models.ActiveUser;

import java.io.*;
import java.net.*;
/**
 * This thread is responsible for reading server's input and printing it
 * to the console.
 * It runs in an infinite loop until the client disconnects from the server.
 */

public class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private ChatClient client;
    public ReadThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;
        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public void run() {
        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String response = reader.readLine();
                System.out.println(response);
                if(response.equals("true")){
                    System.out.println("Getting db logging results");
                    InputStream inputStream = socket.getInputStream();
                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                    ActiveUser activeUser = (ActiveUser) objectInputStream.readObject();

                }
                System.out.println("\n" + response);
//                String response = reader.readLine();
//                System.out.println("\n" + response);
//                // prints the username after displaying the server's message
//                if (client.getUserName() != null) {
//                    System.out.print("[" + client.getUserName() + "]: ");
//                }
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}