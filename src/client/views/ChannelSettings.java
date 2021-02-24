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

    public void channelMenu(){
        int choice = 0;
        while(choice != 55 && choice != 44) {
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
                switch(choice) {
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
            if(choice == 44){
                break;
            }
        }
    }
    public void ExistingChanelOptions() throws IOException {
        int choice = 3;
        while(choice != 44 && choice != 55) {
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
            System.out.println("is incorrect input"+var2.getMessage());
            CommonUtil.resetColor();
        }
            if(choice == 44) {
                break;
            }
        }
    }

  public void CreateChannel() throws IOException {
      Scanner scanner = new Scanner(System.in);
      Component.pageTitleView("CREATE Group IN CLASS_C CHAT");

      CommonUtil.addTabs(10, false);
      System.out.print("Enter your Group name: ");
      String group_name = scanner.nextLine();
      CommonUtil.addTabs(10, false);
      System.out.print("Enter your Group description: ");
      String group_desc = scanner.nextLine();

      ObjectMapper objectMapper=new ObjectMapper();
      Group group=new Group(group_name,group_desc,userId);

      String key="groups/new";
      Request request = new Request(group,key);

      String requestAsString = objectMapper.writeValueAsString(request);
      writer.println(requestAsString);
      ResponseDataSuccessDecoder response= new GroupResponseDataDecoder().decodedResponse(reader.readLine());
      if(response.isSuccess()){
          CommonUtil.addTabs(10,true);
          CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
          CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
          System.out.print("your Group was created successfully");
          CommonUtil.resetColor();

          //add the statement to link to the next navigation
      }
      else {
          CommonUtil.addTabs(10, true);
          CommonUtil.useColor(ConsoleColor.BackgroundColor.RED_BACKGROUND);
          CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
          System.out.print("  Group not created, try again! ");
          CommonUtil.resetColor();
      }

  }

  public void getAllGroups() throws IOException {
        String key="groups/";
        Request request= new Request(new ProfileRequestData(userId),key);
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
              Component.chooseOptionInputView("Type group id to work with: ");
              choice  = Component.getChooseOptionChoice();
              if(!ids.contains(choice)){
                  CommonUtil.addTabs(10, true);
                  System.out.println("Invalid group id. Try again!");
              }
          }while(!ids.contains(choice));
          for (Group group : groups) {
              if(group.getId() == choice){
                  channelProfile(group);
              }
          }
      }else {
          CommonUtil.addTabs(10, true);
          System.out.println("Failed to read users list, sorry for the inconvenience");
      }
  }

  public void channelProfile(Group group){
      int choice = 0;
      while(choice != 55 && choice != 44) {
          Component.pageTitleView("working with "+group.getName()+" group");

          CommonUtil.addTabs(11, true);
          System.out.println("1. chat");
          CommonUtil.addTabs(11, false);
          System.out.println("2. list Group members");
          CommonUtil.addTabs(11, false);
          System.out.println("3. Add Group members");
          CommonUtil.addTabs(11,false);
          System.out.println("4. delete Group member");
          CommonUtil.addTabs(11,false);
          System.out.println("5. update Group profile");
          CommonUtil.addTabs(11,false);
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
                      deleteChannelMember();
                  }
                  case 5 -> {
                      CommonUtil.addTabs(10, true);
                      System.out.println("update group");
                  }
                  case 6 -> {
                      CommonUtil.addTabs(10, true);
                      System.out.println("delete group");
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
        new UserView(userId,writer,reader).allActiveUsers();
        Component.pageTitleView("Create group members");

        Scanner input = new Scanner(System.in);
        System.out.println("How many user do you want to enter?");
        int num = input.nextInt();
        Integer members[] = new Integer[num];
        System.out.println("Enter the " + members + " numbers now.");
        for (int i = 0 ; i < members.length; i++ )
        {
            members[i] = input.nextInt();
        }

        String key = "group/members/create";
        ObjectMapper objectMapper=new ObjectMapper();
        Request request=new Request(new AddMemberRequestData(groupId,members),key);
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
          GroupMember[] groupMembers = new GetGroupMembersRequestData(2).returnGroupMemberListDecoded(response.getData());
          CommonUtil.addTabs(10, true);
          for (GroupMember groupMember : groupMembers) {
              ids.add(groupMember.getGroup_id());
              System.out.println(groupMember.getMember_id());
              CommonUtil.addTabs(10, false);
          }
      }else {
          CommonUtil.addTabs(10, true);
          System.out.println("Failed to read users list, sorry for the inconvenience");
      }
  }

  public void deleteChannelMember(){}


}


