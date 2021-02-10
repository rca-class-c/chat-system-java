package client.views;

import client.interfaces.UserResponseDataDecoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.User;
import utils.CommonUtil;
import client.interfaces.ProfileRequestData;
import client.interfaces.Request;
import client.interfaces.ResponseDataSuccessDecoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import client.views.components.Component;

public class ProfileSettings {
    public int userid;
    public PrintWriter writer;
    public BufferedReader reader;

    public ProfileSettings(int userid, PrintWriter writer, BufferedReader reader) {
        this.userid = userid;
        this.writer = writer;
        this.reader = reader;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
    Scanner scanner = new Scanner(System.in);
    public void viewProfileSettingsOptions() throws IOException {
        Component.pageTitleView("USER Dashboard");
        int choice = 0;
        do {
            CommonUtil.addTabs(10, true);
            System.out.println("1. VIEW PROFILE");
            CommonUtil.addTabs(10, false);
            System.out.println("2. EDIT PROFILE");
            Component.chooseOptionInputView("Choose an option: ");
            choice  = scanner.nextInt();
            if(choice == 1){
                MyProfile();
            }
            else if(choice == 2){

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
    public void MyProfile() throws IOException {
        String  key= "get_profile";
        Request request = new Request(new ProfileRequestData(userid),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()){
            User profile = new UserResponseDataDecoder().returnUserDecoded(response.getData());
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
            System.out.println("PASSWORD:   "+profile.getPassword());

        }
        else{
            System.out.println("No profile found!");
        }

        Component.chooseOptionInputView("Type 1 to edit profile or any other number to go main: ");
        int choice  = scanner.nextInt();

    }

}
