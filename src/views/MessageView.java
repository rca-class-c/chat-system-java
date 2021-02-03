package views;

import controllers.Message;
import utils.CommonUtil;
import utils.ConsoleColor;
import views.components.Component;

import java.util.Scanner;

public class MessageView {

    private final Message message = new Message();
    public void sendMessageGroup() {
        Scanner scanner = new Scanner(System.in);

        Component.pageTitleView("Just a simple test for file sending");

        try {
            CommonUtil.addTabs(10, false);
            System.out.print("Enter your message: ");
            String name = scanner.nextLine();



            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);

            CommonUtil.resetColor();
//            Messages messages = new Messages(name, 1, 2,2);
//
//
//            this.message.sendInGroup(messages);


        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
