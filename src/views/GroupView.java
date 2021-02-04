package views;

//import controllers.GroupController;
import utils.CommonUtil;
import utils.ConsoleColor;
import views.components.Component;

import java.util.Scanner;

public class GroupView {
//    private final GroupController groupController = new GroupController();
    public void createGroupView() {
        Scanner scanner = new Scanner(System.in);

        Component.pageTitleView("Just a simple test for file sending");

        try {
            CommonUtil.addTabs(10, false);
            System.out.print("Enter the Group name: ");
            String name = scanner.nextLine();

            CommonUtil.addTabs(10, false);
            System.out.print("Enter the Group description: ");
            String description = scanner.nextLine();

            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);

            CommonUtil.resetColor();
//            Group group = new Group(name,description,2);
//
//
//            this.groupController.create(group);


        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
