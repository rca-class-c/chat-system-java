package client.views;

import client.interfaces.ProfileRequestData;
import client.interfaces.Request;
import client.interfaces.ResponseDataSuccessDecoder;
import client.interfaces.UserResponseDataDecoder;
import client.views.components.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.User;
import utils.CommonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;

//import utils.CommonUtil;

public class UserView {
    public int userId;
    public PrintWriter writer;
    public BufferedReader reader;

    public UserView(int userId, PrintWriter writer, BufferedReader reader) {
        this.userId = userId;
        this.writer = writer;
        this.reader = reader;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    Scanner scanner = new Scanner(System.in);
    public void viewOptions() throws  IOException{
        Component.pageTitleView("USER Dashboard");
        int choice = 0;
        do {
            CommonUtil.addTabs(10, true);
            System.out.println("1. SEND MESSAGE");
            CommonUtil.addTabs(10, false);
            System.out.println("2. CHANNEL SETTINGS");
            CommonUtil.addTabs(10, false);
            System.out.println("3. NOTIFICATIONS");
            CommonUtil.addTabs(10, false);
            System.out.println("4. USERS LIST");
            CommonUtil.addTabs(10, false);
            System.out.println("5. ADMIN ACTIONS");
            CommonUtil.addTabs(10, false);
            System.out.println("6. PROFILE SETTINGS");
            CommonUtil.addTabs(10, false);
            System.out.println("44. LOGOUT");
            CommonUtil.addTabs(10, false);
            System.out.println("55. QUIT");
            Component.chooseOptionInputView("Choose an option: ");
            choice  = scanner.nextInt();
            if(choice == 1){
                new SendMessageView(userId, writer, reader).OptionsView();
            }
            else if(choice == 2){
                new ChannelSettings(userId,writer,reader).channelMenu();
            }
            else if(choice == 3){
                new SendMessageView(userId, writer, reader).ViewNotifications();
            }
            else if(choice == 5){
                new AdminAction();
            }
            else if(choice == 6){
               new ProfileSettings(userId,writer,reader).viewProfileSettingsOptions();
            }
            else if(choice == 4){
                allActiveUsers();
            }
            else if(choice == 44){
                CommonUtil.addTabs(10, true);
                System.out.println("Going back");
                break;
            }
            else if(choice == 55){
                CommonUtil.addTabs(10, true);
                CommonUtil.useColor("\u001b[1;31m");
                System.out.println("SYSTEM CLOSED !");
                System.exit(1);
            }
        }while(choice != 44 && choice != 55);

    }

    public void allActiveUsers() throws IOException {
        String  key= "get_users_list";
        Request request = new Request(new ProfileRequestData(userId),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        Component.pageTitleView("USERS LIST");
        if(response.isSuccess()){
            User[] users = new UserResponseDataDecoder().returnUsersListDecoded(response.getData());
            CommonUtil.addTabs(10, true);
            for (User user : users) {
                System.out.println(user.getUserID()+". "+user.getFname()+" "+user.getLname());
                CommonUtil.addTabs(10, false);
            }
        }else {
            CommonUtil.addTabs(10, true);
            System.out.println("Failed to read users list, sorry for the inconvenience");
        }
        System.out.println("");
        Component.chooseOptionInputView("Type any number to go to main page: ");
        int choice  = scanner.nextInt();
    }


    public static  void sendInvitations() throws ClassNotFoundException,  SQLException {
        Scanner scanner = new Scanner(System.in);

        Component.pageTitleView("Admin Send An Invitation ");

        CommonUtil.addTabs(10, false);
        System.out.print("Enter Your Email: ");
        String email = scanner.nextLine();

        CommonUtil.addTabs(10, false);
        System.out.print("Enter your Password: ");
        Integer password = scanner.nextInt();
//        try {
//            sendInvitations.sendingInvitations(email,password);
//        }
//        catch (SQLException  e){
//            System.out.println(e);
//        }

    }

}
