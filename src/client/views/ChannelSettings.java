package client.views;

import client.interfaces.ProfileRequestData;
import client.interfaces.Request;
import client.views.components.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.CommonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
                        CreateChanel();
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
            CommonUtil.useColor("\u001b[43m");
            System.out.print("  ");
            CommonUtil.resetColor();
            CommonUtil.useColor("\u001b[0;33m");
            System.out.print(" Choose an option: ");
            CommonUtil.resetColor();
            choice = AdminAction.insertAdminChoice();
            switch(choice) {
                case 1:
                    new SendMessageView(userId,writer, reader).GetAllGroupsView();
                    break;
                case 2:
                    CreateChanel();
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
            if(choice == 44) {
                break;
            }
        }
    }
  public void CreateChanel(){
      Scanner scanner = new Scanner(System.in);
      Component.pageTitleView("CREATE Group IN CLASS_C CHAT");


      CommonUtil.addTabs(10, false);
      System.out.print("Enter your Group name: ");
      String group_name = scanner.nextLine();
      System.out.print("Enter your Group description: ");
      String group_desc = scanner.nextLine();
      System.out.println("Group created successful");
  }
}
