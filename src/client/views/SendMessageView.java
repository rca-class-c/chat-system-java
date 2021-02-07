package client.views;

import client.views.components.Component;
import utils.*;


public class SendMessageView {
    public static void OptionsView() {
        Component.pageTitleView("Send a Message");

        CommonUtil.addTabs(11, true);
        System.out.println("1. Direct Message");
        CommonUtil.addTabs(11, false);
        System.out.println("2. Message a group");

        Component.chooseOptionInputView("Choose an option: ");

        int action;
        do {
            action = Component.getChooseOptionChoice();
            try {
                switch (action) {
                    case 1 -> {
                        CommonUtil.resetColor();
                        DirectMessageView();
                    }
                    case 2 -> {
                         GroupMessageView();
                    }
                    default -> {
                        action = -1;
//                        CommonUtil.addTabs(10, false);
//                        CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
//                        System.out.print("Enter a valid choice (1, 2): ");
//                        CommonUtil.resetColor();
                        Component.showErrorMessage("Enter a valid choice (1, 2): ");

                    }
                }
            } catch (Exception e) {
                Component.showErrorMessage(e.getMessage());
            }
        } while (action == -1);

    }


    public static void DirectMessageView() {
        Component.pageTitleView("Direct Message");

        CommonUtil.addTabs(11, true);
        System.out.println("1. List all Users");
        CommonUtil.addTabs(11, false);
        System.out.println("2. Search a User (names)");
        CommonUtil.addTabs(11, false);
        System.out.println("3. Enter a user ID");

        Component.chooseOptionInputView("Choose an option: ");


        int action;
        do {
            action = Component.getChooseOptionChoice();
            try {
                CommonUtil.resetColor();
                switch (action) {
                    case 1 -> {
                        GetAllUsersView();
                    }
                    case 2 -> {
                        SearchUserView();
                    }
                    case 3 -> {
                        UserIdView();
                    }
                    default -> {
                        action = -1;
                        Component.showErrorMessage("Enter a valid choice (1, 2): ");
                    }
                }
            } catch (Exception e) {
                Component.showErrorMessage(e.getMessage());
            }
        } while (action == -1);
    }

    public static void GroupMessageView() {
        Component.pageTitleView("Group Message");

        CommonUtil.addTabs(11, true);
        System.out.println("1. List all Groups");
        CommonUtil.addTabs(11, false);
        System.out.println("2. Search a Group (name)");
        CommonUtil.addTabs(11, false);
        System.out.println("3. Enter a group ID");

        Component.chooseOptionInputView("Choose an option: ");

        int action;
        do {
            action = Component.getChooseOptionChoice();
            try {
                CommonUtil.resetColor();
                switch (action) {
                    case 1 -> {
                        GetAllGroupsView();
                    }
                    case 2 -> {
                        SearchGroupView();
                    }
                    case 3 -> {
                        GroupIdView();
                    }
                    default -> {
                        action = -1;
                        Component.showErrorMessage("Enter a valid choice (1, 2): ");
                    }
                }
            } catch (Exception e) {
                Component.showErrorMessage(e.getMessage());
            }
        } while (action == -1);
    }


    public static void GetAllUsersView() {
        Component.pageTitleView("Users List");

        System.out.println("1. All users list");
    }



    public static void SearchUserView() {
        Component.pageTitleView("Search a User");

        Component.chooseOptionInputView("Search: ");
    }

    public static void UserIdView() {
        Component.pageTitleView("Get User");

        Component.chooseOptionInputView("Enter User Id: ");
    }

    public static void GetAllGroupsView() {
        Component.pageTitleView("Groups List");

        System.out.println("1. All groups list");
    }



    public static void SearchGroupView() {
        Component.pageTitleView("Search a Group");

        Component.chooseOptionInputView("Search: ");
    }

    public static void GroupIdView() {
        Component.pageTitleView("Get Group");

        Component.chooseOptionInputView("Enter Group Id: ");
    }

}
