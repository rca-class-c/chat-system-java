package views;

import controllers.FileController;
import controllers.Message;
import models.File;
import models.Messages;
import utils.CommonMethod;
import utils.ConsoleColor;
import views.components.Component;

import java.util.Scanner;

public class MessageView {

    private final Message message = new Message();
    public void sendMessageGroup() {
        Scanner scanner = new Scanner(System.in);

        Component.pageTitleView("Just a simple test for file sending");

        try {
            CommonMethod.addTabs(10, false);
            System.out.print("Enter your message: ");
            String name = scanner.nextLine();



            CommonMethod.addTabs(10, true);
            CommonMethod.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
            CommonMethod.useColor(ConsoleColor.BoldColor.WHITE_BOLD);

            CommonMethod.resetColor();
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
