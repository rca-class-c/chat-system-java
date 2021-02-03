
import utils.ConsoleColor;
import utils.CommonUtil;
import views.View;
import views.MessagesView;
import views.components.Component;


import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        landingView();
    }

    static void appNav() {
        Component.pageTitleView("WELCOME TO CHAT SYSTEM IN JAVA");
    }

    static void landingView() {
    	MessagesView messages = new MessagesView();
        int action = -1;

        appNav();

        CommonUtil.addTabs(10, true);
        System.out.println("1. Login");
        CommonUtil.addTabs(10, false);
        System.out.println("2. Create an Account");
        CommonUtil.addTabs(10, false);
        System.out.println("3. Send a File");
        CommonUtil.addTabs(10, false);
        System.out.println("4. View Notifications");
        CommonUtil.addTabs(10, false);
        System.out.println("5. Quit");
        CommonUtil.addTabs(10, false);
        CommonUtil.useColor(ConsoleColor.BackgroundColor.YELLOW_BACKGROUND);
        System.out.print("  ");
        CommonUtil.resetColor();

        CommonUtil.useColor(ConsoleColor.RegularColor.YELLOW);
        System.out.print(" Choose an option: ");
        CommonUtil.resetColor();

            do {
                try {
                    CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);

                    Scanner scanner = new Scanner(System.in);
                    action = scanner.nextInt();
                    CommonUtil.useColor(ConsoleColor.RESET);
                    switch (action) {
                        case 1:
//                    loginView();
                            CommonUtil.resetColor();
                            View.loginView();
                            break;
                        case 2:
                            View.createAccountView();
                            System.out.println(2);
                            break;
                        case 3:
                            CommonUtil.resetColor();
                            View.sendFileView();
                            break;
                        case 4:
                            CommonUtil.resetColor();
                            messages.printNotifications();
                            break;    
                        case 5:
                            CommonUtil.addTabs(10, true);
                            CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
                            System.out.println("SYSTEM CLOSED !");
                            System.exit(1);
                            break;
                        default:
                            action = -1;
                            CommonUtil.addTabs(10, false);
                            CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
                            System.out.print("Enter a valid choice (1, 2): ");
                            CommonUtil.resetColor();
                    }
                }
                 catch ( SQLException e) {
                    CommonUtil.addTabs(10, false);
                    CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
                    System.out.print("Only numbers allowed: ");
                    CommonUtil.resetColor();
                }
                catch (Exception e) {
                    CommonUtil.addTabs(10, false);
                    CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
                    System.out.print("There was an error getting notification!");
                    CommonUtil.resetColor();
                }
            } while (action == -1);

    }


}
