// import java.io.*;
// import java.net.ServerSocket;
// import java.net.Socket;

// public class mainServer {

// 	public static void main(String[] args) throws Exception{
		

	
		
	
// 		while(true){
// 			ServerSocket serverSocket = new ServerSocket(8096);
		
// 			System.out.println("Server started");
// 			System.out.println("Waiting for a client ...");
			
// 		 Socket socket = serverSocket.accept();
// 		 System.out.println("Client accepted"); 
		 
// 		 DataOutputStream output = new DataOutputStream(socket.getOutputStream());
// 		 DataInputStream input = new DataInputStream(socket.getInputStream());
		 
// 		 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
// 		 String request = " ";
// 		 String response = " ";
			
// 		while(!request.equals("end")) {
// 			request = input.readUTF();
// 			System.out.println("Client request : " + request);
// 			System.out.println("Write response : " + response);
// 			response = reader.readLine();
// 			output.writeUTF(response);
// 			System.out.println("Server response : " + response);
			
			
// 		}
// 		input.close();
// 		serverSocket.close();
// 	}
		
		
// 	}

// }



 // Java implementation of Server side 
// It contains two classes : Server and ClientHandler 
// Save file as Server.java 

import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*; 

// Server class 
public class mainServer 
{ 
	public static void main(String[] args) throws IOException 
	{ 
		// server is listening on port 5056 
		ServerSocket ss = new ServerSocket(5056); 
		
		// running infinite loop for getting 
		// client request 
		while (true) 
		{ 
			Socket s = null; 
			
			try
			{ 
				// socket object to receive incoming client requests 
				s = ss.accept(); 
				
				System.out.println("A new client is connected : " + s); 
				
				// obtaining input and out streams 
				DataInputStream dis = new DataInputStream(s.getInputStream()); 
				DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
				
				System.out.println("Assigning new thread for this client"); 

				// create a new thread object 
				Thread t = new ClientHandler(s, dis, dos); 

				// Invoking the start() method 
				t.start(); 
				
			} 
			catch (Exception e){ 
				s.close(); 
				e.printStackTrace(); 
			} 
		} 
	} 
} 

// ClientHandler class 
class ClientHandler extends Thread 
{ 
	
	final DataInputStream dis; 
	final DataOutputStream dos; 
	final Socket s; 
	

	// Constructor 
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) 
	{ 
		this.s = s; 
		this.dis = dis; 
		this.dos = dos; 
	} 

	@Override
	public void run() 
	{ 
		String received;  
		while (true) 
		{ 
			try { 

				// Ask user what he wants 
				dos.writeUTF("Server..."); 
				
				// receive the answer from client 
				received = dis.readUTF(); 
				
				if(received.equals("Exit")) 
				{ 
					System.out.println("Client " + this.s + " sends exit..."); 
					System.out.println("Closing this connection."); 
					this.s.close(); 
					System.out.println("Connection closed"); 
					break; 
				} 
				
				// creating Date object 
				//Date date = new Date(); 

				
				// write on output stream based on the 
				// answer from the client 
				// switch (received) { 
				dos.writeUTF(received);
				// 	case "Date" : 
				// 		toreturn = fordate.format(date); 
				// 		dos.writeUTF(toreturn); 
				// 		break; 
						
				// 	case "Time" : 
				// 		toreturn = fortime.format(date); 
				// 		dos.writeUTF(toreturn); 
				// 		break; 
						
				// 	default: 
				// 		dos.writeUTF("Invalid input"); 
				// 		break; 
				// } 
			} catch (IOException e) { 
				e.printStackTrace(); 
			} 
		} 
		
		try
		{ 
			// closing resources 
			this.dis.close(); 
			this.dos.close(); 
			
		}catch(IOException e){ 
			e.printStackTrace(); 
		} 
	} 
} 
