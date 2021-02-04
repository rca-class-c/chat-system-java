package utils;

import config.Config;
import models.FileSizeTypeEnum;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommonUtil {
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
        Map<String, String> map = new HashMap<String, String>();
        String[] parameters = string.split("&");
        for (String parameter: parameters) {
            String[] mapping = parameter.split("=");
            map.put(mapping[0], mapping[1]);
        }
        return map;
    }

}
