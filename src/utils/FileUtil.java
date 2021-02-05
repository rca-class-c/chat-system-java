package utils;

import server.models.enums.FileSizeTypeEnum;
import server.config.Config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class FileUtil {

    public static  String getFileNameFromFilePath(String path) {
        String fileName = new File(path).getName();
        if (fileName.indexOf(".") > 0)
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
        return fileName;
    }

    public static long getFileSizeFromPath(String fileLocalPath) throws IOException {
        Path path = Paths.get(fileLocalPath);
        return Files.size(path);
    }

    public static String getFileSizeTypeFromFileSize(long size) {
        if (size >= (1024L * 1024 * 1024 * 1024))
            return FileSizeTypeEnum.TB.toString();
        else if (size >= 1024 * 1024 * 1024)
            return FileSizeTypeEnum.GB.toString();
        else if (size >= 1024 * 1024)
            return FileSizeTypeEnum.MB.toString();
        else if (size >= 1024)
            return FileSizeTypeEnum.KB.toString();
        else
            return FileSizeTypeEnum.TB.toString();
    }

    public static int getFormattedFileSizeFromFileSize(double size, FileSizeTypeEnum type ) {
        if (type == FileSizeTypeEnum.TB)
            return (int) (size / (1024L * 1024 * 1024 * 1024));
        else if (type == FileSizeTypeEnum.GB)
            return (int) (size / (1024 * 1024 * 1024));
        else if (type == FileSizeTypeEnum.MB)
            return (int) (size / (1024 * 1024));
        else if (type == FileSizeTypeEnum.KB)
            return (int) (size / (1024));
        else
            return (int) size;
    }

    public static String getFileTypeFromFilePath(String path) throws IOException {
        return Files.probeContentType(Path.of(path));
    }

    private static String getFileExtensionFromFile(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return "." + fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }


    public static String moveFile(String sourcePath, String originalFileName) {
        try {
            String fileName = generateUUID(originalFileName);
            File file = new File(String.valueOf(Paths.get(sourcePath)));
            String PATH = Config.getPublicFilesDirectory() + fileName + getFileExtensionFromFile(file);

            Files.copy(Paths.get(sourcePath), Paths.get(PATH), StandardCopyOption.REPLACE_EXISTING);
            return PATH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



//    C:\Users\DELL\OneDrive\Studies\Timetables\Time Table.pdf

    public static String generateUUID(String fileName) {
        return fileName + "-" +  UUID.randomUUID().toString().replace("-", "");
    }


}
