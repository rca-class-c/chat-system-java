package views;
import controllers.FileController;
import controllers.UserController;
import controllers.sendInvitations;
import models.AuthInput;
import models.User;
import controllers.login;
import org.postgresql.shaded.com.ongres.scram.common.ScramAttributes;
import utils.CommonMethod;
import utils.ConsoleColor;
import utils.UserIterator;
import views.components.Component;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.Scanner;

public class View {

    public static void loginView() throws SQLException {
        UserController userControl = new UserController();
        utils.UserIterator userIterator = new UserIterator();
        userIterator.printUsers(userControl.getAllUser());

        Scanner scanner = new Scanner(System.in);

        Component.pageTitleView("Login to your Account");

        CommonMethod.addTabs(10, false);
        System.out.print("Enter Your Email: ");
        String username = scanner.nextLine();

        CommonMethod.addTabs(10, false);
        System.out.print("Enter your Password: ");
        String password = scanner.nextLine();
        AuthInput input = new AuthInput(username,password);
        UserController userController = new UserController();
//        userController.loginUser(input);
        login.login(username,password);

    }

    public static void createAccountView() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Component.pageTitleView("Create a new account");


        CommonMethod.addTabs(10, false);
        System.out.print("Enter your Username: ");
        String username = scanner.nextLine();


        CommonMethod.addTabs(10, false);
        System.out.print("Enter your FirstName: ");
        String firstName = scanner.nextLine();

        CommonMethod.addTabs(10, false);
        System.out.print("Enter your LastName: ");
        String lastName = scanner.nextLine();

        CommonMethod.addTabs(10, false);
        System.out.print("Enter your Email: ");
        String email = scanner.nextLine();

        CommonMethod.addTabs(10, false);
        System.out.print("Enter your Gender: ");
        String gender = scanner.nextLine();

        CommonMethod.addTabs(10, false);
        System.out.print("Enter your DOB: ");
        String dob = scanner.nextLine();

        CommonMethod.addTabs(10, false);
        System.out.print("Enter your Password: ");
        String password = scanner.nextLine();
        User user = new User(firstName,lastName,password,email,dob,username,gender,1,"ACTIVE");
        UserController userController = new UserController();
        userController.saveUser(user);

        CommonMethod.addTabs(10, true);
        CommonMethod.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
        CommonMethod.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
        System.out.print(" Account Created Successfully");
        CommonMethod.resetColor();

    }

    public static void sendFileView() {
        FileView fileView = new FileView();
        fileView.sendFileView();
    }
    public  static  void sendInvitations() throws ClassNotFoundException, MessagingException, SQLException {
        Scanner scanner = new Scanner(System.in);

        Component.pageTitleView("Send Invitations");


        CommonMethod.addTabs(10, false);
        System.out.print("Enter your Email: ");
        String email = scanner.nextLine();

        CommonMethod.addTabs(10, false);
        System.out.print("Enter your Password: ");
        String password = scanner.nextLine();
        sendInvitations.sendingInvitations(email,password);
    }

    public static void createGroupView() {
        GroupView groupView= new GroupView();
        groupView.createGroupView();
    }

}
