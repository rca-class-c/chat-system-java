package client.views;

import client.ChatClient;
import client.views.components.Component;
import utils.CommonUtil;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import server.services.MessagesService;
import utils.GroupMessage;

public class View
{

    public static void WelcomeView(ChatClient client, PrintWriter writer, BufferedReader reader) {
        Component.pageTitleView("Welcome to chat system");

        CommonUtil.addTabs(10, false);
        System.out.println("\t  1. LOGIN  \t");
        CommonUtil.addTabs(10, false);
        System.out.println("\t  2. SIGNUP \t");
        CommonUtil.addTabs(10, false);
        System.out.println("\t  3. HELP   \t");
        CommonUtil.addTabs(10, false);
        System.out.println("\t 55. QUIT   \t");
        Component.chooseOptionInputView("Choose an option: ");


        int action;
        do {
            action = Component.getChooseOptionChoice();
            try {
                switch (action) {
                    case 1 -> {
                        WelcomeView.Login(client, writer, reader);
                    }
                    case 2 -> {
                        WelcomeView.VerificationCode(client, writer, reader);
                    }
                    case 3 -> {
                        Help.Reach();
                    }
                    case 10 -> {
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Enter user id");
                        int id = scanner.nextInt();
                        MessagesService msg = new MessagesService();
                        Set<ResultSet> notifications = msg.viewUserNotifications(id);
                        CommonUtil.displayTray(notifications);
                    }

                    case 11 -> {
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Enter user id");
                        int id = scanner.nextInt();
                        MessagesService msg1 = new MessagesService();
                        List<GroupMessage> notify = msg1.viewUserNotis(id);
                    }

                    case 55 -> {
                        System.out.println();
                        Component.showErrorMessage("System Closing");
                        System.exit(1);
                    }
                    default -> {
                        action = 0;
                        Component.showErrorMessage("Enter a valid choice (1, 2): ");
                    }
                }
            } catch (Exception e) {
                Component.showErrorMessage(e.getMessage());
            }
        } while (action == 0);

    }

}
