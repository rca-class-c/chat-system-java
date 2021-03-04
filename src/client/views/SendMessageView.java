package client.views;

import client.interfaces.*;
import client.simplifiers.RequestSimplifiers;
import client.views.components.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.File;
import server.models.Group;
import server.models.Messages;
import server.models.User;
import server.models.enums.FileSizeTypeEnum;
import utils.ChatBetweenTwo;
import utils.CommonUtil;
import utils.ConsoleColor;
import utils.FileUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SendMessageView {
    public int userId;
    public PrintWriter writer;
    public BufferedReader reader;
    public int receiver;
    User current;
    User chattingWith;
    Scanner scanner = new Scanner(System.in);

    public SendMessageView(int userId, PrintWriter writer, BufferedReader reader) {
        this.userId = userId;
        this.writer = writer;
        this.reader = reader;
    }

    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }

    public User getChattingWith() {
        return chattingWith;
    }

    public void setChattingWith(User chattingWith) {
        this.chattingWith = chattingWith;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public void OptionsView() throws IOException {
            this.setCurrent(new RequestSimplifiers(writer,reader).goGetUser(userId));

        int choice = 0;
        while(choice != 55 && choice != 44) {
            Component.pageTitleView("Send a Message");

            CommonUtil.addTabs(11, true);
            System.out.println("1. Direct Message");
            CommonUtil.addTabs(11, false);
            System.out.println("2. Message a group");
            CommonUtil.addTabs(11, false);
            System.out.println("44. Go back");
            CommonUtil.addTabs(11, false);
            System.out.println("55. Quit");
            Component.chooseOptionInputView("Choose an option: ");

                choice = Component.getChooseOptionChoice();
                try {
                    switch (choice) {
                        case 1 -> {
                            DirectMessageView();
                        }
                        case 2 -> {
                            GroupMessageView();
                        }
                        case 44->{
                            CommonUtil.addTabs(10, true);
                            System.out.println("Going back");
                        }
                        case 55->{
                            Component.closeUIView();
                        }
                        default -> {
                            choice = -1;
                            Component.showErrorMessage("Enter a valid choice (1, 2): ");

                        }
                    }
                } catch (Exception e) {
                    Component.showErrorMessage(e.getMessage());
                }
        }
    }
    public void DirectMessageView() {
        int choice = 10;
        while(choice != 55 && choice != 44) {
            Component.pageTitleView("Direct Message");

            CommonUtil.addTabs(11, true);
            System.out.println("1. List all Users");
            CommonUtil.addTabs(11, false);
            System.out.println("2. Search a User (names)");
            CommonUtil.addTabs(11, false);
            System.out.println("3. Enter a user ID");
            CommonUtil.addTabs(11, false);
            System.out.println("44. Go back");
            CommonUtil.addTabs(11, false);
            System.out.println("55. Quit");

            Component.chooseOptionInputView("Choose an option: ");

            choice = Component.getChooseOptionChoice();
                try {
                    CommonUtil.resetColor();
                    switch (choice) {
                        case 1 -> {
                            checkUserToSendMessage(allActiveUsers());
                        }
                        case 2 -> {
                            checkUserToSendMessage(SearchUserView());
                        }
                        case 3 -> {
                            UserIdView();
                        }
                        case 44->{
                            break;
                        }
                        case 55->{
                            Component.closeUIView();
                            break;
                        }
                        default -> {
                            Component.showErrorMessage("Enter a valid choice (1, 2): ");
                        }
                    }
                } catch (Exception e) {
                    Component.showErrorMessage(e.getMessage());
                }
                if(choice == 44){
                    break;
                }
        }
    }

    public  void GroupMessageView() {
        Component.pageTitleView("Group Message");

        CommonUtil.addTabs(11, true);
        System.out.println("1. List all Groups");
        CommonUtil.addTabs(11, false);
        System.out.println("2. Search a Group (name)");
        CommonUtil.addTabs(11, false);
        System.out.println("3. Enter a group ID");

        Component.chooseOptionInputView("Choose an option: ");

        int action;
        do {
            action = Component.getChooseOptionChoice();
            try {
                CommonUtil.resetColor();
                switch (action) {
                    case 1 -> {
                        GetAllGroupsView();
                    }
                    case 2 -> {
                        SearchGroupView();
                    }
                    case 3 -> {
                        GroupIdView();
                    }
                    default -> {
                        action = -1;
                        Component.showErrorMessage("Enter a valid choice (1, 2): ");
                    }
                }
            } catch (Exception e) {
                Component.showErrorMessage(e.getMessage());
            }
        } while (action == -1);
    }



    public  void TypeMessageView(String reciever_type) throws IOException {
        Component.pageTitleView("Type a message");

        Scanner scanner = new Scanner(System.in);
        Component.chooseOptionInputView("Your Message: ");
        String message = scanner.nextLine();
        String key = "direct";
        Messages newMessage = null;
        if(reciever_type.equals("direct")){
            newMessage = new Messages(0,userId,receiver,0,message);
            key = "messages/send/direct";
        }
        else if(reciever_type.equals("group")){
            newMessage = new Messages(0,message,userId,receiver,0);
            key = "messages/send/group";
        }
        Request request = new Request(newMessage,key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()){
            CommonUtil.addTabs(10, true);
            Component.alertSuccessMessage(11, "Message sent");
        }else {
            CommonUtil.addTabs(10, true);
            Component.alertDangerErrorMessage(11, "Failed to send");
        }
    }
    public void TypeMessageView(int og_message,String type) throws IOException {
        Component.pageTitleView("Type a reply");

        Scanner scanner = new Scanner(System.in);
        Component.chooseOptionInputView("Your reply message: ");
        String message = scanner.nextLine();
        Messages newMessage = null;
        String key = null;
        if(type.equals("direct")){
        newMessage = new Messages(3,message,userId,this.getReceiver(),9,og_message);
        newMessage.setUser_receiver(1);
        key = "replies/send/direct";
        }
        else if(type.equals("group")){
            newMessage = new Messages(3,message,userId,4,this.getReceiver(),og_message);
            newMessage.setUser_receiver(1);
            key = "replies/send/group";
        }
        Request request = new Request(newMessage,key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()){
            Component.alertSuccessMessage(11, "Reply sent successfully");

        }else {
            Component.alertDangerErrorMessage(11, "Failed to send");
        }
    }

    public void SendFileView() throws IOException {
        Component.pageTitleView("Send a file");

        Scanner scanner = new Scanner(System.in);

        Component.chooseOptionInputView("Enter file path: ");
        String fileLocalPath = scanner.nextLine();


        ObjectMapper objectMapper = new ObjectMapper();

            String fileName = FileUtil.getFileNameFromFilePath(fileLocalPath);
            String fileType = FileUtil.getFileTypeFromFilePath(fileLocalPath);
            String fileSizeType = FileUtil.getFileSizeTypeFromFileSize(FileUtil.getFileSizeFromPath(fileLocalPath));
            int fileSize = FileUtil.getFormattedFileSizeFromFileSize(FileUtil.getFileSizeFromPath(fileLocalPath), FileSizeTypeEnum.valueOf(fileSizeType));


        File file = new File(fileLocalPath, fileName, fileType, fileSize, fileSizeType, userId);
        String key = "file/send";
        Request request = new Request(file, key);
        String requestAsString = objectMapper.writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()){
            Component.alertSuccessMessage(11, "File saved successfully");
            //ageView(new User());
        }
        else{
            Component.alertDangerErrorMessage(11, "File not saved, try again!");
        }
        //View(new User());
    }
    public void EditMessageView() throws IOException {
        Component.pageTitleView("Edit a Message");

        Component.chooseOptionInputView("Enter message id: ");
        int messageId = Component.getChooseOptionChoice();

        Scanner scanner = new Scanner(System.in);
        Component.chooseOptionInputView("Type a new message: ");
        String messageContent = scanner.nextLine();

        String key = "messages/edit";
        Request request = new Request(new MessageResponseDataFormat(userId, messageId, messageContent),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());

        if(response.isSuccess()){
            Component.alertSuccessMessage(11, "Message edited successfully");

        }
        else{
            Component.showErrorMessage("Message edit unsuccessful!");
        }
    }

    public  void DeleteMessageView() throws IOException {
        Component.pageTitleView("Delete a Message");

        Scanner scanner = new Scanner(System.in);

        Component.chooseOptionInputView("Enter message id: ");
        int messageId = Component.getChooseOptionChoice();

        String key= "messages/delete";
        Request request = new Request(new MessageResponseDataFormat(userId,messageId),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()){
            Component.alertSuccessMessage(11, "Message deleted successfully");

        }
        else{
            Component.alertDangerErrorMessage(11, "Message not found!");
        }
    }

    public void DeleteReplieView() throws IOException {
        Component.pageTitleView("Delete a reply");

        Scanner scanner = new Scanner(System.in);

        Component.chooseOptionInputView("Enter message id: ");
        int messageId = scanner.nextInt();

        WriteMessageView();
    }




    public  UsersList SearchUserView() throws IOException {

        Component.pageTitleView("Search a User");

        Component.chooseOptionInputView("Search (User name): ");
        String query = scanner.nextLine();
        String  key= "users/search";
        Request request = new Request(new SearchRequestData(query),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        Component.pageTitleView("Search results");
        List<Integer> ids= new ArrayList<Integer>();
        if(response.isSuccess()){
            User[] users = new UserResponseDataDecoder().returnUsersListDecoded(response.getData());
            CommonUtil.addTabs(10, true);
            for (User user : users) {
                ids.add(user.getUserID());
                System.out.println(user.getUserID()+". "+user.getFname()+" "+user.getLname());
                CommonUtil.addTabs(10, false);
            }

            return new UsersList(users,ids);
        }else {
            Component.alertDangerErrorMessage(11, "Failed to read users list, sorry for the inconvenience");
            CommonUtil.resetColor();
        }
        return null;
    }

    public void UserIdView() throws IOException {
        Component.pageTitleView("Get User");


        Component.chooseOptionInputView("Enter User Id: ");
        int query = Component.getChooseOptionChoice();
        User returned = new RequestSimplifiers(writer,reader).goGetUser(query);
        if(returned != null){
            this.setChattingWith(returned);
            WriteMessageView();
        }
        else {
            Component.alertDangerErrorMessage(11, "User not found");
        }
    }

    public  void GetAllGroupsView() throws IOException {
        String key= "groups/";
        Request request = new Request(new ProfileRequestData(userId),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        Component.pageTitleView("Groups List");
        List ids = new ArrayList<Integer>();
        if(response.isSuccess()){
            Group[] groups = new GroupResponseDataDecoder().returnGroupsListDecoded(response.getData());
            CommonUtil.addTabs(10, true);
            for (Group group : groups) {
                ids.add(group.getId());
                System.out.println(group.getId()+". "+group.getName()+" "+group.getDescription());
                CommonUtil.addTabs(10, false);
            }
            int choice = 0;
            do{
                System.out.println("");
                Component.chooseOptionInputView("Type group id to chat in: ");
                choice  = Component.getChooseOptionChoice();
                if(!ids.contains(choice)){
                    Component.alertDangerErrorMessage(11, "Invalid group id. Try again!");
                }
            }while(!ids.contains(choice));
            for (Group group : groups) {
                if(group.getId() == choice){
                    WriteMessageViewInGroup(group);
                }
            }
        }else {
            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.RegularColor.RED);
            System.out.println("Failed to read users list, sorry for the inconvenience");
            CommonUtil.resetColor();
            Component.alertDangerErrorMessage(11, "Failed to read users list, sorry for the inconvenience");
        }
    }


        public void SearchGroupView() throws IOException {
            Component.pageTitleView("Search a Group");

            Component.chooseOptionInputView("Search (Group name or group description): ");
            String query = scanner.nextLine();
            String  key= "groups/search";
            Request request = new Request(new SearchRequestData(query),key);
            String requestAsString = new ObjectMapper().writeValueAsString(request);
            writer.println(requestAsString);
            List ids = new ArrayList<Integer>();
            ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
            Component.pageTitleView("Search results");
            if(response.isSuccess()){
                Group[] groups = new GroupResponseDataDecoder().returnGroupsListDecoded(response.getData());
                CommonUtil.addTabs(10, true);
                System.out.println("");
                for (Group group : groups) {
                    ids.add(group.getId());
                    CommonUtil.addTabs(10, false);
                    System.out.println(group.getId()+". "+group.getName()+" "+group.getDescription());
                }
                int choice =  0;
                do{
                    System.out.println("");
                    Component.chooseOptionInputView("Type group id to chat in: ");
                    choice = Component.getChooseOptionChoice();
                    if(!ids.contains(choice)){
                        Component.alertDangerErrorMessage(11, "Invalid group id. Try again!");
                    }
                }while(!ids.contains(choice));
                for (Group group : groups) {
                    if(group.getId() == choice){
                        WriteMessageViewInGroup(group);
                    }
                }
            }else {
                Component.alertDangerErrorMessage(11, "Failed to read groups list, sorry for the inconvenience");
            }
        }

    public void GroupIdView() throws IOException {
        Component.pageTitleView("Get Group");


        Component.chooseOptionInputView("Enter Group Id: ");
        int query = Component.getChooseOptionChoice();
        String  key= "groups/profile";
        Request request = new Request(new ProfileRequestData(query),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        Component.pageTitleView("Group BY ID GETTING");
        if(response.isSuccess()){
            Group group = new GroupResponseDataDecoder().returnGroupDecoded(response.getData());
            WriteMessageViewInGroup(group);
        }else {
            Component.alertDangerErrorMessage(11, "Group not found");
        }

    }
    public UsersList allActiveUsers() throws IOException {
        Component.pageTitleView("USERS LIST");
        List ids = new ArrayList<Integer>();
            User[] users = new RequestSimplifiers(writer,reader).goGetUsers(userId);
        if(users != null){
            CommonUtil.addTabs(10, true);
            for (User user : users) {
                ids.add(user.getUserID());
                System.out.println(user.getUserID()+". "+user.getFname()+" "+user.getLname());
                CommonUtil.addTabs(10, false);
            }
            if(users.length == 0){
                return null;
            }
            return new UsersList(users,ids);
        }else {
            Component.alertDangerErrorMessage(11, "Failed to read users list, sorry for the inconvenience");
        }
        System.out.println("");
        return null;

    }
    public void checkUserToSendMessage(UsersList list) throws  IOException{
        if(list == null){
            Component.alertDangerErrorMessage(11, "Users list is empty");
            CommonUtil.resetColor();
        }
        else{
        int choice = 0;
        List ids = list.getIds();
        User[] users = list.getUsers();
        if(users.length == 0){
            Component.alertDangerErrorMessage(11, "No users found");
        }
        else{


        do{
            System.out.println("");
            Component.chooseOptionInputView("Type user id to chat with: ");
            choice  = Component.getChooseOptionChoice();
            if(!ids.contains(choice)){
                Component.alertDangerErrorMessage(11, "User not found, try another!");
            }
        }while(!ids.contains(choice));
        for (User user : users) {
            if(user.getUserID() == choice){
                this.setChattingWith(user);
                this.setReceiver(choice);
                WriteMessageView();
            }
        }
        }
        }
    }
    public  void WriteMessageView() throws IOException {
        Messages[] messages = new RequestSimplifiers(writer,reader).goGetMessages(userId,receiver);
        Component.pageTitleView("Your recent chat");
        if(messages != null){
            if(messages.length != 0){
            for (Messages message : messages) {
                CommonUtil.addTabs(11, true);
                if(message.getSender() != userId){
                    System.out.println("[ SENDER: " + this.getChattingWith().getFname()+" "+this.getChattingWith().getLname() + "] ");
                }
                else{
                    System.out.println("[ SENDER: " + this.getCurrent().getFname()+" "+ this.getCurrent().getLname()+ "] ");
                }
                CommonUtil.addTabs(11, false);
                CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.YELLOW_BOLD_BRIGHT);
                System.out.print("[" + message.getId() + "] ");
                CommonUtil.resetColor();
                CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.WHITE_BOLD_BRIGHT);
                System.out.print(message.getContent());

                    CommonUtil.useColor(ConsoleColor.RegularColor.PURPLE);
                    System.out.print("  (Date:  ");
                    CommonUtil.useColor(ConsoleColor.BoldColor.BLUE_BOLD);
                    System.out.print(message.getSent_at());
                    CommonUtil.useColor(ConsoleColor.RegularColor.PURPLE);
                    System.out.println(")");

                CommonUtil.resetColor();
            }
            }
            else{
                CommonUtil.addTabs(11, false);
                CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.PURPLE_BOLD_BRIGHT);
                System.out.println("No messages sent yet");
                CommonUtil.resetColor();
            }
        }else {
            Component.alertDangerErrorMessage(11, "Failed to read users list, sorry for the inconvenience");
        }
        Component.pageTitleView("Write Message to "+ chattingWith.getFname()+" "+chattingWith.getLname());


        CommonUtil.addTabs(11, true);
        System.out.println("1. Write a message");
        CommonUtil.addTabs(11, false);
        System.out.println("2. Send a file");
        CommonUtil.addTabs(11, false);
        System.out.println("3. Edit a message");
        CommonUtil.addTabs(11, false);
        System.out.println("4. Delete a message");
        CommonUtil.addTabs(11, false);
        System.out.println("5. Replies");

        Component.chooseOptionInputView("Choose an option: ");

        int action;
        do {
            action = Component.getChooseOptionChoice();
            try {
                switch (action) {
                    case 1 -> {
                        TypeMessageView("direct");
                    }
                    case 2 -> {
                        SendFileView();
                    }
                    case 3 -> {
                        EditMessageView();
                    }
                    case 4 -> {
                        DeleteMessageView();
                    }
                    case 5 -> {
                        MessageRepliesView();
                    }

                    default -> {
                        action = -1;

                        Component.showErrorMessage("Enter a valid choice (1, 2): ");

                    }
                }
            } catch (Exception e) {
                Component.showErrorMessage(e.getMessage());
            }
        } while (action == -1);

    }

    public  void WriteMessageViewInGroup(Group group) throws IOException {
        String key = "messages/group";
        Request request = new Request(new ProfileRequestData(group.getId()), key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        Component.pageTitleView("Your recent chat");
        if(response.isSuccess()){
            Messages[] messages = new MessageResponseDataDecoder().returnMessagesNotificationsList(response.getData());
            CommonUtil.addTabs(10, true);
            for (Messages message : messages) {
                CommonUtil.useColor(ConsoleColor.RegularColor.PURPLE);
                System.out.print(message.getSender());
                CommonUtil.useColor(ConsoleColor.RegularColor.YELLOW);
                System.out.println("\t at "+message.getSent_at());
                CommonUtil.resetColor();
                CommonUtil.addTabs(10, false);
                System.out.println("Message: "+message.getContent());
                CommonUtil.addTabs(10, true);
            }
        }else {
            Component.alertDangerErrorMessage(11, "Failed to read users list, sorry for the inconvenience");
        }
        Component.pageTitleView("Write Message to "+ group.getName()+" Group");


        CommonUtil.addTabs(11, true);
        System.out.println("1. Write a message");
        CommonUtil.addTabs(11, false);
        System.out.println("2. Send a file");
        CommonUtil.addTabs(11, false);
        System.out.println("3. Delete a message");
        CommonUtil.addTabs(11, false);
        System.out.println("4. Replies");
        CommonUtil.addTabs(11, false);
        System.out.println("5. Notifications");
        CommonUtil.addTabs(11, false);
        System.out.println("44. Go back");
        CommonUtil.addTabs(11, false);
        System.out.println("55. Quit");


        Component.chooseOptionInputView("Choose an option: ");

        int action;
        do {
            action = Component.getChooseOptionChoice();
            try {
                switch (action) {
                    case 1 -> {
                        TypeMessageView("group");
                    }
                    case 2 -> {
                        SendFileView();
                    }
                    case 3 -> {
                        DeleteMessageView();
                    }
                    case 4 -> {
                        MessageRepliesView();
                    }
                    case 44->{
                        CommonUtil.addTabs(10, true);
                        System.out.println("Going back");
                    }
                    case 55->{
                        CommonUtil.addTabs(10, true);
                        CommonUtil.useColor("\u001b[1;31m");
                        System.out.println("SYSTEM CLOSED !");
                        System.exit(1);
                    }
                    default -> {
                        action = -1;

                        Component.showErrorMessage("Enter a valid choice (1, 2): ");

                    }
                }
            } catch (Exception e) {
                Component.showErrorMessage(e.getMessage());
            }
        } while (action == -1);

    }

    // --------------------Notifications View-----------
    // author : Souvede & Chanelle

    public void ViewNotifications() throws IOException {
        Component.pageTitleView("My notifications");
        String  key= "messages/notifications";
        Request request = new Request(new ProfileRequestData(userId),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()){
            Messages[] messageList = new MessageResponseDataDecoder().returnMessagesNotificationsList(response.getData());
            CommonUtil.addTabs(10, true);
            for (Messages messages : messageList) {
                System.out.println("New Notification from Group id : "+messages.getSender()+". " +" "+"  Sent at : "+messages.getSent_at());
                CommonUtil.addTabs(10, false);
            }
        }else {
            Component.alertDangerErrorMessage(11, "Failed to get notifications, sorry for the inconvenience");
        }
        System.out.println("");
        Component.chooseOptionInputView("Type any number to go to main page: ");
        int choice  = Component.getChooseOptionChoice();
    }


    public void ViewNoti() throws IOException {
        Component.pageTitleView("My notifications");
        String  key= "messages/notifi";
        Request request = new Request(new ProfileRequestData(userId),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()){
            Messages[] messageList = new MessageResponseDataDecoder().returnMessagesNotificationsList(response.getData());
            CommonUtil.addTabs(10, true);
            for (Messages messages : messageList) {
                System.out.println("New Notification from user id : "+messages.getSender()+". " +" "+"  Sent at : "+messages.getSent_at());
                CommonUtil.addTabs(10, false);
            }
        }else {
            Component.alertDangerErrorMessage(11, "Failed to get notifications, sorry for the inconvenience");
        }
        System.out.println("");
        Component.chooseOptionInputView("Type any number to go to main page: ");
        int choice  = Component.getChooseOptionChoice();
    }




    public void SendReplyView() throws IOException {
        Component.pageTitleView("Send reply");

        Component.chooseOptionInputView("Enter message id to reply: ");
        int message_id = Component.getChooseOptionChoice();
        if(new RequestSimplifiers(writer,reader).goGetMessage(message_id) == null){
            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
            System.out.println("Message Not found");
            CommonUtil.resetColor();
        }
        else{
        CommonUtil.addTabs(11, true);
        System.out.println("1. Write a message");
        CommonUtil.addTabs(11, false);
        System.out.println("2. Send a file");
        CommonUtil.addTabs(11, false);
        System.out.println("44. Go back");
        CommonUtil.addTabs(11, false);
        System.out.println("55. Quit");
        Component.chooseOptionInputView("Choose an option: ");

        int action;
        do {
            action = Component.getChooseOptionChoice();
            try {
                switch (action) {
                    case 1 -> {
                        TypeMessageView(message_id,"direct");
                    }
                    case 2 -> {
                        SendFileView();
                    }
                    case 44->{
                        CommonUtil.addTabs(10, true);
                        System.out.println("Going back");
                    }
                    case 55->{
                        CommonUtil.addTabs(10, true);
                        CommonUtil.useColor("\u001b[1;31m");
                        System.out.println("SYSTEM CLOSED !");
                        System.exit(1);
                    }

                    default -> {
                        action = -1;
                        Component.showErrorMessage("Enter a valid choice (1, 2): ");

                    }
                }
            } catch (Exception e) {
                Component.showErrorMessage(e.getMessage());
            }

        } while (action == -1);
        }
    }

    public  void ViewRepliesView() throws IOException {


        String key = "replies/";
        Request request = new Request(new ChatBetweenTwo(userId,this.getReceiver()), key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        Component.pageTitleView("REPLIES LIST");
        if (response.isSuccess()) {
            Messages[] messages = new MessageResponseDataDecoder().returnDecodedReplies(response.getData());
            for (Messages message : messages) {
                CommonUtil.addTabs(11, true);
                if(message.getSender() != userId){
                    System.out.println("[ SENDER: " + this.getChattingWith().getFname()+" "+this.getChattingWith().getLname() + "] ");
                }
                else{
                    System.out.println("[ SENDER: " + this.getCurrent().getFname()+" "+ this.getCurrent().getLname()+ "] ");
                }
                CommonUtil.addTabs(11, false);
                CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.YELLOW_BOLD_BRIGHT);
                System.out.print("[" + message.getId() + "] ");
                CommonUtil.resetColor();
                CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.WHITE_BOLD_BRIGHT);
                String messageKey= "messages/single";
                Request profileRequest = new Request(new ProfileRequestData(message.getOriginal_message()),messageKey);
                String messageRequestAsString = new ObjectMapper().writeValueAsString(profileRequest);
                writer.println(messageRequestAsString);
                ResponseDataSuccessDecoder profileResponse = new UserResponseDataDecoder().decodedResponse(reader.readLine());
                System.out.print(message.getContent());
                if(profileResponse.isSuccess()){
                    Messages messages1 = new MessageResponseDataDecoder().returnDecodedMessage(profileResponse.getData());
                    CommonUtil.useColor(ConsoleColor.RegularColor.PURPLE);
                    System.out.print("  (OG' message:  ");
                    CommonUtil.useColor(ConsoleColor.BoldColor.BLUE_BOLD);
                    System.out.print(messages1.getContent());
                    CommonUtil.useColor(ConsoleColor.RegularColor.PURPLE);
                    System.out.println(")");
                }

                else{
                System.out.println("  (OG' message: " + message.getOriginal_message()+"(It was deleted))");
                }
                CommonUtil.resetColor();
            }
        } else {
            CommonUtil.addTabs(11, true);
            Component.alertDangerErrorMessage(11, "Failed to read replies, sorry for the inconvenience");
        }
    }

    public  void MessageRepliesView() {
        Component.pageTitleView("Message Replies");

        CommonUtil.addTabs(11, false);
        System.out.println("1. Send a reply");
        CommonUtil.addTabs(11, false);
        System.out.println("2. View replies");
        CommonUtil.addTabs(11, false);
        System.out.println("3. Delete a reply");

        Component.chooseOptionInputView("Choose an option: ");

        int action;
        do {
            action = Component.getChooseOptionChoice();
            try {
                switch (action) {
                    case 1 -> {
                        SendReplyView();
                    }
                    case 2 -> {
                        ViewRepliesView();
                    }
                    case 3 -> {
                        DeleteMessageView();
                    }


                    default -> {
                        action = -1;
//                        CommonUtil.addTabs(10, false);
//                        CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
//                        System.out.print("Enter a valid choice (1, 2): ");
//                        CommonUtil.resetColor();
                        Component.showErrorMessage("Enter a valid choice (1, 2, 3): ");

                    }
                }
            } catch (Exception e) {
                Component.showErrorMessage(e.getMessage());
            }
        } while (action == -1);

    }

}
