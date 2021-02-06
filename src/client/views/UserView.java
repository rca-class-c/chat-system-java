package client.views;

import client.interfaces.ProfileRequestData;
import client.views.components.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.User;
import utils.CommonUtil;
import client.interfaces.Request;
import client.interfaces.ResponseDecoded;
import client.interfaces.DecodeResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
            System.out.println("1. MY PROFILE");
            CommonUtil.addTabs(10, false);
            System.out.println("2. CHAT SET");
            CommonUtil.addTabs(10, false);
            System.out.println("3. USERS LIST");
            CommonUtil.addTabs(10, false);
            System.out.println("5. NOTIFICATIONS");
            CommonUtil.addTabs(10, false);
            System.out.println("6. LOGOUT");
            Component.chooseOptionInputView("Choose an option: ");
            choice  = scanner.nextInt();
            if(choice == 1){
                MyProfile();
            }
        }while(choice != 6);

    }

    public void allActiveUsers(){
        Component.pageTitleView("ACTIVE USERS LIST");
        CommonUtil.addTabs(10, true);
        System.out.println("1. chanelle740");
        CommonUtil.addTabs(10, false);
        System.out.println("2. edine-noella");
        CommonUtil.addTabs(10, false);
        System.out.println("3. divin-irakiza");
        CommonUtil.addTabs(10, false);
        System.out.println("4. Hortance-irakoze");
        CommonUtil.addTabs(10, false);
        System.out.println("5. Loraine");
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

        Component.chooseOptionInputView("Type any number to go to main page: ");
        int choice  = scanner.nextInt();

    }
}
