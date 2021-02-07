package client.views.components;

import utils.*;
//import utils.CommonUtil;
//import utils.ConsoleColor;
import java.util.Locale;

import java.util.Properties;

import java.util.Scanner;


/**
 * View Reusable Components
 * @author Divin Irakiza
 */
public class Component {

    /**
     * Set a page TitleView
     * @param title pageTitle
     */
    public static void pageTitleView(String title) {
        title = title.toUpperCase(Locale.ROOT);
        CommonUtil.clearScreen();
        CommonUtil.useColor(ConsoleColor.BoldColor.GREEN_BOLD);
        CommonUtil.addTabs(23, true);
        CommonUtil.useColor(ConsoleColor.UnderlineColor.GREEN_UNDERLINED);
        System.out.println(title);
        CommonUtil.resetColor();
    }

    /**

     * @author damour
     * printing  the bye key word in when person leave the sytem
     */
    public static void byeBye(){
        CommonUtil.addTabs(10, true);
        System.out.print(" ************************************************************");
        CommonUtil.addTabs(10, true);
        System.out.print("*                        ");
        CommonUtil.useColor(ConsoleColor.RegularColor.YELLOW);
        System.out.print("CHAT SYSTEM");
        CommonUtil.resetColor();
        System.out.print("                           *");
        CommonUtil.addTabs(10, true);
        System.out.print("*                                                              *");
        CommonUtil.addTabs(10, true);
        System.out.print("*                                                              *");
        CommonUtil.addTabs(10, true);
        System.out.print("*                                                              *");
        CommonUtil.addTabs(10, true);
        System.out.print("*                                                              *");
        CommonUtil.addTabs(10, true);
        System.out.print("*                                                              *");
        CommonUtil.addTabs(10, true);
        System.out.print("*                                                              *");
        CommonUtil.addTabs(10, true);
        System.out.println("  *************************************************************");
        CommonUtil.addTabs(10, true);

    }
/**
     * Print Styled Exception Message
     * @param error Error Message
     */
    public static void showErrorMessage(String error) {
        CommonUtil.addTabs(11, false);
        CommonUtil.useColor(ConsoleColor.BoldColor.RED_BOLD);
        System.out.print(error);
        CommonUtil.resetColor();
    }

    /**
     * Styles the choose option
     * @param title Choose Option Title
     */
    public static void chooseOptionInputView(String title) {
        CommonUtil.addTabs(11, false);

        CommonUtil.useColor(ConsoleColor.BackgroundColor.YELLOW_BACKGROUND);
        System.out.print("  ");
        CommonUtil.resetColor();

        CommonUtil.useColor(ConsoleColor.RegularColor.YELLOW);
        System.out.print(title);
        CommonUtil.resetColor();
    }


    /**
     * Styles the choose option from choices
     */
    public static int getChooseOptionChoice() {
        int action;
        CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);

        Scanner scanner = new Scanner(System.in);
        action = scanner.nextInt();
        CommonUtil.useColor(ConsoleColor.RESET);

        return action;
    }



}
