package repositories;

import config.Config;
import models.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class FileRepository {

    public File save(File file) throws SQLException {
      try {

          Connection connection = Config.getConnection();
          Statement statement = connection.createStatement();

          String query = String.format("INSERT INTO files(url, file_name, file_type, file_size, file_size_type, sender_id) VALUES (" +
                  "%s, %s, %s, %s, %s, %d);", file.getUrl(), file.getFileName(), file.getFileType(), file.getFileSize(), file.getFileSizeType(), file.getSenderId());



          int i = statement.executeUpdate(query);
          System.out.println("Rows inserted: "+i);

          statement.close();
          connection.close();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
        return file;
    }


}
