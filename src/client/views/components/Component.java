package client.views.components;

import utils.CommonUtil;
import utils.ConsoleColor;

import java.util.Locale;
import java.util.Scanner;

//import utils.CommonUtil;
//import utils.ConsoleColor;


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
        System.out.println();
        title = title.toUpperCase(Locale.ROOT);
        CommonUtil.clearScreen();
        CommonUtil.useColor(ConsoleColor.BoldColor.GREEN_BOLD);
        CommonUtil.addTabs(10, true);
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
     * Alert Exception Message
     * @param error Error Message
     */
    public static void alertDangerErrorMessage(String error) {
        CommonUtil.addTabs(11, true);
        CommonUtil.useColor(ConsoleColor.BackgroundColor.RED_BACKGROUND);
        CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
        System.out.print(" " + error + " ");
        CommonUtil.resetColor();
    }


    public static void alertSuccessErrorMessage(String error) {
        CommonUtil.addTabs(10, true);
        CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
        CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);
        System.out.print(" " + error + " ");
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


    public static void listItemView(String key, String value) {
        CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.CYAN_BOLD_BRIGHT);
        System.out.print("=>");
        CommonUtil.resetColor();
        CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.WHITE_BOLD_BRIGHT);
        System.out.print(" " + key + ": ");
        CommonUtil.resetColor();
        CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.YELLOW_BOLD_BRIGHT);
        System.out.print(value);
        CommonUtil.resetColor();
        System.out.println();
//        System.out.println(ConsoleColor.BoldHighIntensityColor.CYAN_BOLD_BRIGHT + "=>" + ConsoleColor.RESET + ConsoleColor.BoldHighIntensityColor.WHITE_BOLD_BRIGHT + " " + key + ":  " + ConsoleColor.RESET +  ConsoleColor.BoldHighIntensityColor.YELLOW_BOLD_BRIGHT + value + ConsoleColor.RESET);
    }


    public static void goBackUIView() {
        CommonUtil.addTabs(3, true);
        CommonUtil.useColor(ConsoleColor.BoldHighIntensityColor.PURPLE_BOLD_BRIGHT);
        System.out.println("<- Going back");
        CommonUtil.resetColor();
    }

}
