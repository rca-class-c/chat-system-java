package client.views;

import client.interfaces.*;
import client.views.components.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.Group;
import server.models.GroupMember;
import server.models.Response;
import utils.CommonUtil;
import utils.ConsoleColor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ChannelSettings {
    public int userId;
    public PrintWriter writer;
    public BufferedReader reader;

    public ChannelSettings(int userId, PrintWriter writer, BufferedReader reader) {
        this.userId = userId;
        this.writer = writer;
        this.reader = reader;
    }

    Scanner scanner = new Scanner(System.in);

    public void channelMenu() {
        int choice = 0;
        while (choice != 55 && choice != 44) {
            Component.pageTitleView("CHANNEL SETTINGS");
            CommonUtil.addTabs(10, true);
            System.out.println("1. Existing channels");
            CommonUtil.addTabs(10, false);
            System.out.println("2. New channel");
            CommonUtil.addTabs(10, false);
            System.out.println("44. Go back");
            CommonUtil.addTabs(10, false);
            System.out.println("55. Quit");

            try {
                CommonUtil.addTabs(10, false);
                CommonUtil.useColor("\u001b[43m");
                System.out.print("  ");
                CommonUtil.resetColor();
                CommonUtil.useColor("\u001b[0;33m");
                System.out.print(" Choose an option: ");
                CommonUtil.resetColor();
                choice = AdminAction.insertAdminChoice();
                switch (choice) {
                    case 1:
                        ExistingChanelOptions();
                        break;
                    case 2:
                        CreateChannel();
                        break;
                    case 44:
                        CommonUtil.addTabs(10, true);
                        System.out.println("Going back");
                        break;
                    case 55:
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
            if (choice == 44) {
                break;
            }
        }
    }

    public void ExistingChanelOptions() throws IOException {
        int choice = 3;
        while (choice != 44 && choice != 55) {
            Component.pageTitleView("Chanells");
            CommonUtil.addTabs(10, true);
            System.out.println("1. Channels list");
            CommonUtil.addTabs(10, false);
            System.out.println("2. Search Channel");
            CommonUtil.addTabs(10, false);
            System.out.println("3. ID Channel");
            CommonUtil.addTabs(10, false);
            System.out.println("44. Go back");
            CommonUtil.addTabs(10, false);
            System.out.println("55. Quit");
            CommonUtil.addTabs(10, false);
            try {
                CommonUtil.useColor("\u001b[43m");
                System.out.print("  ");
                CommonUtil.resetColor();
                CommonUtil.useColor("\u001b[0;33m");
                System.out.print(" Choose an option: ");
                CommonUtil.resetColor();
                choice = AdminAction.insertAdminChoice();

                switch (choice) {
                    case 1:
                        getAllGroups();
//                    new SendMessageView(userId,writer,reader).GetAllGroupsView();
                        break;
                    case 2:
                        new SendMessageView(userId, writer, reader).SearchGroupView();
                        break;
                    case 3:
                        new SendMessageView(userId, writer, reader).GroupIdView();
                    case 44:
                        System.out.println("Going back");
                        break;
                    case 55:
                        CommonUtil.addTabs(10, true);
                        CommonUtil.useColor("\u001b[1;31m");
                        System.out.println("SYSTEM CLOSED !");
                        System.exit(1);
                        break;
                    default:
                        CommonUtil.addTabs(10, false);
                        CommonUtil.useColor("\u001b[1;31m");
                        System.out.println("Enter a valid choice: ");
                        CommonUtil.resetColor();
                }
            } catch (Exception var2) {
                CommonUtil.addTabs(10, false);
                CommonUtil.useColor("\u001b[1;31m");
                System.out.println("is incorrect input" + var2.getMessage());
                CommonUtil.resetColor();
            }
            if (choice == 44) {
                break;
            }
        }
    }

    public void CreateChannel() throws IOException {

        Component.pageTitleView("CREATE Group IN CLASS_C CHAT");

        CommonUtil.addTabs(10, false);
        System.out.print("Enter your Group name: ");
        String group_name = scanner.nextLine();
        CommonUtil.addTabs(10, false);
        System.out.print("Enter your Group description: ");
        String group_desc = scanner.nextLine();

        ObjectMapper objectMapper = new ObjectMapper();
        Group group = new Group(group_name, group_desc, userId);

        String key = "groups/new";
        Request request = new Request(group, key);

        String requestAsString = objectMapper.writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new GroupResponseDataDecoder().decodedResponse(reader.readLine());
        if (response.isSuccess()) {
            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
            System.out.print("your Group was created successfully");
            CommonUtil.resetColor();

            //add the statement to link to the next navigation
        } else {
            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.BackgroundColor.RED_BACKGROUND);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
            System.out.print("  Group not created, try again! ");
            CommonUtil.resetColor();
            choice = AdminAction.insertAdminChoice();

            switch(choice) {
                case 1:
                    getAllGroups();
//                    new SendMessageView(userId,writer,reader).GetAllGroupsView();
                    break;
                case 2:
                    new SendMessageView(userId,writer, reader).SearchGroupView();
                    break;
                case 3:
                    new SendMessageView(userId,writer, reader).GroupIdView();
                case 44:
                    System.out.println("Going back");
                    break;
                case 55:
                    CommonUtil.addTabs(10, true);
                    System.out.println("Invalid group id. Try again!");
                }
            } while (!ids.contains(choice));
            for (Group group : groups) {
                if (group.getId() == choice) {
                    channelProfile(group);
                }
            }
        } else {
            CommonUtil.addTabs(10, true);
            System.out.println("Failed to read users list, sorry for the inconvenience");
        }
    }

    public void channelProfile(Group group) {
        int choice = 0;
        while (choice != 55 && choice != 44) {
            Component.pageTitleView("working with " + group.getName() + " group");

            CommonUtil.addTabs(11, true);
            System.out.println("1. chat");
            CommonUtil.addTabs(11, false);
            System.out.println("2. list Group members");
            CommonUtil.addTabs(11, false);
            System.out.println("3. Add Group members");
            CommonUtil.addTabs(11, false);
            System.out.println("4. delete Group member");
            CommonUtil.addTabs(11, false);
            System.out.println("5. profile");
            CommonUtil.addTabs(11, false);
            System.out.println("6. delete group");
            CommonUtil.addTabs(11, false);
            System.out.println("44. Go back");
            CommonUtil.addTabs(11, false);
            System.out.println("55. Quit");
            Component.chooseOptionInputView("Choose an option: ");

            choice = Component.getChooseOptionChoice();
            try {
                switch (choice) {
                    case 1 -> {
                        CommonUtil.addTabs(10, true);
                        System.out.println("chat in this group");
                    }
                    case 2 -> {
                        getChannelMembers(group.getId());
                    }
                    case 3 -> {
                        createChannelMembers(group.getId());
                    }
                    case 4 -> {
                        deleteChannelMember(group.getId());
                    }
                    case 5 -> {
                        profileGroup(group);
                    }
                    case 6 -> {
                        deleteGroup(group.getId());
                    }
                    case 44 -> {
                        CommonUtil.addTabs(10, true);
                        System.out.println("Going back");
                    }
                    case 55 -> {
                        CommonUtil.addTabs(10, true);
                        CommonUtil.useColor("\u001b[1;31m");
                        System.out.println("SYSTEM CLOSED !");
                        System.exit(1);
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

    public void createChannelMembers(int groupId) throws IOException {
        new UserView(userId, writer, reader).allActiveUsers();
        Component.pageTitleView("Create group members");

        Scanner input = new Scanner(System.in);
        CommonUtil.addTabs(10, true);
        System.out.print("How many user do you want to enter :");
        int num = input.nextInt();
        Integer[] members = new Integer[num];
        for (int i = 0; i < members.length; i++) {

            CommonUtil.addTabs(10, true);
            System.out.print("Enter the no." + 1 + ":");
            members[i] = input.nextInt();
        }

        String key = "groups/members/create";
        ObjectMapper objectMapper = new ObjectMapper();
        Request request = new Request(new AddMemberRequestData(groupId, members), key);
        String requestAsString = objectMapper.writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new GroupResponseDataDecoder().decodedResponse(reader.readLine());
        if (response.isSuccess()) {
            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
            System.out.print("your Group members was created successfully");
            CommonUtil.resetColor();

            //add the statement to link to the next navigation
        } else {
            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.BackgroundColor.RED_BACKGROUND);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
            System.out.print("  Group members not created, try again! ");
            CommonUtil.resetColor();
        }
    }

    public void getChannelMembers(int groupId) throws IOException {
        String key= "groups/members";
        Request request = new Request(new ProfileRequestData(groupId),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        Component.pageTitleView("Groups members List");
        List ids = new ArrayList<Integer>();
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
    }

    public void deleteChannelMember(int groupId) throws IOException {
        getChannelMembers(groupId);
        CommonUtil.addTabs(10, true);
        System.out.print("enter the user id you want to delete :");
        int deleteId = scanner.nextInt();

        String key = "groups/members/delete";
        Request request = new Request(new DeleteMemberRequestData(groupId, deleteId), key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if (response.isSuccess()) {
            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
            System.out.print("your Group members was deleted successfully");
            CommonUtil.resetColor();

            //add the statement to link to the next navigation
        } else {
            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.BackgroundColor.RED_BACKGROUND);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
            System.out.print("  Group members not deleted, try again! ");
            CommonUtil.resetColor();
        }
    }

    public void deleteGroup(int groupId) throws IOException {
        String key = "groups/remove";
        Request request = new Request(new ProfileRequestData(groupId), key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if (response.isSuccess()) {
            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
            System.out.print("your Group members was deleted successfully");
            CommonUtil.resetColor();

            //add the statement to link to the next navigation
        } else {
            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.BackgroundColor.RED_BACKGROUND);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
            System.out.print("  Group members not deleted, try again! ");
            CommonUtil.resetColor();
        }
    }

    public void profileGroup(Group group) throws IOException {
        Component.pageTitleView("Group Profile");
        int choice = 0;
        do {
            CommonUtil.addTabs(10, true);
            System.out.println("1. View profile");
            CommonUtil.addTabs(10, false);
            System.out.println("2. Edit profile");
            Component.chooseOptionInputView("Choose an option: ");
            choice = scanner.nextInt();
            if (choice == 1) {
                String key = "groups/profile";
                Request request = new Request(new ProfileRequestData(group.getId()), key);
                String requestAsString = new ObjectMapper().writeValueAsString(request);
                writer.println(requestAsString);
                ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
                if (response.isSuccess()) {
                    Group profile = new GroupResponseDataDecoder().returnGroupDecoded(response.getData());
                    Component.pageTitleView("Group profile");
                    CommonUtil.addTabs(10, false);
                    System.out.println("name:  " + profile.getName());
                    CommonUtil.addTabs(10, false);
                    System.out.println("description:  " + profile.getDescription());
                    CommonUtil.addTabs(10, false);
                    System.out.println("creator:  " + profile.getCreator());
                } else {
                    System.out.println("No profile found!");
                }
            } else if (choice == 2) {
                updateGroupProfile(group.getId());
            }
        }while (choice != 44 && choice != 55) ;
    }

    public void updateGroupProfile(int groupId) throws IOException {
        String key= "groups/profile";
        String groupName = "";
        String groupDescription = "";
        Request profileRequest = new Request(new ProfileRequestData(groupId),key);
        String requestAsString = new ObjectMapper().writeValueAsString(profileRequest);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder profileResponse = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        Scanner scanner = new Scanner(System.in);

        if(profileResponse.isSuccess()){
            Group profile = new GroupResponseDataDecoder().returnGroupDecoded(profileResponse.getData());
            Component.pageTitleView("Edit group profile");
            CommonUtil.addTabs(10, false);
            System.out.println("If you don't want to change any of your data, type [-1] ");
            CommonUtil.addTabs(10, false);
            System.out.print("Group name"+"["+profile.getName()+"]: ");
            groupName = scanner.nextLine();
            if(!groupName.equals("-1") && !groupName.equals(profile.getName())){
                CommonUtil.addTabs(10, false);
                System.out.print("First name changed!");
                profile.setName(groupName);
                System.out.println();

            }

            CommonUtil.addTabs(10, false);
            System.out.println("Group description"+"["+profile.getDescription()+"]:  ");
            CommonUtil.addTabs(10, false);
            groupDescription = scanner.nextLine();
            if(!groupDescription.equals("-1") && !groupDescription.equals(profile.getDescription())){
                CommonUtil.addTabs(10, false);
                System.out.print("Last name changed!");
                profile.setDescription(groupDescription);
                System.out.println();

            }

            ObjectMapper objectMapper = new ObjectMapper();
            String updateKey = "groups/update";
            System.out.println(profile.getId());
            Request request = new Request(profile,updateKey);
            String requestUpdateAsString = objectMapper.writeValueAsString(request);
            writer.println(requestUpdateAsString);
            ResponseDataSuccessDecoder updateResponse = new GroupResponseDataDecoder().decodedResponse(reader.readLine());
            if(updateResponse.isSuccess()){
                CommonUtil.addTabs(10, true);
                channelProfile(new Group(groupId,groupName,groupDescription,userId));
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
    }
}
