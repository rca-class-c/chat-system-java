package repositories;

import config.Config;
import models.File;
import models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {
    public User save(User user) throws SQLException {
        try {
            Connection connection = Config.getConnection();
            Statement statement = connection.createStatement();

            String query = String.format("INSERT INTO users(first_name, last_name, username, email, gender, pass_word,dob,status,categoryid) VALUES (" +
                    "'%s','%s','%s','%s','%s','%s','%s','%s',%d);", user.getFname(), user.getLname(), user.getUsername(), user.getEmail(), user.getGender(), user.getPassword(),user.getDob(),user.getStatus(),user.getCategoryID());

            System.out.println(query);

            int i = statement.executeUpdate(query);
            System.out.println("Rows inserted: "+i);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
}
