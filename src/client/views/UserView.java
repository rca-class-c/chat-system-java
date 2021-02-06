package client.views;

import client.views.components.Component;
import utils.CommonUtil;

import java.util.Scanner;

public class UserView {
    Scanner scanner = new Scanner(System.in);
    public void viewOptions(){
        Component.pageTitleView("USER Dashboard");
        int choice = 0;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            CommonUtil.addTabs(10, true);
            System.out.println("1. MY PROFILE");
            CommonUtil.addTabs(10, false);
            System.out.println("2. MAKE A CHAT");
            CommonUtil.addTabs(10, false);
            System.out.println("3. ALL USERS");
            CommonUtil.addTabs(10, false);
            System.out.println("4. ACTIVE USERS");
            CommonUtil.addTabs(10, false);
            System.out.println("5. NOTIFICATIONS");
            CommonUtil.addTabs(10, false);
            System.out.println("6. LOGOUT");
            Component.chooseOptionInputView("Choose an option: ");
            choice  = scanner.nextInt();
        }while(choice != 6);

    }
}
