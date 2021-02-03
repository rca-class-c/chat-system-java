
import utils.ConsoleColor;
import utils.CommonMethod;
import views.View;
import views.MessagesView;
import views.components.Component;
import controllers.Token;


import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        landingView();
    }

    static void appNav() {
        Component.pageTitleView("WELCOME TO CHAT SYSTEM IN JAVA");
    }

    static void landingView() {

        TreeMap<String,String> payload = new TreeMap<String,String>();

        payload.put("name","liberi");

        Token token = new Token(payload);

        System.out.println(token.generateToken(1, ChronoUnit.DAYS));

        //my test code



        //end of my test code

//
//
//    	MessagesView messages = new MessagesView();
//        int action = -1;
//
//        appNav();
//
//        CommonMethod.addTabs(10, true);
//        System.out.println("1. Login");
//        CommonMethod.addTabs(10, false);
//        System.out.println("2. Create an Account");
//        CommonMethod.addTabs(10, false);
//        System.out.println("3. Send a File");
//        CommonMethod.addTabs(10, false);
//        System.out.println("4. View Notifications");
//        CommonMethod.addTabs(10, false);
//        System.out.println("5. Quit");
//        CommonMethod.addTabs(10, false);
//        CommonMethod.useColor(ConsoleColor.BackgroundColor.YELLOW_BACKGROUND);
//        System.out.print("  ");
//        CommonMethod.resetColor();
//
//        CommonMethod.useColor(ConsoleColor.RegularColor.YELLOW);
//        System.out.print(" Choose an option: ");
//        CommonMethod.resetColor();
//
//            do {
//                try {
//                    CommonMethod.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
//
//                    Scanner scanner = new Scanner(System.in);
//                    action = scanner.nextInt();
//                    CommonMethod.useColor(ConsoleColor.RESET);
//                    switch (action) {
//                        case 1:
////                    loginView();
//                            CommonMethod.resetColor();
//                            View.loginView();
//                            break;
//                        case 2:
//                            View.createAccountView();
//                            System.out.println(2);
//                            break;
//                        case 3:
//                            CommonMethod.resetColor();
//                            View.sendFileView();
//                            break;
//                        case 4:
//                            CommonMethod.resetColor();
//                            messages.printNotifications();
//                            break;
//                        case 5:
//                            CommonMethod.addTabs(10, true);
//                            CommonMethod.useColor(ConsoleColor.BoldColor.RED_BOLD);
//                            System.out.println("SYSTEM CLOSED !");
//                            System.exit(1);
//                            break;
//                        default:
//                            action = -1;
//                            CommonMethod.addTabs(10, false);
//                            CommonMethod.useColor(ConsoleColor.BoldColor.RED_BOLD);
//                            System.out.print("Enter a valid choice (1, 2): ");
//                            CommonMethod.resetColor();
//                    }
//                }
//                 catch ( SQLException e) {
//                    CommonMethod.addTabs(10, false);
//                    CommonMethod.useColor(ConsoleColor.BoldColor.RED_BOLD);
//                    System.out.print("Only numbers allowed: ");
//                    CommonMethod.resetColor();
//                }
//                catch (Exception e) {
//                    CommonMethod.addTabs(10, false);
//                    CommonMethod.useColor(ConsoleColor.BoldColor.RED_BOLD);
//                    System.out.print("There was an error getting notification!");
//                    CommonMethod.resetColor();
//                }
//            } while (action == -1);

    }


}
