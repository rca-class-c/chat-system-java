package server.middlewares;

import server.config.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Authorization methods checkers
 * @author: Pretty Diane
 */
public class Authorization {
    private int id_DeleteUser = 1;
    private int id_createGroup = 2;
    private int id_Invite=3;
    private int id_DeleteGroup=4;
    private int id_DeActivateUser=5;
    private int id_viewStatistics=7;
    private int id_RemoveFromGroup=8;
    private int id_AddToGroup=9;


    public void getIds()throws SQLException{
        String sql = "select name from permissions";
        Connection connection = Config.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String store=resultSet.getString("name");
            System.out.println(store);

        }
    }




    //Checking if a user with a certain category id is allowed to delete a user. We pass the category id to the
    // function and it returns a boolean

    public  boolean canDeleteUser(int cat_Id) throws SQLException {
        boolean allowed = false;
        String sql = "select permission_id from user_category_permissions where category_id=?";
        Connection connection = Config.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cat_Id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int permission_id = resultSet.getInt("permission_id");
            if(permission_id==id_DeleteUser){
                allowed=true;
            }
        }
        return allowed;
    };

    //Checking if a user with a certain category id is allowed to create a group. We pass the category id to the
    // function and it returns a boolean , true if allowed and false if not allowed
    public  boolean canCreateGroup(int cat_Id) throws SQLException {
        boolean allowed = false;
        String sql = "select permission_id from user_category_permissions where category_id=?";
        Connection connection = Config.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cat_Id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int permission_id = resultSet.getInt("permission_id");
            if(permission_id==id_createGroup){
                allowed=true;
            }
        }
        return allowed;
    };


    //Checking if a user with a certain category id is allowed to invite another person to the system. We pass the category id to the
    // function and it returns a boolean , true if allowed and false if not allowed

    public  boolean canInvite(int cat_Id) throws SQLException {
        boolean allowed = false;
        String sql = "select permission_id from user_category_permissions where category_id=?";
        Connection connection = Config.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cat_Id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int permission_id = resultSet.getInt("permission_id");
            if(permission_id==id_Invite){
                allowed=true;
            }
        }
        return allowed;
    };


    //Checking if a user with a certain category id is allowed to  delete a group. We pass the category id to the
    // function and it returns a boolean , true if allowed and false if not allowed

    public  boolean canDeleteGroup(int cat_Id) throws SQLException {
        boolean allowed = false;
        String sql = "select permission_id from user_category_permissions where category_id=?";
        Connection connection = Config.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cat_Id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int permission_id = resultSet.getInt("permission_id");
            if(permission_id==id_DeleteGroup){
                allowed=true;
            }
        }
        return allowed;
    };

    //Checking if a user with a certain category id is allowed to  view statistics. We pass the category id to the
    // function and it returns a boolean , true if allowed and false if not allowed

    public  boolean canViewStatistics(int cat_Id) throws SQLException {
        boolean allowed = false;
        String sql = "select permission_id from user_category_permissions where category_id=?";
        Connection connection = Config.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cat_Id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int permission_id = resultSet.getInt("permission_id");
            if(permission_id==id_viewStatistics){
                allowed=true;
            }
        }
        return allowed;
    };

    //Checking if a user with a certain category id is allowed to  de activate another user. We pass the category id to the
    // function and it returns a boolean , true if allowed and false if not allowed

    public  boolean canDeactivateUser(int cat_Id) throws SQLException {
        boolean allowed = false;
        String sql = "select permission_id from user_category_permissions where category_id=?";
        Connection connection = Config.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cat_Id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int permission_id = resultSet.getInt("permission_id");
            if(permission_id==id_DeActivateUser){
                allowed=true;
            }
        }
        return allowed;
    };


    //Checking if a user with a certain category id is allowed to add a user to a group. We pass the category id to the
    // function and it returns a boolean , true if allowed and false if not allowed

    public  boolean canAddToGroup(int cat_Id) throws SQLException {
        boolean allowed = false;
        String sql = "select permission_id from user_category_permissions where category_id=?";
        Connection connection = Config.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cat_Id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int permission_id = resultSet.getInt("permission_id");
            if(permission_id==id_AddToGroup){
                allowed=true;
            }
        }
        return allowed;
    };


    //Checking if a user with a certain category id is allowed to  remove some one from a group. We pass the category id to the
    // function and it returns a boolean , true if allowed and false if not allowed

    public  boolean canRemoveFromGroup(int cat_Id) throws SQLException {
        boolean allowed = false;
        String sql = "select permission_id from user_category_permissions where category_id=?";
        Connection connection = Config.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cat_Id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int permission_id = resultSet.getInt("permission_id");
            if(permission_id==id_RemoveFromGroup){
                allowed=true;
            }
        }
        return allowed;
    };
    public static void main(String[] args) throws SQLException {
        Authorization auth=new Authorization();
//        boolean b=  auth.canRemoveFromGroup(5);
//        System.out.println(b);
        auth.getIds();
    }
}