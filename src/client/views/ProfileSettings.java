package client.views;

import client.interfaces.UserResponseDataDecoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.User;
import utils.CommonUtil;
import client.interfaces.ProfileRequestData;
import client.interfaces.Request;
import client.interfaces.ResponseDataSuccessDecoder;
import utils.ConsoleColor;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import client.views.components.Component;

/**
 * @AUTHOR: Shallon Kobusinge
 * The view for user updating profile
 * */
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
                updateUser(userid);
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
        String  key= "users/profile";
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

        Component.chooseOptionInputView("Type number to continue: ");
        int choice  = scanner.nextInt();

    }
    public void updateUser(int userid) throws IOException{
        String key= "users/profile";
        Request profileRequest = new Request(new ProfileRequestData(userid),key);
        String requestAsString = new ObjectMapper().writeValueAsString(profileRequest);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder profileResponse = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        Scanner scanner = new Scanner(System.in);

        if(profileResponse.isSuccess()){
            User profile = new UserResponseDataDecoder().returnUserDecoded(profileResponse.getData());
            Component.pageTitleView("MY PROFILE");
            CommonUtil.addTabs(10, false);
            System.out.println("If you don't want to change any of your data, type [-1] ");
            CommonUtil.addTabs(10, false);
            System.out.print("FIRST NAME"+"["+profile.getFname()+"]: ");
            String firstName = scanner.nextLine();
            if(!firstName.equals("-1") && !firstName.equals(profile.getFname())){
                CommonUtil.addTabs(10, false);
                System.out.print("First name changed!");
                profile.setFname(firstName);
                System.out.println();

            }

            CommonUtil.addTabs(10, false);
            System.out.print("LAST NAME"+"["+profile.getLname()+"]:  ");
            String lastName = scanner.nextLine();
            if(!lastName.equals("-1") && !lastName.equals(profile.getLname())){
                CommonUtil.addTabs(10, false);
                System.out.print("Last name changed!");
                profile.setLname(lastName);
                System.out.println();
            }


            CommonUtil.addTabs(10, false);
            System.out.print("USERNAME"+"["+profile.getUsername()+"]: ");
            String username = scanner.nextLine();
            if(!username.equals("-1") && !username.equals(profile.getUsername())){
                CommonUtil.addTabs(10, false);
                System.out.print("Username changed!");
                profile.setUsername(username);
                System.out.println();

            }


            CommonUtil.addTabs(10, false);
            System.out.print("EMAIL"+"["+profile.getEmail()+"]:  ");
            String email = scanner.nextLine();
            if(!email.equals("-1") && !email.equals(profile.getEmail())){
                CommonUtil.addTabs(10, false);
                System.out.print("Email changed!");
                profile.setEmail(email);

            }

            CommonUtil.addTabs(10, false);
            System.out.print("DOB"+"["+profile.getDob()+"]:  ");
            String dob = scanner.nextLine();
            if(!dob.equals("-1") && !dob.equals(profile.getDob())){
                CommonUtil.addTabs(10, false);
                System.out.print("DOB changed!");
                profile.setDob(dob);

            }

            String gender = "";
            do {
            CommonUtil.addTabs(10, false);
            System.out.print("GENDER"+"["+profile.getGender()+"]:  ");
            gender = scanner.nextLine();
            if(!gender.equals("-1") && !gender.equals(profile.getGender())){
                CommonUtil.addTabs(10, true);
                System.out.print("Gender changed!");
                profile.setGender(gender);
                if(!gender.equals("male") && !gender.equals("female")){
                    CommonUtil.addTabs(10, false);
                    System.out.println(gender +"Gender not valid");
                }
            }
            }while(!gender.equals("-1") && !gender.equals("male") && !gender.equals("female"));

            CommonUtil.addTabs(10, false);
            System.out.print("PASSWORD"+"["+profile.getPassword()+"]: ");
            String password = scanner.nextLine();
            if(!password.equals("-1") && !password.equals(profile.getPassword())){
                profile.setPassword(password);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            String updateKey = "users/update";
            Request request = new Request(profile,updateKey);
            String requestUpdateAsString = objectMapper.writeValueAsString(request);
            writer.println(requestUpdateAsString);
            ResponseDataSuccessDecoder updateResponse = new UserResponseDataDecoder().decodedResponse(reader.readLine());
            if(updateResponse.isSuccess()){
                CommonUtil.addTabs(10, true);
                CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
                CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
                System.out.println(" Your account was updated successfully ");
                CommonUtil.resetColor();


            }
            else{
                CommonUtil.addTabs(10, true);
                CommonUtil.useColor(ConsoleColor.BackgroundColor.RED_BACKGROUND);
                CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
                System.out.print("  Account not updated, try again! ");
                CommonUtil.resetColor();
            }
        }
        else{
            System.out.println("No profile found!");
        }

        Component.chooseOptionInputView("Type 1 to edit profile or any other number to go main: ");
        int choice  = scanner.nextInt();
    }

}
