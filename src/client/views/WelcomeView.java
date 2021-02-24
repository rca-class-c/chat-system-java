package client.views;

import client.ChatClient;
import client.interfaces.ProfileRequestData;
import client.interfaces.UserResponseDataDecoder;
import client.interfaces.Request;
import client.interfaces.ResponseDataSuccessDecoder;
import client.views.components.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.AuthInput;
import server.models.User;
import utils.CommonUtil;
import utils.ConsoleColor;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;

public class WelcomeView {
    /**
     * This a function that takes user login data
     * @AUTHOR: Phinah Mahoro
     */
    public static void Login(ChatClient client, PrintWriter writer, BufferedReader reader) throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        Component.pageTitleView("LOGIN TO CLASS_C CHAT");

        CommonUtil.addTabs(12, false);
        System.out.print("Your username: ");
        String userName = scanner.nextLine();

        CommonUtil.addTabs(12, false);
        System.out.print("Your password: ");
        Console cons = System.console();
        String password = scanner.nextLine();

        ObjectMapper objectMapper = new ObjectMapper();
        AuthInput loginData = new AuthInput(userName,password);
        String url = "users/login";
        Request request = new Request(loginData,url);

        String LoginDataAsString = objectMapper.writeValueAsString(request);

        writer.println(LoginDataAsString);

        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()){
            JsonNode data = objectMapper.readTree(response.getData());
            client.setUserid(data.get("userID").asInt());
            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
            System.out.print(" Your login was successful ");
            CommonUtil.resetColor();
            new UserView(client.getUserid(), writer, reader).viewOptions();
        }
        else{
            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.BackgroundColor.RED_BACKGROUND);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
            System.out.print("  Invalid Username or Password  ");
            CommonUtil.resetColor();
        }
    }

    public static void VerificationCode(ChatClient client, PrintWriter writer, BufferedReader reader){
        Scanner scanner = new Scanner(System.in);
        Component.pageTitleView("Invitation Code Verifier.");
        try {
            System.out.println("");
            CommonUtil.addTabs(10, false);
            System.out.print("Enter the verification code: ");
            int code = scanner.nextInt();
            scanner.nextLine();

            String  key= "users/verify";
            Request request = new Request(new ProfileRequestData(code),key);
            String requestAsString = new ObjectMapper().writeValueAsString(request);
            writer.println(requestAsString);
            ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
            if(response.isSuccess()){
                CommonUtil.addTabs(10, true);
                CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
                CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
                System.out.print(" Code verification worked out with success! ");
                CommonUtil.resetColor();
                Signup(client, writer, reader);
            }
            else{
                CommonUtil.addTabs(10, true);
                CommonUtil.useColor(ConsoleColor.BackgroundColor.RED_BACKGROUND);
                CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
                System.out.print(" Invalid Verification code ");
                CommonUtil.resetColor();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public static void Signup(ChatClient client, PrintWriter writer, BufferedReader reader) throws  IOException {
        Scanner scanner = new Scanner(System.in);
        Component.pageTitleView("CREATE ACCOUNT IN CLASS_C CHAT");


        CommonUtil.addTabs(10, false);
        System.out.print("Enter your Username: ");
        String username = scanner.nextLine();

        CommonUtil.addTabs(10, false);
        System.out.print("Enter your FirstName: ");
        String firstName = scanner.nextLine();

        CommonUtil.addTabs(10, false);
        System.out.print("Enter your LastName: ");
        String lastName = scanner.nextLine();

        CommonUtil.addTabs(10, false);
        System.out.print("Enter your Email: ");
        String email = scanner.nextLine();
        String gender = "";
        do {
            CommonUtil.addTabs(10, false);
            System.out.print("Enter your Gender[male,female]: ");
            gender = scanner.nextLine();
            if(!gender.equals("male") && !gender.equals("female")){
                CommonUtil.addTabs(10, false);
                System.out.println(gender +"Gender not valid");
            }
        }while(!gender.equals("male") && !gender.equals("female"));


        CommonUtil.addTabs(10, false);
        System.out.print("Enter your DOB: ");
        String dob = scanner.nextLine();

        CommonUtil.addTabs(10, false);
        System.out.print("Enter your Password: ");
        String password = scanner.nextLine();
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User(firstName,lastName,password,email,dob,username,gender,1,"ACTIVE");
        String key = "users/register";
        Request request = new Request(user,key);

        String requestAsString = objectMapper.writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()){
            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
            System.out.print(" Your account was created successfully ");
            CommonUtil.resetColor();

            new UserView(client.getUserid(),writer,reader).viewOptions();
        }
        else{
            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.BackgroundColor.RED_BACKGROUND);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
            System.out.print("  Account not created, try again! ");
            CommonUtil.resetColor();
        }
    }
}
