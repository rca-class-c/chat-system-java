//package client.views;
//
//import client.interfaces.ProfileRequestData;
//import client.interfaces.Request;
//import client.interfaces.ResponseDataSuccessDecoder;
//import client.interfaces.UserResponseDataDecoder;
//import client.views.components.Component;
//import server.models.User;
//import utils.CommonUtil;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class ChannelView {
//    public int groupId;
//    public PrintWriter writer;
//    public BufferedReader reader;
//
//    public ChannelView(int groupId, PrintWriter writer, BufferedReader reader) {
//        this.groupId = groupId;
//        this.writer = writer;
//        this.reader = reader;
//    }
//
//    public int getGroupId() {
//        return groupId;
//    }
//
//    public void setGroupId(int groupId) {
//        this.groupId = groupId;
//    }
//
//    public PrintWriter getWriter() {
//        return writer;
//    }
//
//    public void setWriter(PrintWriter writer) {
//        this.writer = writer;
//    }
//
//    public BufferedReader getReader() {
//        return reader;
//    }
//
//    public void setReader(BufferedReader reader) {
//        this.reader = reader;
//    }
//
////        if(response.isSuccess()){
//
////
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
//
//    public void viewChannels() throws IOException{
//
//    }
//}
