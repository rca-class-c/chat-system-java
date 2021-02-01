package utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommonMethod {
    /**
     * Use a Console Color
     * @param color
     */
    public static void useColor (String color) {
        System.out.print(color);
    }

    /**
     * Reset Color to Console default
     */
    public static void resetColor () {
        System.out.print(ConsoleColor.RESET);
    }

    /**
     * Style Text
     * @param tabCount
     */
    public static void addTabs(int tabCount, Boolean newLine) {
        if (newLine)
            System.out.print("\n" + "\t".repeat(Math.max(0, tabCount)));
        else
            System.out.print("" + "\t".repeat(Math.max(0, tabCount)));

    }


    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {}

    }

    public static Map<String, String> mapRequestString(String string) {
        System.out.println("jksdjksd");
        Map<String, String> map = new HashMap<String, String>();
        String[] parameters = string.split("&");
        for (String parameter: parameters) {
            String[] mapping = parameter.split("=");
            map.put(mapping[0], mapping[1]);
        }
        return map;
    }
}
