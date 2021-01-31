package controllers;

import models.File;
import services.FileService;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class FileController {

    private final FileService fileService = new FileService();

    public File saveFile(File file) throws SQLException {
        return this.fileService.saveItem(file);
    }

}
