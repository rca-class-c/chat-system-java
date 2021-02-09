package server.services;
import server.models.File;
import server.repositories.FileRepository;

import java.sql.SQLException;
import java.util.Map;


/**
 * Files Services provider
 * @author: Divin Irakiza
 */
public class FileService {
    private final FileRepository fileRepository = new FileRepository();

    public File saveFile(File file) {
        try {
            return fileRepository.save(file);
        }
        catch (Exception e) {
            return null;
        }
    }
}


