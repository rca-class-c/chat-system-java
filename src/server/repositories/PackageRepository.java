package server.repositories;

import server.config.PostegresConfig;
import server.models.Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Package {
    public Package savePackage(Pac categories) throws SQLException {
        Connection connection = PostegresConfig.getConnection();
        String query = " Insert into user_categories (name,created_at,updated_at) VALUES(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, categories.getCategoryName());
        statement.setDate(2, categories.getCreated_at());
        statement.setDate(3, categories.getUpdated_at());

        int row = statement.executeUpdate();

        connection.close();
        return categories;
    }
}
