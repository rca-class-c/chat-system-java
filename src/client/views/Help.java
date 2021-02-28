package client.views;

import client.views.components.Component;
import utils.CommonUtil;
import utils.ConsoleColor;

import java.util.Scanner;

public class Help {
    public  static void Reach() {
        int choice = 0;
        Scanner scanner = new Scanner(System.in);

        do{
        Component.pageTitleView("Help center");
        CommonUtil.addTabs(10, false);
        System.out.println("Have been stack? this is your right place go back");


            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.BoldColor.PURPLE_BOLD);
            System.out.println("BASIC KEYS");
            CommonUtil.resetColor();
            CommonUtil.addTabs(10, false);
        System.out.println("55: This is a key that will let you quit the system.");
        CommonUtil.addTabs(10, false);
        System.out.println("44: This key is for going back.");


            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.BoldColor.PURPLE_BOLD);
            System.out.println("SENDING FILE");
            CommonUtil.resetColor();
            CommonUtil.addTabs(10, false);
            System.out.println("If a link hasn’t already been created, click Create Link on the Can Edit or Can View option ");
            CommonUtil.addTabs(10, false);
            System.out.println("file or folder that you want to send via email and click");
            CommonUtil.addTabs(10, false);
            System.out.println("to set a password, expiration date or other permission ");


            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.BoldColor.PURPLE_BOLD);
            System.out.println("PROFILE");
            CommonUtil.resetColor();
            CommonUtil.addTabs(10, false);
            System.out.println("ter a Sender Name If using SMTP Auth, the sender name may be overwritt");
            CommonUtil.addTabs(10, false);
            System.out.println("TP Auth, the sender name may be overwritten depending on");


            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.BoldColor.PURPLE_BOLD);
            System.out.println("FEEDBACK");
            CommonUtil.resetColor();
            CommonUtil.addTabs(10, false);
            System.out.println("feedback email is an email which primarily contains");
            CommonUtil.addTabs(10, false);
            System.out.println("ently requested, especially when one wants to know");
            CommonUtil.addTabs(10, false);
            System.out.println(" and strong points of a business or an individual");


            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.BoldColor.PURPLE_BOLD);
            System.out.println("GROUPS");
            CommonUtil.resetColor();
            CommonUtil.addTabs(10, false);
            System.out.println("feedback email is an email which primarily contains");
            CommonUtil.addTabs(10, false);
            System.out.println("ently requested, especially when one wants to know");
            CommonUtil.addTabs(10, false);
            System.out.println(" and strong points of a business or an individual");

            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.BoldColor.PURPLE_BOLD);
            System.out.println("OPTIONS");
            CommonUtil.resetColor();
            CommonUtil.addTabs(11, false);
            System.out.println("1. Re-read");
            CommonUtil.addTabs(11, false);
            System.out.println(ConsoleColor.RegularColor.BLUE + "44" + ConsoleColor.RESET + ". Back");
            CommonUtil.addTabs(11, false);
            System.out.println(ConsoleColor.RegularColor.RED + "55" + ConsoleColor.RESET + ". Quit");
            try {
                Component.chooseOptionInputView("Choose an option: ");
                choice  = scanner.nextInt();
                switch(choice) {
                    case 1:
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
        }while(choice != 44 && choice != 55);

    }
}
