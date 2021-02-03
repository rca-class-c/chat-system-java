package server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[]args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4008);
            Socket socket = serverSocket.accept();

            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String request = "";
            String response = "";

            while(!request.equals("end")) {
                request = input.readUTF();
                System.out.println("Client requested: "+request);

                System.out.println("Writing response: ");
                response = reader.readLine();

                output.writeUTF(response);
            }
            input.close();
            serverSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}