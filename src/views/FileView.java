package views;

import controllers.FileController;
import models.File;
import utils.CommonMethod;
import utils.ConsoleColor;
import views.components.Component;

import java.util.Scanner;

public class FileView {

    private final FileController fileController = new FileController();
    public void sendFileView() {
        Scanner scanner = new Scanner(System.in);

        Component.pageTitleView("Just a simple test for file sending");

        try {
            CommonMethod.addTabs(10, false);
            System.out.print("Enter your File Name: ");
            String fileName = scanner.nextLine();

            CommonMethod.addTabs(10, false);
            System.out.print("Enter your File Type: ");
            String fileType = scanner.nextLine();

            CommonMethod.addTabs(10, false);
            System.out.print("Enter your File Size Type: ");
            String fileSizeType = scanner.nextLine();


            CommonMethod.addTabs(10, false);
            System.out.print("Enter your File Size: ");
            int fileSize = scanner.nextInt();


            CommonMethod.addTabs(10, false);
            System.out.print("Enter The sender Id: ");
            int senderId = scanner.nextInt();

            CommonMethod.addTabs(10, true);
            CommonMethod.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
            CommonMethod.useColor(ConsoleColor.BoldColor.WHITE_BOLD);

            CommonMethod.resetColor();
            File file = new File(fileName, fileType, fileSize, fileSizeType, senderId);
            this.fileController.saveFile(file);
//            System.out.println(this.fileController.saveFile(file));

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
