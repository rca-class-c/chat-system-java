package views;

import controllers.GroupController;
import models.Group;
import utils.CommonMethod;
import utils.ConsoleColor;
import views.components.Component;

import java.util.Scanner;

public class GroupView {
    private final GroupController groupController = new GroupController();
    public void createGroupView() {
        Scanner scanner = new Scanner(System.in);

        Component.pageTitleView("Just a simple test for file sending");

        try {
            CommonMethod.addTabs(10, false);
            System.out.print("Enter the Group name: ");
            String name = scanner.nextLine();

            CommonMethod.addTabs(10, false);
            System.out.print("Enter the Group description: ");
            String description = scanner.nextLine();

            CommonMethod.addTabs(10, true);
            CommonMethod.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
            CommonMethod.useColor(ConsoleColor.BoldColor.WHITE_BOLD);

            CommonMethod.resetColor();
            Group group = new Group(name,description,2);


            this.groupController.create(group);


        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
