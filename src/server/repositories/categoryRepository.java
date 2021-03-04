package server.repositories;
import server.models.Permission;
import server.models.categories;
import server.config.PostegresConfig;

import java.sql.*;
import java.util.LinkedList;

/**
 * @author  Cyusa Munezero Keny . Github : UnrealDrift
 */
public class categoryRepository {
    public categories saveCategory(categories categories) throws SQLException {
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

    public int updateCategory(categories categories, int id) throws SQLException {
        Connection connection = PostegresConfig.getConnection();
        String query = "UPDATE user_categories SET name = ? ,updated_at=? WHERE categoryid=? ";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, categories.getCategoryName());
        statement.setDate(2, (Date) categories.getCreated_at());
        statement.setInt(3, id);
        int row = statement.executeUpdate();
        connection.close();
        if (row != -1) {
            return 1;
        } else {
            return 0;
        }
    }

    public categories getCategory(int id) throws SQLException {
        categories categories = new categories();
        Connection connection = PostegresConfig.getConnection();
        String query = "SELECT name  from user_categories WHERE categoryid=? ";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            categories.setCategoryName(resultSet.getString("name"));
            categories.setCategoryId(id);
            categories.setCreated_at(resultSet.getDate("created_at"));
            categories.setUpdated_at(resultSet.getDate("updated_at"));

            return categories;
        } else {

            return null;
        }
    }

    public LinkedList<categories> getAllCategories() throws SQLException {
        categories categories = new categories();
        Connection connection = PostegresConfig.getConnection();
        String query = "SELECT *  from user_categories ";
        LinkedList<categories> linkedList = new LinkedList<>();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            categories.setCategoryName(resultSet.getString("name"));
            categories.setCategoryId(resultSet.getInt("categoryid"));
            categories.setCreated_at(resultSet.getDate("created_at"));
            categories.setUpdated_at(resultSet.getDate("updated_at"));
            linkedList.add(categories);
        }
        if (resultSet.next()) {
            return linkedList;
        } else {
            return null;
        }

    }

    public int deleteCategory(int id) throws SQLException {
        Connection connection = PostegresConfig.getConnection();
        String query = " DELETE from user_categories where categoryid=?";
        PreparedStatement statement = connection.prepareStatement(query);
        int rows = statement.executeUpdate();
        if (rows != -1) {

            return 1;
        } else {

            return 0;
        }
    }

    public int AssignPermissionToCategory(int categoryId, int permissionId) throws SQLException {
        int flag=0;
        PermissionRepository PermRepository = new PermissionRepository();
        Connection connection = PostegresConfig.getConnection();
        categories categories = getCategory(categoryId);
        Permission permission = PermRepository.getPermission(permissionId);
        if (categories.getCategoryName()!=null && permission.getPermission_name()!=null) {
            final String query = "INSERT INTO user_category_permissions VALUES(?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, categories.getCategoryId());
            statement.setInt(2, permission.getPermission_id());
            int row = statement.executeUpdate();
            statement.close();
            connection.close();
            if (row != 1) {

                flag=1;
            }
        }
        return flag;
    }
}
