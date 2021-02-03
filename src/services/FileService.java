package services;
import java.util.UUID;
import models.File;
import repositories.FileRepository;

import java.sql.SQLException;

public class FileService {
    private final FileRepository fileRepository = new FileRepository();

    public String saveItem(File file) throws SQLException {
        return fileRepository.save(file);
    }

}


