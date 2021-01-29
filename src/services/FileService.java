package services;

import models.File;
import repository.FileRepository;

import java.sql.SQLException;
import java.util.List;

public class FileService {
    private final FileRepository fileRepository = new FileRepository();

    public File saveItem(File file) throws SQLException {
        return fileRepository.save(file);
    }
}
