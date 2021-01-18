import java.io.*;
import java.net.Socket;
public class mainClient {

	public static void main(String[] args) throws Exception{
		
		Socket socket = new Socket("localhost",8096);
		DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		DataInputStream input = new DataInputStream(socket.getInputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String request = " ";
		String response = " ";
		
		while(!request.equals("end")) {
			System.out.println("Write a request to the server: ");
			request = reader.readLine();
			output.writeUTF(request);
			output.flush();
			response = input.readUTF();
			System.out.println("Response from the server: " +response);
		}
		socket.close();
	}

}


