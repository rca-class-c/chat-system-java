package views.components;

import utils.CommonMethod;
import utils.ConsoleColor;

import java.util.Locale;

public class Component {
    public static void pageTitleView(String title) {
        title = title.toUpperCase(Locale.ROOT);
        CommonMethod.clearScreen();
        CommonMethod.useColor(ConsoleColor.BoldColor.GREEN_BOLD);
        CommonMethod.addTabs(20, true);
        CommonMethod.useColor(ConsoleColor.UnderlineColor.GREEN_UNDERLINED);

        System.out.println(title);
        CommonMethod.resetColor();
    }
}
