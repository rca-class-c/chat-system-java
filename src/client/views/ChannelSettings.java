package client.views;

import client.interfaces.ProfileRequestData;
import client.interfaces.Request;
import client.views.components.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.CommonUtil;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ChannelSettings {
    public int groupId;
    public int userId;
    public PrintWriter writer;
    public BufferedReader reader;

    public ChannelSettings(int userId, PrintWriter writer, BufferedReader reader) {

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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public static void channelMenu(){
        Component.pageTitleView("CHANNEL SETTINGS");
        CommonUtil.addTabs(10, true);
        System.out.println("1. View channels");
        CommonUtil.addTabs(10, false);
        System.out.println("2. Create channel");
        CommonUtil.addTabs(10, false);
        System.out.println("0. Go back");
        CommonUtil.addTabs(10, false);
        System.out.println("4. Quit");
        while(true) {
            try {
                CommonUtil.addTabs(10, false);
                CommonUtil.useColor("\u001b[43m");
                System.out.print("  ");
                CommonUtil.resetColor();
                CommonUtil.useColor("\u001b[0;33m");
                System.out.print(" Choose an option: ");
                CommonUtil.resetColor();
                int choice = AdminAction.insertAdminChoice();
                switch(choice) {
                    case 1:
                        System.out.println("channels");
                        break;
                    case 2:
                        System.out.println("chann");
                        break;
                    case 0:
                        System.out.println("you should return back");
                        break;
                    case 4:
                        CommonUtil.addTabs(10, true);
                        CommonUtil.useColor("\u001b[1;31m");
                        System.out.println("SYSTEM CLOSED !");
                        System.exit(1);
                        break;
                    default:
                        CommonUtil.addTabs(10, false);
                        CommonUtil.useColor("\u001b[1;31m");
                        System.out.println("Enter a valid choice (1,5): ");
                        CommonUtil.resetColor();
                }
            } catch (Exception var2) {
                CommonUtil.addTabs(10, false);
                CommonUtil.useColor("\u001b[1;31m");
                System.out.println("is incorrect input");
                CommonUtil.resetColor();
            }
        }
    }
    ////    public void MyProfile() throws IOException {
////        String  key= "get_profile";
////        Request request = new Request(new ProfileRequestData(userId),key);
////        String requestAsString = new ObjectMapper().writeValueAsString(request);
////        writer.println(requestAsString);
////        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
////            User profile = new UserResponseDataDecoder().returnUserDecoded(response.getData());
////            Component.pageTitleView("MY PROFILE");
////            CommonUtil.addTabs(10, false);
////            System.out.println("FIRST NAME:  "+profile.getFname());
////            CommonUtil.addTabs(10, false);
////            System.out.println("LAST NAME:  "+profile.getLname());
////            CommonUtil.addTabs(10, false);
////            System.out.println("USERNAME:  "+profile.getUsername());
////            CommonUtil.addTabs(10, false);
////            System.out.println("EMAIL:  "+profile.getEmail());
////            CommonUtil.addTabs(10, false);
////            System.out.println("GENDER:  "+profile.getGender());
////            CommonUtil.addTabs(10, false);
////            System.out.println("PASSWORD:   "+profile.getPassword());
////
////        }
////        else{
////            System.out.println("No profile found!");
////        }
////
////        Component.chooseOptionInputView("Type 1 to edit profile or any other number to go main: ");
////        int choice  = scanner.nextInt();
////
////    }
    public void listChannel() throws JsonProcessingException {
        String  key= "get_my_groups";
        Request request = new Request(new ProfileRequestData(userId),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
       writer.println(requestAsString);


    }
}
