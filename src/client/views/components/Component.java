package client.views.components;

import utils.CommonUtil;
import utils.ConsoleColor;

import java.util.Locale;
import java.util.Properties;

public class Component {
    public static void pageTitleView(String title) {
        title = title.toUpperCase(Locale.ROOT);
        CommonUtil.clearScreen();
        CommonUtil.useColor(ConsoleColor.BoldColor.GREEN_BOLD);
        CommonUtil.addTabs(20, true);
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
}
