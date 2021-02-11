package client.views;

import client.interfaces.Request;
import client.interfaces.ResponseDataSuccessDecoder;
import client.interfaces.UserResponseDataDecoder;
import client.views.components.Component;
import client.views.components.TableView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

public class AdminAction {
    PrintWriter writer;
    BufferedReader reader;
    int userId;
    public AdminAction(PrintWriter writer,BufferedReader reader,int userId)
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
                choice = insertAdminChoice();
                switch (choice) {
                    case 1 -> this.chooseStat();
                    case 2 -> this.usersOperation();
                    case 3 -> System.out.println("back to profile setting");
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
                        System.out.println("Enter a valid choice (1,5): ");
                        CommonUtil.resetColor();
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


    private void chooseStat() {
        int choice = 0;
        while(choice != 55 && choice != 44){
        Component.pageTitleView("VIEW STATISTICS OF THE APP");
        CommonUtil.addTabs(10, true);
        System.out.println("1. message reports");
        CommonUtil.addTabs(10, false);
        System.out.println("2. user reports");
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
                        }
                        case 55->{
                            CommonUtil.addTabs(10, true);
                            CommonUtil.useColor("\u001b[1;31m");
                            System.out.println("SYSTEM CLOSED !");
                            System.exit(1);
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
                            TableView st = new TableView();
                            st.setShowVerticalLines(true);
                            st.setHeaders("one", "two", "three", "four");
                            st.addRow("super", "broccoli", "flexible", "there we are");
                            st.addRow("assumption", "announcement", "reflection", "");
                            st.addRow("logic", "pleasant", "wild", "weel doen all ");
                            st.print();
                            break;
                        
                        case 5: System.out.println("choice 5");
                        break;
                        case 6 : this.starts();
                        break;
                        case 44 :
                            CommonUtil.addTabs(10, true);
                            System.out.println("Going back");
                        
                        case 55 :
                            CommonUtil.addTabs(10, true);
                            CommonUtil.useColor("\u001b[1;31m");
                            System.out.println("SYSTEM CLOSED !");
                            System.exit(1);
                        break;
                        default :
                            CommonUtil.addTabs(10, false);
                            CommonUtil.useColor("\u001b[1;31m");
                            System.out.print("Enter a valid choice (1,5): ");
                            CommonUtil.resetColor();
                        break;
                    }

                } catch (Exception var3) {
                    CommonUtil.addTabs(10, false);
                    CommonUtil.useColor("\u001b[1;31m");
                    System.out.println("is incorrect input");
                    CommonUtil.resetColor();
                }
            }
        }

        public void InviteUsers() throws IOException {
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
                    emails.add(email);
                }
            }
            String key = "send_email_invitation";
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
    }
