package server.repositories;

import server.config.PostegresConfig;
import server.models.File;
import utils.FileUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class FileRepository {
    public File save(File file) {
      try {

          Connection connection = PostegresConfig.getConnection();
          Statement statement = connection.createStatement();

          file.setUrl(uploadFileToServer(file));

          String query = String.format("INSERT INTO files(url, file_name, file_type, file_size, file_size_type, sender_id) VALUES (" +
                  "'%s', '%s', '%s', '%s', '%s', %d);", file.getUrl(), file.getFileName(), file.getFileType(), file.getFileSize(), file.getFileSizeType(), file.getSenderId());

          int i = statement.executeUpdate(query);

          if(i > 0) {
              return file;
          }

          statement.close();
          connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
        return null;
    }

    private String uploadFileToServer(File file) {
        try {
            return FileUtil.moveFile(file.getFileLocalPath(), file.getFileName());
        }
        catch (Exception ignored) { }
        return null;
    }

}
