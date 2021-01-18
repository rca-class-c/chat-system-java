import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class mainServer {

	public static void main(String[] args) throws Exception{
		

	
		
	
		
			ServerSocket serverSocket = new ServerSocket(8096);
		
			System.out.println("Server started");
			System.out.println("Waiting for a client ...");
			
		 Socket socket = serverSocket.accept();
		 System.out.println("Client accepted"); 
		 
		 DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		 DataInputStream input = new DataInputStream(socket.getInputStream());
		 
		 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		 String request = " ";
		 String response = " ";
			
		while(!request.equals("end")) {
			request = input.readUTF();
			System.out.println("Client request : " + request);
			System.out.println("Write response : " + response);
			response = reader.readLine();
			output.writeUTF(response);
			System.out.println("Server response : " + response);
			
			
		}
		input.close();
		serverSocket.close();
	

		
		
	}

}

