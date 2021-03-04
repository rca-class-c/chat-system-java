package client.views;

import client.simplifiers.RequestSimplifiers;
import client.views.components.Component;
import server.models.User;
import utils.CommonUtil;
import utils.ConsoleColor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class UserView {
    public int userId;
    public PrintWriter writer;
    public BufferedReader reader;

    public UserView(int userId, PrintWriter writer, BufferedReader reader) {
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

    Scanner scanner = new Scanner(System.in);

    public void viewOptions() throws IOException {
        Component.pageTitleView("Dashboard");
        int choice = 0;
        do {
            CommonUtil.addTabs(11, true);
            System.out.println("1. Send a Message");
            CommonUtil.addTabs(11, false);
            System.out.println("2. Channel Settings");
            CommonUtil.addTabs(11, false);
            System.out.println("3. Notifications");
            CommonUtil.addTabs(11, false);
            System.out.println("4. Admin Actions");
            CommonUtil.addTabs(11, false);
            System.out.println("5. Profile Settings");
            CommonUtil.addTabs(11, false);
            System.out.println(ConsoleColor.RegularColor.BLUE + "44" + ConsoleColor.RESET + ". Logout");
            CommonUtil.addTabs(11, false);
            System.out.println(ConsoleColor.RegularColor.RED + "55" + ConsoleColor.RESET + ". Quit");
            Component.chooseOptionInputView("Choose an option: ");
            choice = scanner.nextInt();
            if (choice == 1) {
                new SendMessageView(userId, writer, reader).OptionsView();
            } else if (choice == 2) {
                new ChannelSettings(userId, writer, reader).channelMenu();
            } else if (choice == 3) {
                Component.pageTitleView("Notifications");
                CommonUtil.addTabs(11, true);
                System.out.println("1. Notifications from Direct Messages ");
                CommonUtil.addTabs(11, false);
                System.out.println("2. Notifications from Group Messages");
                Component.chooseOptionInputView("Choose an option: ");
                int result = scanner.nextInt();

                if (result == 1) {
                    new SendMessageView(userId, writer, reader).ViewNoti();

                } else if (result == 2) {
                    new SendMessageView(userId, writer, reader).ViewNotifications();
                }

            } else if (choice == 4) {
                new AdminAction(writer, reader, userId);
            } else if (choice == 5) {
                new ProfileSettings(userId, writer, reader).viewProfileSettingsOptions();
            } else if (choice == 44) {
                break;
            } else if (choice == 55) {
                Component.closeUIView();
                System.exit(1);
            }
        } while (choice != 44 && choice != 55);

    }

    public void allActiveUsers() throws IOException {
        User[] users = new RequestSimplifiers(writer,reader).goGetUsers(userId);
        Component.pageTitleView("USERS LIST");
        if (users != null) {
            if(users.length != 0){


            for (User user : users) {
                CommonUtil.addTabs(11, false);
                CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.YELLOW_BOLD_BRIGHT);
                System.out.print("[" + user.getUserID() + "] ");
                CommonUtil.resetColor();
                CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.WHITE_BOLD_BRIGHT);
                System.out.println(user.getFname() + " " + user.getLname());
                CommonUtil.resetColor();
            }
            }
            else {
                CommonUtil.addTabs(11, false);
                CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.PURPLE_BOLD_BRIGHT);
                System.out.println("Users list is empty ");
                CommonUtil.resetColor();
            }
        } else {
            CommonUtil.addTabs(11, true);
            Component.alertDangerErrorMessage(11, "Failed to read users list, sorry for the inconvenience");
        }
    }

}