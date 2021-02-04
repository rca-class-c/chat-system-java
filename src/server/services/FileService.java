package server.services;
import server.models.File;
import server.repositories.FileRepository;

import java.sql.SQLException;
import java.util.Map;

public class FileService {
    private final FileRepository fileRepository = new FileRepository();

    public String saveFile(Map<String, String> body) {
        try {
            File file = new File(body.get("fileLocalPath"), body.get("fileName"), body.get("fileType"), Integer.parseInt(body.get("fileSize")), body.get("fileSizeType"), Integer.parseInt(body.get("senderId")));
            return fileRepository.save(file);

        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}


