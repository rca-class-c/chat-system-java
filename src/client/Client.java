package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;


public class Client {
    public static void main(String[]args) throws Exception {
        Socket socket = new Socket("localhost", 8000);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String request = "";
        String response = "";

        while(!request.equals("end")) {
            System.out.println("Write a request: ");
            request = reader.readLine();
            output.writeUTF(request);
            output.flush();
            response = input.readUTF();
            System.out.println("Server response:\n");
            System.out.println(response);
        }
        socket.close();
    }
}
