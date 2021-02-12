package utils;

import server.models.FileSizeTypeEnum;
import server.config.PostegresConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


/**
 * Common File Utilities Class
 * @author Divin Irakiza
 */
public class FileUtil {

    /**
     * GetFileName from a path
     * @param path File Path
     * @return String fileName
     */
    public static  String getFileNameFromFilePath(String path) {
        String fileName = new File(path).getName();
        if (fileName.indexOf(".") > 0)
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
        return fileName;
    }

    /**
     * Gert fileSize from a path
     * @param fileLocalPath File Local Path
     * @return long File Size
     * @throws IOException
     */
    public static long getFileSizeFromPath(String fileLocalPath) throws IOException {
        Path path = Paths.get(fileLocalPath);
        return Files.size(path);
    }

    /**
     * Get FileSizeType from fileSize
     * @param size File Size
     * @return String of FileSizedTypeEnum
     */
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


    /**
     * Get formatted fileSize from file Size
     * @param size File size
     * @param type FileSize type
     * @return int formattedFileSize
     */
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

    /**
     * Get File Type From File Path
     * @param path String File Path
     * @return String fileType
     * @throws IOException
     */
    public static String getFileTypeFromFilePath(String path) throws IOException {
        return Files.probeContentType(Path.of(path));
    }

    /**
     * Get File Extension From File
     * @param file File
     * @return String fileExtension
     */
    private static String getFileExtensionFromFile(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return "." + fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }


    /**
     * Move File to a Path
     * @param sourcePath Source File Path
     * @param originalFileName Original File Name
     * @return String newFilePath
     */
    public static String moveFile(String sourcePath, String originalFileName) {
        try {
            String fileName = generateUUID(originalFileName);
            File file = new File(String.valueOf(Paths.get(sourcePath)));
            String PATH = PostegresConfig.getPublicFilesDirectory() + fileName + getFileExtensionFromFile(file);

            Files.copy(Paths.get(sourcePath), Paths.get(PATH), StandardCopyOption.REPLACE_EXISTING);
            return PATH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



//    C:\Users\DELL\OneDrive\Studies\Timetables\Time Table.pdf

    /**
     * Generate Random UUID
     * @param fileName FileName
     * @return String UUID
     */
    public static String generateUUID(String fileName) {
        return fileName + "-" +  UUID.randomUUID().toString().replace("-", "");
    }


}
