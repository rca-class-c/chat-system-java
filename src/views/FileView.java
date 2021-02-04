package views;

import config.Config;
import controllers.FileController;
import models.FileSizeTypeEnum;
import utils.CommonUtil;
import utils.ConsoleColor;
import utils.FileUtil;
import views.components.Component;

import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;

import java.util.Scanner;

public class FileView {

    public void sendFileView() {
        Scanner scanner = new Scanner(System.in);

        Component.pageTitleView("Just a simple test for file sending");

        try {
            CommonUtil.addTabs(10, false);
            System.out.print("Enter your File Path: ");
            String fileLocalPath = scanner.nextLine().trim();

            CommonUtil.addTabs(10, false);
            System.out.print("Enter The sender Id: ");
            int senderId = scanner.nextInt();



            String fileName = FileUtil.getFileNameFromFilePath(fileLocalPath);
            String fileType = FileUtil.getFileTypeFromFilePath(fileLocalPath);
            String fileSizeType = FileUtil.getFileSizeTypeFromFileSize(FileUtil.getFileSizeFromPath(fileLocalPath));
            int fileSize = FileUtil.getFormattedFileSizeFromFileSize(FileUtil.getFileSizeFromPath(fileLocalPath), FileSizeTypeEnum.valueOf(fileSizeType));


            CommonUtil.addTabs(10, true);
            CommonUtil.useColor(ConsoleColor.HighIntensityBackgroundColor.GREEN_BACKGROUND_BRIGHT);
            CommonUtil.useColor(ConsoleColor.BoldColor.WHITE_BOLD);

            CommonUtil.resetColor();

            String requestBody = String.format("fileLocalPath=%s&fileName=%s&fileType=%s&fileSize=%d&fileSizeType=%s&senderId=%d",
                    fileLocalPath, fileName, fileType, fileSize, fileSizeType, senderId);

            sendPOST(Config.API_URL + "/api/files", requestBody, "Mozilla/5.0");

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
