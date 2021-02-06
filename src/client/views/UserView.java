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
            if(choice == 4){
                allActiveUsers();
            }
            else if(choice == 1){
                MyProfile();
            }
        }while(choice != 6);

    }

    public void allActiveUsers(){
        Component.pageTitleView("ACTIVE USERS LIST");
        CommonUtil.addTabs(10, true);
        System.out.println("1. chanelle740");
        CommonUtil.addTabs(10, false);
        System.out.println("2. edine-noella");
        CommonUtil.addTabs(10, false);
        System.out.println("3. divin-irakiza");
        CommonUtil.addTabs(10, false);
        System.out.println("4. Hortance-irakoze");
        CommonUtil.addTabs(10, false);
        System.out.println("5. Loraine");
        Component.chooseOptionInputView("Type any number to go to main page: ");
        int choice  = scanner.nextInt();
    }

    public void MyProfile(){
        Component.pageTitleView("MY PROFILE");
        CommonUtil.addTabs(10, false);
        System.out.println("FIRST NAME:  Didier");
        CommonUtil.addTabs(10, false);
        System.out.println("LAST NAME:  Munezero");
        CommonUtil.addTabs(10, false);
        System.out.println("USERNAME:  Didien");
        CommonUtil.addTabs(10, false);
        System.out.println("EMAIL:  didier@gmail.com");
        CommonUtil.addTabs(10, false);
        System.out.println("GENDER:  male");
        CommonUtil.addTabs(10, false);
        System.out.println("PASSWORD:  ***********");

        Component.chooseOptionInputView("Type any number to go to main page: ");
        int choice  = scanner.nextInt();

    }
}
