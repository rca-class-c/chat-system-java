package client.views;

import client.interfaces.Request;
import client.interfaces.ResponseDataSuccessDecoder;
import client.interfaces.UserResponseDataDecoder;
import client.views.components.Component;
import client.views.components.TableView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.services.ReportsServices;
import utils.CommonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import utils.CommonUtil;
import client.views.components.TableView;
import utils.Mailing;
import utils.ValidEmail;

public class AdminAction {
    PrintWriter writer;
    BufferedReader reader;
    int userId;
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
        Component.pageTitleView("ADMIN ACTIVITIES");
        CommonUtil.addTabs(10, true);
        System.out.println("1. Statistics");
        CommonUtil.addTabs(10, false);
        System.out.println("2. Users");
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
                choice = this.insertAdminChoice();
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
        Component.pageTitleView("VIEW STATISTICS OF THE APP");
        CommonUtil.addTabs(10, true);
        System.out.println("1. message reports");
        CommonUtil.addTabs(10, false);
        System.out.println("2. user reports");
        CommonUtil.addTabs(10, false);
        System.out.println("2. group reports");
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
                    choice = this.insertAdminChoice();
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
                        case 3 -> {
                            System.out.flush();
                            CommonUtil.clearScreen();
                            this.choosePeriod("group report");
                        }
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
        Component.pageTitleView("CHOOSE " + range.toUpperCase(Locale.ROOT) + " REPORT");
        CommonUtil.addTabs(10, true);
        System.out.println("1. Daily");
        CommonUtil.addTabs(10, false);
        System.out.println("2. Monthly");
        CommonUtil.addTabs(10, false);
        System.out.println("3. Yearly");
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
                    choice = insertAdminChoice();
                    switch(choice) {
                        case 1:
                            if (range.contains("messaging")) {
                                List<List> allStats = new ReportsServices().getMessageReport();
                                printStatatics(allStats,"message:");

                            } else if(range.contains("user report")) {
                                List<List> allStats = new ReportsServices().getUserReport();
                                printStatatics(allStats,"user:");
                            }else{
                                List<List> allStats = new ReportsServices().getGroupReport();
                                printStatatics(allStats,"group:");
                            }
                            break;
                        case 2:
//                            if (range.contains("messaging")) {
//                                List<List> allStats = new ReportsServices().getMessageReport();
//                                printStatatics(allStats);
//                            } else {
//                                List<List> allStat = new ReportsServices().getUserReport();
//                                printStatatics(allStat);
//                            }
                            break;
                        case 3:
                            if (range.contains("messaging")) {
                                List<List> allStats = new ReportsServices().getGroupReport();
                                printStatatics(allStats,"message:");
                            } else {
                                List<List> allStats = new ReportsServices().getGroupReport();
                                printStatatics(allStats,"group");
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
            if(choice == 44){
                break;
            }
            }
        }

    private void usersOperation() {
        int choice = 0;
        while(choice != 55 && choice != 44){
        Component.pageTitleView("USER OPERATIONS");
        CommonUtil.addTabs(10, true);
        System.out.println("1. Invite user");
        CommonUtil.addTabs(10, false);
        System.out.println("2. Reactivate user");
        CommonUtil.addTabs(10, false);
        System.out.println("3. Deactivate user");
        CommonUtil.addTabs(10, false);
        System.out.println("4. VIEW all user");
        CommonUtil.addTabs(10, false);
        System.out.println("5. View user logs");
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
                    choice = insertAdminChoice();
                    switch (choice) {
                        case 1 -> InviteUsers();
                        case 2 -> System.out.println("choice 2");
                        case 3 -> System.out.println("choice 3");
                        case 4 -> new UserView(userId, writer, reader).allActiveUsers();
                        case 5 -> System.out.println("choice 5");
                        case 6 -> this.starts();
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
                            CommonUtil.addTabs(10, false);
                            CommonUtil.useColor("\u001b[1;31m");
                            System.out.print("Enter a valid choice (1,5): ");
                            CommonUtil.resetColor();
                        }
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
        Component.pageTitleView("SEND INVITATION TO OTHERS TO JOIN CLASS_C CHAT");
        Scanner scanner = new Scanner(System.in);
        List<String> emails = new ArrayList<String>();
        String email = "";
        CommonUtil.addTabs(10, true);
        System.out.println("Type quit to stop writing emails");
        while(!email.equals("quit")){
            CommonUtil.resetColor();
            CommonUtil.useColor("\u001b[0;33m");
            CommonUtil.addTabs(10, false);
            System.out.print(" User email: ");
            CommonUtil.resetColor();
            email = scanner.nextLine();
            CommonUtil.useColor("\u001b[0m");
            if(!email.equals("quit")){
                if(!new ValidEmail(email).checkEmail()){
                    CommonUtil.addTabs(10, true);
                    CommonUtil.useColor("\u001b[1;31m");
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
        CommonUtil.addTabs(10, false);
        System.out.println("Sending emails ...");
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()){
            CommonUtil.addTabs(10, false);
            System.out.println("Email sent successfully");
        }
        else{
            CommonUtil.addTabs(10, false);
            System.out.println("Email failed to send.");
        }
    }

    /**
     * method to print out the formatted statistics
     * @param all
     */
    private void printStatatics(List<List> all,String trimStr){
        for (int i = 0; i < all.get(0).size(); i++) {
            String out = String.format("%d %s %10s",i+1,all.get(0).get(i).toString().replace(trimStr,""),all.get(1).get(i));
            CommonUtil.addTabs(10, false);
            System.out.println(out);

        }
    }
    }
