package client.views;

import client.interfaces.DecodeResponse;
import client.interfaces.ProfileRequestData;
import client.interfaces.Request;
import client.interfaces.ResponseDecoded;
import client.views.components.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.User;
<<<<<<< HEAD
import utils.*;
//import utils.CommonUtil;
=======
import server.services.sendInvitations;
import utils.CommonUtil;
>>>>>>> 3d1efe3f5554294736d40116c52904cf9081568f

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;

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
            System.out.println("5. PROFILE SETTINGS");
            CommonUtil.addTabs(10, false);
            System.out.println("6. LOGOUT");
            Component.chooseOptionInputView("Choose an option: ");
            choice  = scanner.nextInt();
            if(choice == 1){
                new SendMessageView(userId,writer,reader).OptionsView();
            }
            else if(choice == 5){
                MyProfile();
            }
            else if(choice == 4){
                allActiveUsers();
            }
        }while(choice != 6);

    }

    public void allActiveUsers() throws IOException {
        String  key= "get_users_list";
        Request request = new Request(new ProfileRequestData(userId),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDecoded response = new DecodeResponse().decodedResponse(reader.readLine());
        Component.pageTitleView("USERS LIST");
        if(response.isSuccess()){
            User[] users = new DecodeResponse().returnUsersListDecoded(response.getData());
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

    public void MyProfile() throws IOException {
        String  key= "get_profile";
        Request request = new Request(new ProfileRequestData(userId),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDecoded response = new DecodeResponse().decodedResponse(reader.readLine());
       if(response.isSuccess()){
           User profile = new DecodeResponse().returnUserDecoded(response.getData());
           Component.pageTitleView("MY PROFILE");
           CommonUtil.addTabs(10, false);
           System.out.println("FIRST NAME:  "+profile.getFname());
           CommonUtil.addTabs(10, false);
           System.out.println("LAST NAME:  "+profile.getLname());
           CommonUtil.addTabs(10, false);
           System.out.println("USERNAME:  "+profile.getUsername());
           CommonUtil.addTabs(10, false);
           System.out.println("EMAIL:  "+profile.getEmail());
           CommonUtil.addTabs(10, false);
           System.out.println("GENDER:  "+profile.getGender());
           CommonUtil.addTabs(10, false);
           System.out.println("PASSWORD:  ***********");

       }
       else{
           System.out.println("No profile found!");
       }

        Component.chooseOptionInputView("Type 1 to edit profile or any other number to go main: ");
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
        String password = scanner.nextLine();
        try {
            sendInvitations.sendingInvitations(email,password);
        }
        catch (SQLException | MessagingException e){
            System.out.println(e);
        }

    }

}
