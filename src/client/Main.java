//package client;
//
//import client.views.AdminAction;
//import utils.ConsoleColor;
//import utils.CommonUtil;
////import client.views.MessagesView;
//import client.views.components.Component;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//public class Main {
//    private static String SERVER_HOSTNAME;
//    private static int SERVER_PORT;
//
//
//    public static int getServerPort() {
//        return SERVER_PORT;
//    }
//
//    public static String getServerHostname() {
//        return SERVER_HOSTNAME;
//    }
//
//    public static void setServerHostname(String serverHostname) {
//        SERVER_HOSTNAME = serverHostname;
//    }
//
//    public static void setServerPort(int serverPort) {
//        SERVER_PORT = serverPort;
//    }
//
//    public static String getServerAPIURL() {
//        return "http://" + getServerHostname() + ":" + getServerPort();
//    }
//
//
//    public static void main(String[] args) {
//        landingView();
//    }
//
//    static void appNav() {
//        Component.pageTitleView("WELCOME TO CHAT SYSTEM IN JAVA");
//    }
//
//
//    static void landingView() {
////    	MessagesView messages = new MessagesView();
//        int action = -1;
//
//        appNav();
//
//        CommonUtil.addTabs(10, true);
//        System.out.println("1. Login");
//        CommonUtil.addTabs(10, false);
//        System.out.println("2. Create an Account");
//        CommonUtil.addTabs(10, false);
//        System.out.println("3. Send a File");
//        CommonUtil.addTabs(10, false);
//
//        System.out.println("4. View Notifications");
//        CommonUtil.addTabs(10, false);
//        System.out.println("5. Quit");
//        CommonUtil.addTabs(10, false);
//        System.out.println("6. Send a Message");
//        CommonUtil.addTabs(10, false);
//        CommonUtil.useColor(ConsoleColor.BackgroundColor.YELLOW_BACKGROUND);
//        System.out.print("  ");
//        CommonUtil.resetColor();
//
//        CommonUtil.useColor(ConsoleColor.RegularColor.YELLOW);
//        System.out.print(" Choose an option: ");
//        CommonUtil.resetColor();
//
//            do {
//                try {
//                    CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
//
//                    Scanner scanner = new Scanner(System.in);
//                    action = scanner.nextInt();
//                    CommonUtil.useColor(ConsoleColor.RESET);
//                    //                    loginView();
//                    switch (action) {
//                        case 1 -> {
//                            CommonUtil.resetColor();
//                            View.loginView();
//                        }
//                        case 2 -> {
//                            View.createAccountView();
//                            System.out.println(2);
//                        }
//                        case 3 -> {
//                            View.sendFileView();
//                        }
//                        case 4 -> {
//                            CommonUtil.resetColor();
//                            AdminAction admin = new AdminAction();
//                        }
//                        case 5 -> {
//                            CommonUtil.addTabs(10, true);
//                            Component.byeBye();
//                            CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
//                            System.out.println("SYSTEM CLOSED !");
//                            System.exit(1);
//                        }
//                        case 6 -> {
//                           View.sendMessageView.OptionsView();
//                        }
//                        default -> {
////                            action = -1;
//                            CommonUtil.addTabs(10, false);
//                            CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
//                            System.out.print("Enter a valid choice (1, 2): ");
//                            CommonUtil.resetColor();
//                        }
//                    }
//                }
//                 catch ( SQLException e) {
//                    CommonUtil.addTabs(10, false);
//                    CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
//                    System.out.print("Only numbers allowed: ");
//                    CommonUtil.resetColor();
//                }
//                catch (Exception e) {
//                    CommonUtil.addTabs(10, false);
//                    CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
//                    System.out.print("There was an error getting notification!");
//                    CommonUtil.resetColor();
//                }
//            } while (action == -1);
//
//    }
//
//
//}
