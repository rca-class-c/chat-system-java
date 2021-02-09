package client.views;

import client.interfaces.*;
import client.views.components.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.Group;
import server.models.Messages;
import server.models.User;
import utils.CommonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class SendMessageView {
    int userId;
    public PrintWriter writer;
    public BufferedReader reader;
    Scanner scanner = new Scanner(System.in);

    public SendMessageView(int userId, PrintWriter writer, BufferedReader reader) {
        this.userId = userId;
        this.writer = writer;
        this.reader = reader;
    }

    public void OptionsView() {
        Component.pageTitleView("Send a Message");

        CommonUtil.addTabs(11, true);
        System.out.println("1. Direct Message");
        CommonUtil.addTabs(11, false);
        System.out.println("2. Message a group");

        Component.chooseOptionInputView("Choose an option: ");

        int action;
        do {
            action = Component.getChooseOptionChoice();
            try {
                switch (action) {
                    case 1 -> {
                        DirectMessageView();
                    }
                    case 2 -> {
                         GroupMessageView();
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
    public void DirectMessageView() {
        Component.pageTitleView("Direct Message");

        CommonUtil.addTabs(11, true);
        System.out.println("1. List all Users");
        CommonUtil.addTabs(11, false);
        System.out.println("2. Search a User (names)");
        CommonUtil.addTabs(11, false);
        System.out.println("3. Enter a user ID");

        Component.chooseOptionInputView("Choose an option: ");


        int action;
        do {
            action = Component.getChooseOptionChoice();
            try {
                CommonUtil.resetColor();
                switch (action) {
                    case 1 -> {
                        allActiveUsers();
                    }
                    case 2 -> {
                        SearchUserView();
                    }
                    case 3 -> {
                        UserIdView();
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

    public void GroupMessageView() {
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



    public static void TypeMessageView() {
        Component.pageTitleView("Type a message");

        Scanner scanner = new Scanner(System.in);

        Component.chooseOptionInputView("Your Message: ");
        String message = scanner.nextLine();

        WriteMessageView(new User());
    }

    public static void SendFileView() {
        Component.pageTitleView("Send a file");

        Scanner scanner = new Scanner(System.in);

        Component.chooseOptionInputView("Enter file path: ");
        String message = scanner.nextLine();

        WriteMessageView(new User());
    }

    public static void DeleteMessageView() {
        Component.pageTitleView("Delete a Message");

        Scanner scanner = new Scanner(System.in);

        Component.chooseOptionInputView("Enter message id: ");
        int messageId = scanner.nextInt();

        WriteMessageView(new User());
    }




    public  void SearchUserView() throws IOException {

        Component.pageTitleView("Search a User");

        Component.chooseOptionInputView("Search (User name): ");
        String query = scanner.nextLine();
        String  key= "search_user";
        Request request = new Request(new SearchRequestData(query),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        Component.pageTitleView("Search results");
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
        Component.chooseOptionInputView("Type user id to chat with: ");
        int choice  = scanner.nextInt();
    }

    public void UserIdView() throws IOException {
        Component.pageTitleView("Get User");


        Component.chooseOptionInputView("Enter User Id: ");
        int query = scanner.nextInt();
        String  key= "get_profile";
        Request request = new Request(new ProfileRequestData(query),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        Component.pageTitleView("USER BY ID GETTING");
        if(response.isSuccess()){
            User user = new UserResponseDataDecoder().returnUserDecoded(response.getData());
            WriteMessageView(user);
        }else {
            CommonUtil.addTabs(10, true);
            System.out.println("User not found");
        }



    }

    public  void GetAllGroupsView() throws IOException {
        String  key= "get_groups_list";
        Request request = new Request(new ProfileRequestData(userId),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        Component.pageTitleView("Groups List");
        if(response.isSuccess()){
            Group[] groups = new GroupResponseDataDecoder().returnGroupsListDecoded(response.getData());
            CommonUtil.addTabs(10, true);
            for (Group group : groups) {
                System.out.println(group.getId()+". "+group.getName()+" "+group.getDescription());
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



    public void SearchGroupView() throws IOException {
        Component.pageTitleView("Search a Group");

        Component.chooseOptionInputView("Search (Group name or group description): ");
        String query = scanner.nextLine();
        String  key= "search_group";
        Request request = new Request(new SearchRequestData(query),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        Component.pageTitleView("Search results");
        if(response.isSuccess()){
            Group[] groups = new GroupResponseDataDecoder().returnGroupsListDecoded(response.getData());
            CommonUtil.addTabs(10, true);
            for (Group group : groups) {
                System.out.println(group.getId()+". "+group.getName()+" "+group.getDescription());
                CommonUtil.addTabs(10, false);
            }
        }else {
            CommonUtil.addTabs(10, true);
            System.out.println("Failed to read users list, sorry for the inconvenience");
        }
        System.out.println("");
        Component.chooseOptionInputView("Type user id to chat with: ");
        int choice  = scanner.nextInt();
    }

    public void GroupIdView() throws IOException {
        Component.pageTitleView("Get Group");


        Component.chooseOptionInputView("Enter User Id: ");
        int query = scanner.nextInt();
        String  key= "get_group";
        Request request = new Request(new ProfileRequestData(query),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        Component.pageTitleView("Group BY ID GETTING");
        if(response.isSuccess()){
            Group group = new GroupResponseDataDecoder().returnGroupDecoded(response.getData());
            WriteMessageView(new User());
        }else {
            CommonUtil.addTabs(10, true);
            System.out.println("Group not found");
        }

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

    public void FindUser(int id) throws IOException {
        String key = "get_profile";
        Request request = new Request(new ProfileRequestData(id), key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if (response.isSuccess()) {
            User profile = new UserResponseDataDecoder().returnUserDecoded(response.getData());
            Component.pageTitleView("Chat with " + profile.getUsername()+" "+profile.getFname());
            CommonUtil.addTabs(10, false);
            System.out.println("Type number message:  ");
            CommonUtil.addTabs(10, false);
            int message = scanner.nextInt();
        } else {
            CommonUtil.addTabs(10, false);
            System.out.println("User not found");
        }
    }

    public static void WriteMessageView(User user) {
        Component.pageTitleView("Write Message to "+ user.getUsername()+" "+user.getLname());


        CommonUtil.addTabs(11, true);
        System.out.println("1. Write a message");
        CommonUtil.addTabs(11, false);
        System.out.println("2. Send a file");
        CommonUtil.addTabs(11, false);
        System.out.println("3. Delete a message");
        CommonUtil.addTabs(11, false);
        System.out.println("4. Replies");

        Component.chooseOptionInputView("Choose an option: ");

        int action;
        do {
            action = Component.getChooseOptionChoice();
            try {
                switch (action) {
                    case 1 -> {
                        TypeMessageView();
                    }
                    case 2 -> {
                        SendFileView();
                    }
                    case 3 -> {
                        DeleteMessageView();
                    }
                    case 4 -> {
                        ViewRepliesView();
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

    public void ViewNotifications() throws IOException {
        Component.pageTitleView("My notifications");
        String  key= "get_my_notifications";
        Request request = new Request(new ProfileRequestData(userId),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()){
            Messages[] messageList = new MessageResponseDataDecoder().returnMessagesNotificationsList(response.getData());
            CommonUtil.addTabs(10, true);
            for (Messages messages : messageList) {
                System.out.println(messages.getSender()+". "+messages.getContent()+" "+messages.getSent_at());
                CommonUtil.addTabs(10, false);
            }
        }else {
            CommonUtil.addTabs(10, true);
            System.out.println("Failed to get notifications, sorry for the inconvenience");
        }
        System.out.println("");
        Component.chooseOptionInputView("Type any number to go to main page: ");
        int choice  = scanner.nextInt();
    }
    public static void SendReplyView() {
        Component.pageTitleView("Send reply");


        CommonUtil.addTabs(11, true);
        System.out.println("1. Write a message");
        CommonUtil.addTabs(11, false);
        System.out.println("2. Send a file");

        Component.chooseOptionInputView("Choose an option: ");

        int action;
        do {
            action = Component.getChooseOptionChoice();
            try {
                switch (action) {
                    case 1 -> {
                        TypeMessageView();
                    }
                    case 2 -> {
                        SendFileView();
                    }

                    default -> {
                        action = -1;
//                        CommonUtil.addTabs(10, false);
//                        CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
//                        System.out.print("Enter a valid choice (1, 2): ");
//                        CommonUtil.resetColor();
                        Component.showErrorMessage("Enter a valid choice (1, 2): ");

                    }
                }
            } catch (Exception e) {
                Component.showErrorMessage(e.getMessage());
            }
        } while (action == -1);
    }

    public static void ViewRepliesView() {
        Scanner scanner = new Scanner(System.in);
        Component.pageTitleView("View Replies");

        System.out.println("Replies list");

        MessageRepliesView();

    }

    public static void MessageRepliesView() {
        Component.pageTitleView("Message Replies a Group");

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
                        Component.showErrorMessage("Enter a valid choice (1, 2): ");

                    }
                }
            } catch (Exception e) {
                Component.showErrorMessage(e.getMessage());
            }
        } while (action == -1);

    }

}
