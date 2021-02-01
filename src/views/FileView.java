package views;

import config.Config;
import controllers.FileController;
import models.File;
import utils.CommonMethod;
import utils.ConsoleColor;
import views.components.Component;

import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLConnection;

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
//            this.fileController.saveFile(file);

            String input ="userName=Pankaj&name=djksaf";

            System.out.println(CommonMethod.mapRequestString(input));

            sendPOST(Config.API_URL + "/api/files", input, "Mozilla/5.0");
//            System.out.println(this.fileController.saveFile(file));

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }

    private static void sendPOST(String POST_URL, String POST_PARAMS, String USER_AGENT) throws IOException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }
}
