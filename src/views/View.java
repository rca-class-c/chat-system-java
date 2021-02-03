package views;
import controllers.UserController;
import models.AuthInput;
import models.User;
import utils.CommonUtil;
import utils.ConsoleColor;
import utils.UserIterator;
import views.components.Component;

import java.sql.SQLException;
import java.util.Scanner;

public class View {

    public static void loginView() throws SQLException {
        UserController userControl = new UserController();
        utils.UserIterator userIterator = new UserIterator();
        userIterator.printUsers(userControl.getAllUser());

        Scanner scanner = new Scanner(System.in);

        Component.pageTitleView("Login to your Account");

        CommonUtil.addTabs(10, false);
        System.out.print("Enter Your Username: ");
        String username = scanner.nextLine();

        CommonUtil.addTabs(10, false);
        System.out.print("Enter your Password: ");
        String password = scanner.nextLine();
        AuthInput input = new AuthInput(username,password);
        UserController userController = new UserController();
       User newUser =  userController.loginUser(input);
       System.out.println(newUser.username);
//        if (username.equals("admin") && password.equals("admin@123")) {
//            CommonMethod.addTabs(10, true);
//            CommonMethod.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
//            CommonMethod.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
//            System.out.print(" Successfully Logged In  ");
//            CommonMethod.resetColor();
//        } else {
//            CommonMethod.addTabs(10, true);
//            CommonMethod.useColor(ConsoleColor.BackgroundColor.RED_BACKGROUND);
//            CommonMethod.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
//            System.out.print("  Invalid Username or Password  ");
//            CommonMethod.resetColor();
//        }
    }

    public static void createAccountView() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Component.pageTitleView("Create a new account");


        CommonUtil.addTabs(10, false);
        System.out.print("Enter your Username: ");
        String username = scanner.nextLine();


        CommonUtil.addTabs(10, false);
        System.out.print("Enter your FirstName: ");
        String firstName = scanner.nextLine();

        CommonUtil.addTabs(10, false);
        System.out.print("Enter your LastName: ");
        String lastName = scanner.nextLine();

        CommonUtil.addTabs(10, false);
        System.out.print("Enter your Email: ");
        String email = scanner.nextLine();

        CommonUtil.addTabs(10, false);
        System.out.print("Enter your Gender: ");
        String gender = scanner.nextLine();

        CommonUtil.addTabs(10, false);
        System.out.print("Enter your DOB: ");
        String dob = scanner.nextLine();

        CommonUtil.addTabs(10, false);
        System.out.print("Enter your Password: ");
        String password = scanner.nextLine();
        User user = new User(firstName,lastName,password,email,dob,username,gender,1,"ACTIVE");
        UserController userController = new UserController();
        userController.saveUser(user);

        CommonUtil.addTabs(10, true);
        CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
        CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
        System.out.print(" Account Created Successfully");
        CommonUtil.resetColor();

    }

    public static void sendFileView() {
        FileView fileView = new FileView();
        fileView.sendFileView();
    }


}
