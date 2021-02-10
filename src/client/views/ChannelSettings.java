package client.views;

import client.interfaces.Request;
import client.views.components.Component;
import utils.CommonUtil;

public class ChannelSettings {

    public static void channelMenu(){
        Component.pageTitleView("CHANNEL SETTINGS");
        CommonUtil.addTabs(10, true);
        System.out.println("1. View channels");
        CommonUtil.addTabs(10, false);
        System.out.println("2. Create channel");
        CommonUtil.addTabs(10, false);
        System.out.println("0. Go back");
        CommonUtil.addTabs(10, false);
        System.out.println("4. Quit");
        while(true) {
            try {
                CommonUtil.addTabs(10, false);
                CommonUtil.useColor("\u001b[43m");
                System.out.print("  ");
                CommonUtil.resetColor();
                CommonUtil.useColor("\u001b[0;33m");
                System.out.print(" Choose an option: ");
                //This one works
                CommonUtil.resetColor();
                int choice = AdminAction.insertAdminChoice();
                switch(choice) {
                    case 1:
                        System.out.println("channels");
                        break;
                    case 2:
                        System.out.println("chann");
                        break;
                    case 0:
                        System.out.println("you should return back");
                        break;
                    case 4:
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
        }
    }
    public void createChanel(){
        Component.pageTitleView("Create new channel");
        CommonUtil.addTabs(10, true);
        System.out.println("Type channel:");
        Request request = new Request(new Object(),"get_my_groups");

    }
}
