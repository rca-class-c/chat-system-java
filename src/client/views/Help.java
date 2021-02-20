package client.views;

import client.views.components.Component;
import utils.CommonUtil;
import utils.ConsoleColor;

public class Help {
    public  static void Reach() {
        int choice = 0;
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


            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.BoldColor.PURPLE_BOLD);
            System.out.println("SENDING FILE");
            CommonUtil.resetColor();
            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.BoldColor.PURPLE_BOLD);
            System.out.println("PROFILE");
            CommonUtil.resetColor();
            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.BoldColor.PURPLE_BOLD);
            System.out.println("FEEDBACK");
            CommonUtil.resetColor();
            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.BoldColor.PURPLE_BOLD);
            System.out.println("GROUPS");
            CommonUtil.resetColor();


            CommonUtil.useColor(ConsoleColor.BoldColor.PURPLE_BOLD);
            System.out.println("OPTIONS");
            CommonUtil.resetColor();
            CommonUtil.addTabs(10, false);
            System.out.println("1. re- read");
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
                choice = AdminAction.insertAdminChoice();
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
