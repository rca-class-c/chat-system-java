package repositories;

import config.Config;
import models.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class FileRepository {

    public String save(File file) {
      try {

          Connection connection = Config.getConnection();
          Statement statement = connection.createStatement();

          String query = String.format("INSERT INTO files(url, file_name, file_type, file_size, file_size_type, sender_id) VALUES ('SDFAsdfa', " +
                  "'%s', '%s', '%s', '%s', %d);", file.getFileName(), file.getFileType(), file.getFileSize(), file.getFileSizeType(), file.getSenderId());

          int i = statement.executeUpdate(query);

          if (i > 0) {
              return "File Saved Successfully";
          }

          statement.close();
          connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
        return "";
    }


}
