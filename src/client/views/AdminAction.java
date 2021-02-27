package client.views;

import client.interfaces.Request;
import client.interfaces.ResponseDataSuccessDecoder;
import client.interfaces.UserResponseDataDecoder;
import client.views.components.Component;
import client.views.components.TableView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import utils.CommonUtil;
import client.views.components.TableView;

public class AdminAction {
    PrintWriter writer;
    BufferedReader reader;
    int userId;
    Scanner scanner = new Scanner(System.in);
    public AdminAction(PrintWriter writer, BufferedReader reader, int userId)
    {
        this.reader = reader;
        this.writer = writer;
        this.userId = userId;
        this.starts();
    }

    public void starts() {
        int choice = 0;
        while(choice != 55 && choice != 44) {
        Component.pageTitleView("ADMIN ACTIONS");
        CommonUtil.addTabs(11, true);
        System.out.println("1. Statistics");
        CommonUtil.addTabs(11, false);
        System.out.println("2. Users");
        CommonUtil.addTabs(11, false);
        System.out.println(ConsoleColor.RegularColor.BLUE + "44" + ConsoleColor.RESET + ". Back");
        CommonUtil.addTabs(11, false);
        System.out.println(ConsoleColor.RegularColor.RED + "55" + ConsoleColor.RESET + ". Quit");

            try {
                Component.chooseOptionInputView("Choose an option: ");
                choice  = scanner.nextInt();
                switch(choice) {
                    case 1:
                        this.chooseStat();
                        break;
                    case 2:
                        this.usersOperation();
                        break;
                    case 3:
                       AdminInput.InviteUser();
                        System.out.println("back to profile setting");
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


    private void chooseStat() {
        int choice = 0;
        while(choice != 55 && choice != 44){
        Component.pageTitleView("Statistics");
        CommonUtil.addTabs(11, true);
        System.out.println("1. Message Reports");
        CommonUtil.addTabs(11, false);
        System.out.println("2. User Reports");
        CommonUtil.addTabs(11, false);
        System.out.println(ConsoleColor.RegularColor.BLUE + "44" + ConsoleColor.RESET + ". Back");
        CommonUtil.addTabs(11, false);
        System.out.println(ConsoleColor.RegularColor.RED + "55" + ConsoleColor.RESET + ". Quit");
                try {
                    Component.chooseOptionInputView("Choose an option: ");
                    choice  = scanner.nextInt();
                    switch (choice) {
                        case 1 -> {
                            CommonUtil.clearScreen();
                            this.choosePeriod("messaging");
                        }
                        case 2 -> {
                            System.out.flush();
                            CommonUtil.clearScreen();
                            this.choosePeriod("user report");
                        }
                        case 3 -> this.starts();
                        case 44->{
                            CommonUtil.addTabs(10, true);
                            System.out.println("Going back");
                            break;
                        }
                        case 55->{
                            CommonUtil.addTabs(10, true);
                            CommonUtil.useColor("\u001b[1;31m");
                            System.out.println("SYSTEM CLOSED !");
                            System.exit(1);
                            break;
                        }
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

    public static int insertAdminChoice() {
        CommonUtil.useColor("\u001b[1;37m");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        CommonUtil.useColor("\u001b[0m");
        return num;
    }

    private void choosePeriod(String range) {
        int choice = 0;
        while(choice != 55 && choice != 44){
        Component.pageTitleView("CHOOSE " + range + " REPORT");
        CommonUtil.addTabs(11, true);
        System.out.println("1. Daily");
        CommonUtil.addTabs(11, false);
        System.out.println("2. Monthly");
        CommonUtil.addTabs(11, false);
        System.out.println("3. Yearly");
        CommonUtil.addTabs(11, false);
        System.out.println(ConsoleColor.RegularColor.BLUE + "44" + ConsoleColor.RESET + ". Back");
        CommonUtil.addTabs(11, false);
        System.out.println(ConsoleColor.RegularColor.RED + "55" + ConsoleColor.RESET + ". Quit");


                try {
                    Component.chooseOptionInputView("Choose an option: ");
                    choice  = scanner.nextInt();
                    switch(choice) {
                        case 1:
                            if (range.contains("messaging")) {
                                System.out.println("choicePeriod = " + choice);
                            } else {
                                System.out.println("daily user report");
                            }
                            break;
                        case 2:
                            if (range.contains("messaging")) {
                                System.out.println("monthly messaging report");
                            } else {
                                System.out.println("monthly user report");
                            }
                            break;
                        case 3:
                            if (range.contains("messaging")) {
                                System.out.println("yearly messaging report");
                            } else {
                                System.out.println("yearly user report");
                            }
                            break;
                        case 4:
                            this.starts();
                            break;
                        case 44:
                            CommonUtil.addTabs(10, true);
                            System.out.println("Going back");
                            break;
                        case 55:
                            System.out.println();
                            Component.showErrorMessage("System Closed");
                            System.exit(1);
                            break;
                        default:
                            Component.showErrorMessage("Enter a valid choice (1, 2, 3, 55): ");
                    }
                } catch (Exception e) {
                    System.out.println();
                    Component.showErrorMessage(e.getMessage());
                }
            if(choice == 44){
                break;
            }
            }
        }

    private void usersOperation() {
        int choice = 0;
        while(choice != 55 && choice != 44){
        Component.pageTitleView("USER");
        CommonUtil.addTabs(11, true);
        System.out.println("1. Invite user");
        CommonUtil.addTabs(11, false);
        System.out.println("2. Activate user");
        CommonUtil.addTabs(11, false);
        System.out.println("3. Deactivate user");
        CommonUtil.addTabs(11, false);
        System.out.println("4. View all users");
        CommonUtil.addTabs(11, false);
        System.out.println("5. View User logs");
        CommonUtil.addTabs(11, false);
        System.out.println(ConsoleColor.RegularColor.BLUE + "44" + ConsoleColor.RESET + ". Back");
        CommonUtil.addTabs(11, false);
        System.out.println(ConsoleColor.RegularColor.RED + "55" + ConsoleColor.RESET + ". Quit");


                try {
                    CommonUtil.addTabs(10, false);
                    CommonUtil.useColor("\u001b[43m");
                    System.out.print("  ");
                    CommonUtil.resetColor();
                    CommonUtil.useColor("\u001b[0;33m");
                    System.out.print(" Choose an option: ");
                    CommonUtil.resetColor();
                    choice = this.insertAdminChoice();
                    switch(choice) {
                        case 1:
                            InviteUsers();
                            break;
                        case 2:
                            System.out.println("choice 2");
                            break;
                        case 3:
                            System.out.println("choice 3");
                            break;
                        case 4:
                            new UserView(userId,writer,reader).allActiveUsers();
                            break;
                        case 5:
                            System.out.println("choice 5");
                            break;
                        case 6:
                            this.starts();
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
                            System.out.print("Enter a valid choice (1,5): ");
                            CommonUtil.resetColor();
                    }
                } catch (Exception var3) {
                    CommonUtil.addTabs(10, false);
                    CommonUtil.useColor("\u001b[1;31m");
                    System.out.println("is incorrect input");
                    CommonUtil.resetColor();
                }
            }
        }
    public void InviteUsers() throws IOException, JsonProcessingException {
        Component.pageTitleView("SEND INVITATION TO JOIN CLASS_C CHAT");
        Scanner scanner = new Scanner(System.in);
        List<String> emails = new ArrayList<String>();
        String email = "";
        CommonUtil.addTabs(12, false);
        CommonUtil.useColor(ConsoleColor.HighIntensityColor.CYAN_BRIGHT);
        System.out.println("* Type [-1] to stop writing emails *");
        CommonUtil.resetColor();
        System.out.println();
        while(!email.equals("-1")){
            CommonUtil.addTabs(11, false);
            System.out.print("Email: ");
            email = scanner.nextLine();
            if(!email.equals("-1")){
                if(!new ValidEmail(email).checkEmail()){
                    CommonUtil.resetColor();
                    CommonUtil.addTabs(11, true);
                    CommonUtil.useColor(ConsoleColor.BackgroundColor.RED_BACKGROUND);
                    CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
                    System.out.println("This email is not valid; it is not saved");
                    CommonUtil.resetColor();
                }
                else{
                    emails.add(email);
                }

            }
        }
        String key = "users/invite";
        Request request  = new Request(emails,key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        CommonUtil.addTabs(11, false);
        CommonUtil.useColor(ConsoleColor.BoldColor.BLUE_BOLD);
        System.out.println("Sending emails ...");
        CommonUtil.resetColor();

        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()){
            CommonUtil.addTabs(11, false);
            CommonUtil.addTabs(11, true);
            CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
            System.out.print("Emails sent successfully");
            CommonUtil.resetColor();

            System.out.println();
        }
        else{
            CommonUtil.addTabs(11, true);
            CommonUtil.useColor(ConsoleColor.BackgroundColor.RED_BACKGROUND);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
            System.out.print("Emails failed to send.");
            CommonUtil.resetColor();
        }
    }
    }
