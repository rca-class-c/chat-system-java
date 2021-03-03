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
        Component.pageTitleView("Profile Settings");
        int choice = 0;
        do {
            CommonUtil.addTabs(11, true);
            System.out.println("1. View your profile");
            CommonUtil.addTabs(11, false);
            System.out.println("2. Edit your profile");
            CommonUtil.addTabs(11, false);
            System.out.println(ConsoleColor.RegularColor.BLUE + "44" + ConsoleColor.RESET + ". Back");
            CommonUtil.addTabs(11, false);
            System.out.println(ConsoleColor.RegularColor.RED + "55" + ConsoleColor.RESET + ". Quit");
            Component.chooseOptionInputView("Choose an option: ");
            choice  = scanner.nextInt();
            if(choice == 1){
                MyProfile();
            }
            else if(choice == 2){
                updateUser(userid);
            }

            else if(choice == 44){
                break;
            }
            else if(choice == 55){
                Component.closeUIView();
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
            Component.pageTitleView("VIEW YOUR PROFILE");
            System.out.println();

            CommonUtil.addTabs(11, false);
            Component.listItemView("First name", profile.getFname());

            CommonUtil.addTabs(11, false);
            Component.listItemView("Last name", profile.getLname());

            CommonUtil.addTabs(11, false);
            Component.listItemView("Username", profile.getUsername());

            CommonUtil.addTabs(11, false);
            Component.listItemView("Email", profile.getEmail());

            CommonUtil.addTabs(11, false);
            Component.listItemView("Gender", profile.getGender());

            CommonUtil.addTabs(11, false);
            Component.listItemView("Password", profile.getPassword());

            viewProfileSettingsOptions();
        }
        else{
            Component.alertDangerErrorMessage(11, "No profile found!");
        }


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
            Component.pageTitleView("Edit your PROFILE");
            CommonUtil.addTabs(12, false);
            CommonUtil.useColor(ConsoleColor.HighIntensityColor.CYAN_BRIGHT);
            System.out.println("* Type [-1] to skip field update *");
            System.out.println();

            CommonUtil.addTabs(11, false);
            CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.WHITE_BOLD_BRIGHT);
            System.out.print("First name ");
            CommonUtil.resetColor();

            CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.YELLOW_BOLD_BRIGHT);
            System.out.print("[" + profile.getFname() + "]: ");
            String firstName = scanner.nextLine();

            if(!firstName.equals("-1") && !firstName.equals(profile.getFname())){
                CommonUtil.addTabs(11, false);
                CommonUtil.useColor(ConsoleColor.RegularColor.CYAN);
                System.out.print("First name changed!");
                profile.setFname(firstName);
                System.out.println();
            }

            CommonUtil.addTabs(11, false);
            CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.WHITE_BOLD_BRIGHT);
            System.out.print("Last name ");
            CommonUtil.resetColor();

            CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.YELLOW_BOLD_BRIGHT);
            System.out.print("[" + profile.getLname() + "]: ");
            String lastName = scanner.nextLine();

            if(!lastName.equals("-1") && !lastName.equals(profile.getLname())){
                CommonUtil.addTabs(11, false);
                CommonUtil.useColor(ConsoleColor.RegularColor.CYAN);
                System.out.print("Last name changed!");
                profile.setLname(lastName);
                System.out.println();
            }


            CommonUtil.addTabs(11, false);
            CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.WHITE_BOLD_BRIGHT);
            System.out.print("Username ");
            CommonUtil.resetColor();

            CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.YELLOW_BOLD_BRIGHT);
            System.out.print("[" + profile.getUsername() + "]: ");
            String username = scanner.nextLine();

            if(!username.equals("-1") && !username.equals(profile.getUsername())){
                CommonUtil.addTabs(11, false);
                CommonUtil.useColor(ConsoleColor.RegularColor.CYAN);
                System.out.print("Username changed!");
                profile.setFname(username);
                System.out.println();
            }

            CommonUtil.addTabs(11, false);
            CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.WHITE_BOLD_BRIGHT);
            System.out.print("Email ");
            CommonUtil.resetColor();

            CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.YELLOW_BOLD_BRIGHT);
            System.out.print("[" + profile.getEmail() + "]: ");
            String email = scanner.nextLine();

            if(!email.equals("-1") && !email.equals(profile.getFname())){
                CommonUtil.addTabs(11, false);
                CommonUtil.useColor(ConsoleColor.RegularColor.CYAN);
                System.out.print("Email changed!");
                profile.setFname(email);
                System.out.println();
            }


            CommonUtil.addTabs(11, false);
            CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.WHITE_BOLD_BRIGHT);
            System.out.print("DOB ");
            CommonUtil.resetColor();

            CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.YELLOW_BOLD_BRIGHT);
            System.out.print("[" + profile.getDob() + "]: ");
            String dob = scanner.nextLine();

            if(!dob.equals("-1") && !dob.equals(profile.getDob())){
                CommonUtil.addTabs(11, false);
                CommonUtil.useColor(ConsoleColor.RegularColor.CYAN);
                System.out.print("DOB changed!");
                profile.setFname(dob);
                System.out.println();
            }


            CommonUtil.addTabs(11, false);
            CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.WHITE_BOLD_BRIGHT);
            System.out.print("Gender ");
            CommonUtil.resetColor();

            CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.YELLOW_BOLD_BRIGHT);
            System.out.print("[" + profile.getGender() + "]: ");
            String gender = scanner.nextLine();

            if(!gender.equals("-1") && !gender.equals(profile.getGender())){
                CommonUtil.addTabs(11, false);
                CommonUtil.useColor(ConsoleColor.RegularColor.CYAN);
                System.out.print("Gender changed!");
                profile.setFname(gender);
                if(!gender.equals("male") && !gender.equals("female")){
                    CommonUtil.addTabs(10, false);
                    System.out.println(gender +"Gender not valid");
                }
                System.out.println();
            }


            CommonUtil.addTabs(11, false);
            CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.WHITE_BOLD_BRIGHT);
            System.out.print("Password ");
            CommonUtil.resetColor();

            CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.YELLOW_BOLD_BRIGHT);
            System.out.print("[" + profile.getPassword() + "]: ");
            String password = scanner.nextLine();

            if(!password.equals("-1") && !password.equals(profile.getPassword())){
                CommonUtil.addTabs(11, false);
                CommonUtil.useColor(ConsoleColor.RegularColor.CYAN);
                System.out.print("Password changed!");
                profile.setFname(password);
                System.out.println();
            }

            ObjectMapper objectMapper = new ObjectMapper();
            String updateKey = "users/update";
            System.out.println(profile.getUserID());
            Request request = new Request(profile,updateKey);
            String requestUpdateAsString = objectMapper.writeValueAsString(request);
            writer.println(requestUpdateAsString);
            ResponseDataSuccessDecoder updateResponse = new UserResponseDataDecoder().decodedResponse(reader.readLine());
            if(updateResponse.isSuccess()){
                Component.alertSuccessMessage(11, "Your account was updated successfully");
            }
            else{
                Component.alertDangerErrorMessage(11, "Account not updated, try again!");
            }
        }
        else{
            Component.alertDangerErrorMessage(11, "No profile found!");

        }
        viewProfileSettingsOptions();
    }

}
