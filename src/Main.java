
import utils.ConsoleColor;
import utils.CommonMethod;
import views.View;
import views.components.Component;


import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        landingView();
    }

    static void appNav() {
        Component.pageTitleView("WELCOME TO CHAT SYSTEM IN JAVA");
    }

    static void landingView() {
        int action = -1;

        appNav();

        CommonMethod.addTabs(10, true);
        System.out.println("1. Login");
        CommonMethod.addTabs(10, false);
        System.out.println("2. Create an Account");
        CommonMethod.addTabs(10, false);
        System.out.println("3. Send a File");
        CommonMethod.addTabs(10, false);
        System.out.println("4. Quit");
        CommonMethod.addTabs(10, false);
        CommonMethod.useColor(ConsoleColor.BackgroundColor.YELLOW_BACKGROUND);
        System.out.print("  ");
        CommonMethod.resetColor();

        CommonMethod.useColor(ConsoleColor.RegularColor.YELLOW);
        System.out.print(" Choose an option: ");
        CommonMethod.resetColor();

            do {try {
                    CommonMethod.useColor(ConsoleColor.BoldColor.WHITE_BOLD);

                    Scanner scanner = new Scanner(System.in);
                    action = scanner.nextInt();
                    CommonMethod.useColor(ConsoleColor.RESET);
                    switch (action) {
                        case 1:
//                    loginView();
                            CommonMethod.resetColor();
                            View.loginView();
                            break;
                        case 2:
                            View.createAccountView();
                            System.out.println(2);
                            break;
                        case 3:
                            CommonMethod.resetColor();
                            View.sendFileView();
                            break;
                        case 4:
                            CommonMethod.addTabs(10, true);
                            CommonMethod.useColor(ConsoleColor.BoldColor.RED_BOLD);
                            System.out.println("SYSTEM CLOSED !");
                            System.exit(1);
                            break;
                        default:
                            action = -1;
                            CommonMethod.addTabs(10, false);
                            CommonMethod.useColor(ConsoleColor.BoldColor.RED_BOLD);
                            System.out.print("Enter a valid choice (1, 2): ");
                            CommonMethod.resetColor();
                    }
                }
                 catch (InputMismatchException | SQLException e) {
                    CommonMethod.addTabs(10, false);
                    CommonMethod.useColor(ConsoleColor.BoldColor.RED_BOLD);
                    System.out.print("Only numbers allowed: ");
                    CommonMethod.resetColor();
                }
            } while (action == -1);

    }


}
