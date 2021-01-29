package services;

import models.File;
import repositories.FileRepository;

import java.sql.SQLException;

public class FileService {
    private final FileRepository fileRepository = new FileRepository();

    public File saveItem(File file) throws SQLException {
        return fileRepository.save(file);
    }
}


