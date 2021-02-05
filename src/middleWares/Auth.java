package middleWares;
import config.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class Auth {
    public int id_DeleteUser = 1;
    public int id_createGroup = 2;
    public int id_Invite=3;
    public int id_DeleteGroup=4;
    public int id_DeActivateUser=5;
    public int id_viewStatistics=7;
    public int id_RemoveFromGroup=8;
    public int id_AddToGroup=9;
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
    public  boolean canviewStatistics(int cat_Id) throws SQLException {
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
        Auth auth=new Auth();
        boolean b=  auth.canRemoveFromGroup(5);
        System.out.println(b);
    }
}




