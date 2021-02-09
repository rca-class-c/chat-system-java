package server.repositories;
import server.models.Permission;
import server.models.categories;
import server.config.Config;

import java.sql.*;
import java.util.LinkedList;

public class categoryRepository {
    public categories saveCategory(categories categories) throws SQLException {
        Connection connection = Config.getConnection();
        String query = " Insert into user_categories (name,created_at,updated_at) VALUES(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, categories.getCategoryName());
        statement.setDate(2, categories.getCreated_at());
        statement.setDate(3, categories.getUpdated_at());

        int row = statement.executeUpdate();
        if (row != -1) {
            System.out.println("Saved The Category");
        } else {
            System.out.println("Failed To Save The Category ");
        }
        connection.close();
        return categories;
    }

    public int updateCategory(categories categories, int id) throws SQLException {
        Connection connection = Config.getConnection();
        String query = "UPDATE user_categories SET name = ? ,updated_at=? WHERE categoryid=? ";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, categories.getCategoryName());
        statement.setDate(2, (Date) categories.getCreated_at());
        statement.setInt(3, id);
        int row = statement.executeUpdate();
        connection.close();
        if (row != -1) {
            System.out.println("Category Updated");
            return 1;
        } else {
            System.out.println("Category Not Updated");
            return 0;
        }
    }

    public categories getCategory(int id) throws SQLException {
        categories categories = new categories();
        Connection connection = Config.getConnection();
        String query = "SELECT name  from user_categories WHERE category_id=? ";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            categories.setCategoryName(resultSet.getString("name"));
            categories.setCategoryId(id);
            categories.setCreated_at(resultSet.getDate("created_at"));
            categories.setUpdated_at(resultSet.getDate("updated_at"));
            System.out.println("Category Found");
            return categories;
        } else {
            System.out.println("No Category Found");
            return null;
        }
    }

    public LinkedList<categories> getAllCategories() throws SQLException {
        categories categories = new categories();
        Connection connection = Config.getConnection();
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
        Connection connection = Config.getConnection();
        String query = " DELETE from user_categories where category_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        int rows = statement.executeUpdate();
        if (rows != -1) {
            System.out.println("Category Deleted Safely");
            return 1;
        } else {
            System.out.println("User Not Deleted");
            return 0;
        }
    }

    public int AssignPermissionToCategory(int categoryId, int permissionId) throws SQLException {
        int flag=0;
        PermissionRepository PermRepository = new PermissionRepository();
        Connection connection = Config.getConnection();
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
                System.out.println("Category " + categories.getCategoryName() + " Given " + permission.getPermission_name());
                flag=1;
            } else {
                System.out.println("Failed");
            }
        }
        return flag;
    }
}
