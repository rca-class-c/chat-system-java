package server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.requestHandlers.*;
import server.routes.*;
import utils.CommonUtil;
import utils.ConsoleColor;

import java.io.*;
import java.net.Socket;

/**
 * This is a thread that allows many clients to the server as it handles one currently connected and when new one comes any
 * @Author: Didier Munezero
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
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);


            String clientMessage;
            do {
                clientMessage = reader.readLine();
                CommonUtil.addTabs(10, true);
                CommonUtil.useColor(ConsoleColor.RegularColor.YELLOW);
                System.out.println("REQUEST: " + clientMessage);
                CommonUtil.resetColor();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(clientMessage);
                String request_type = jsonNode.get("request_type").asText();
                String data = jsonNode.get("data").toString();

                if(request_type.startsWith("users/")){
                    new UserRoutes(data,writer,objectMapper,server,request_type).Main();
                }
                else if(request_type.startsWith("groups/")){
                    new GroupsRoutes(data,writer,objectMapper,server,request_type).Main();
                }
                else if(request_type.startsWith("messages/")){
                    new MessagesRoutes(data,writer,objectMapper,server,request_type).Main();
                }
                else if(request_type.startsWith("replies/")){
                    new RepliesRoutes(data,writer,objectMapper,server,request_type).Main();
                }
                else if(request_type.startsWith("file/")){
                    new FileRoutes(data,writer,objectMapper,server,request_type).Main();
                }
                else if(request_type.equals("passwordReset_initiate")){
                    new PasswordResetsRequestHandler().handlePasswordResetInitiation(data,writer,objectMapper);
                }
                else if(request_type.equals("passwordReset")){
                    new PasswordResetsRequestHandler().handlePasswordReset(data,writer,objectMapper);
                }
                else if(request_type.equals("auth_login")){
                    new AuthenticationRequestHandler().handleLogin(data,writer,objectMapper);
                }
                else if(request_type.startsWith("file/")){
                    new FileRoutes(data,writer,objectMapper,server,request_type).Main();
                }
//                else if(request_type.equals("get_my_notifications")){
//                    new MessageRequestHandler().HandleViewNotifications(data,writer,objectMapper);
//                }
//
                else{
                    writer.println("Request type not known");
                }
            } while (!clientMessage.equals("bye"));
            socket.close();
        } catch (Exception ex) {
            System.out.println();
            CommonUtil.addTabs(10, false);
            CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.RED_BACKGROUND_BRIGHT);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);

            if (ex.getMessage().equals("Connection reset")) {
                System.out.print(" User Disconnected  ");
            } else {
                System.out.print(ex.getMessage());
            }

            CommonUtil.resetColor();
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