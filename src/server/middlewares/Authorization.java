package server.middlewares;

import server.config.PostegresConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Authorization methods checkers
 * @author: Pretty Diane
 */
public class Authorization {
    private int id_DeleteUser ;
    private int id_createGroup ;
    private int id_Invite;
    private int id_DeActivateUser;
    private int id_viewStatistics;
    private int id_SendMessage;
    private int id_Admin;




    //Checking if a user with a certain category id is allowed to delete a user. We pass the category id to the
    // function and it returns a boolean

    public void getIds() throws SQLException{

        String sql = "select name ,permission_id from permissions ";
        Connection connection = PostegresConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String name=resultSet.getString("name");
            int id= resultSet.getInt("permission_id");
          System.out.println(name+" ID:"+id);
            switch (name) {
                case "DELETE_USER" -> id_DeleteUser = id;
                case "CREATE_GROUP" -> id_createGroup = id;
                case "INVITE_USER" -> id_Invite = id;
                case "DEACTIVATE_USER" -> id_DeActivateUser = id;
                case "VIEW_STATISTICS" -> id_viewStatistics = id;
                case "SEND_MESSAGE" -> id_SendMessage = id;
            }
        }

     System.out.println(id_SendMessage);
    }

// checking if the user is allowed  to delete another user, if the function returns true then the person is allowed else
    //user is unauthorized.
    public  boolean canDeleteUser(int cat_Id) throws SQLException {
        boolean allowed = false;
        String sql = "select permission_id from user_category_permissions where category_id=?";
        Connection connection = PostegresConfig.getConnection();
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
        Connection connection = PostegresConfig.getConnection();
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
        Connection connection = PostegresConfig.getConnection();
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




    //Checking if a user with a certain category id is allowed to  view statistics. We pass the category id to the
    // function and it returns a boolean , true if allowed and false if not allowed

    public  boolean canViewStatistics(int cat_Id) throws SQLException {
        boolean allowed = false;
        String sql = "select permission_id from user_category_permissions where category_id=?";
        Connection connection = PostegresConfig.getConnection();
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
        Connection connection = PostegresConfig.getConnection();
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







    //used to check if a user can send a message;
    public boolean canSendMessage(int cat_Id) throws SQLException{
        boolean allowed = false;
        String sql = "select permission_id from user_category_permissions where category_id=?";
        Connection connection = PostegresConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cat_Id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int permission_id = resultSet.getInt("permission_id");
            if(permission_id==id_SendMessage){
                allowed=true;
            }
        }
        return allowed;
    }  ;

    ///a function for finding out if the person is an admin  or not
    public boolean isAdmin(int cat_Id) throws SQLException{
        boolean isAdmin=false;
        String sql = "select categoryid from user_categories where name='ADMIN' ";
        Connection connection = PostegresConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            id_Admin=(resultSet.getInt("categoryid"));
            if(id_Admin==cat_Id){
                isAdmin=true;
            }
        }
        return isAdmin;
    }  ;

    //this function is for finding out if a person is a groupAdmin and which  group did he create

    public boolean groupCreater(int user_id, String groupName) throws SQLException {
      boolean allowed=false;
        String sql = "select group_name from groups where group_creator=?";
        Connection connection = PostegresConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, user_id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
           String group_name=resultSet.getString("group_name");
         if(group_name.equals(groupName)){
             allowed=true;
         }

        }

       return allowed;


    }



}