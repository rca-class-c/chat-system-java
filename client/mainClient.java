// import java.io.*;
// import java.net.Socket;
// public class mainClient {

// 	public static void main(String[] args) throws Exception{
		
// 		Socket socket = new Socket("localhost",8096);
// 		DataOutputStream output = new DataOutputStream(socket.getOutputStream());
// 		DataInputStream input = new DataInputStream(socket.getInputStream());
// 		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
// 		String request = " ";
// 		String response = " ";
		
// 		while(!request.equals("end")) {
// 			System.out.println("Write a request to the server: ");
// 			request = reader.readLine();
// 			output.writeUTF(request);
// 			output.flush();
// 			response = input.readUTF();
// 			System.out.println("Response from the server: " +response);
// 		}
// 		socket.close();
// 	}

// }


// Java implementation for a client 
// Save file as Client.java 

import java.io.*; 
import java.net.*; 
import java.util.Scanner; 

// Client class 
public class mainClient 
{ 
	public static void main(String[] args) throws IOException 
	{ 
		try
		{ 
			Scanner scn = new Scanner(System.in); 
			
			// getting localhost ip 
			InetAddress ip = InetAddress.getByName("localhost"); 
	
			// establish the connection with server port 5056 
			Socket s = new Socket(ip, 5056); 
	
			// obtaining input and out streams 
			DataInputStream dis = new DataInputStream(s.getInputStream()); 
			DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
	
			// the following loop performs the exchange of 
			// information between client and client handler 
			while (true) 
			{ 
				System.out.println(dis.readUTF()); 
				String tosend = scn.nextLine(); 
				dos.writeUTF(tosend); 
				
				// If client sends exit,close this connection 
				// and then break from the while loop 
				if(tosend.equals("Exit")) 
				{ 
					System.out.println("Closing this connection : " + s); 
					s.close(); 
					System.out.println("Connection closed"); 
					break; 
				} 
				
				// printing date or time as requested by client 
				String received = dis.readUTF(); 
				System.out.println(received); 
			} 
			
			// closing resources 
			scn.close(); 
			dis.close(); 
			dos.close(); 
		}catch(Exception e){ 
			e.printStackTrace(); 
		} 
	} 
} 
