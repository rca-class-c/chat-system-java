package server.controllers;

import server.models.File;
import server.services.FileService;

import java.util.Map;


public class FileController {

    private final FileService fileService = new FileService();

//    public String saveFile(Map<String, String> body) {
//        try {
//            File file = new File(body.get("fileLocalPath"), body.get("fileName"), body.get("fileType"), Integer.parseInt(body.get("fileSize")), body.get("fileSizeType"), Integer.parseInt(body.get("senderId")));
//            return this.fileService.saveItem(file);
//        }
//        catch (Exception e) {
//            return e.getMessage();
//        }
//
//    }
}
