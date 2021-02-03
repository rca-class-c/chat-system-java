package views;

import controllers.GroupMemberController;

import models.Group_member;
import utils.CommonMethod;
import utils.ConsoleColor;
import views.components.Component;

import java.util.Scanner;

public class GroupMemberView {
    private final GroupMemberController groupMemberController = new GroupMemberController();

    public void createGroupMemberView() {
        Scanner scanner = new Scanner(System.in);

        Component.pageTitleView("Just A simple test for creating a group member");

        try {
            CommonMethod.addTabs(10, false);
            System.out.print("Enter the Group id: ");
            int groupID = scanner.nextInt();

            CommonMethod.addTabs(10, false);
            System.out.print("Enter the user id: ");
            int userID = scanner.nextInt();

            CommonMethod.addTabs(10, true);
            CommonMethod.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
            CommonMethod.useColor(ConsoleColor.BoldColor.WHITE_BOLD);

            CommonMethod.resetColor();
            Group_member group_member = new Group_member(groupID,userID);


            this.groupMemberController.CreateGroupMember(group_member);


        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
