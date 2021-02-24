package server.services;
import server.models.File;
import server.repositories.FileRepository;

import java.sql.SQLException;
import java.util.Map;


/**
 * FilesService Provider
 * @author: Divin Irakiza
 */
public class FileService {
    private final FileRepository fileRepository = new FileRepository();

    /**
     * Saves File
     * @param file File to save
     * @return File
     */
    public File saveFile(File file) {
        try {
            return fileRepository.save(file);
        }
        catch (Exception e) {
            return null;
        }
    }
}


