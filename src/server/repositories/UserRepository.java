package server.repositories;

import server.components.Component;
import server.config.PostegresConfig;
import server.models.AuthInput;
import server.models.User;
import server.services.ReportsServices;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
/**
 * @Author: Kobusinge Shallon
 * */
public class UserRepository {
    /**
     * Method for saving the new User
     * */
    public boolean checkVerificationCode(int code){
        try{
            Connection connection = PostegresConfig.getConnection();


            String query = String.format("SELECT * FROM sent_invitations  WHERE verificationcode = '%d' and status = 'PENDING';",code);
            Statement statement =  connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if(rs.next()){
               return true;
            }
        }
        catch ( Exception e ) {

            System.err.println( e.getClass().getName()+": "+ e.getMessage() );

            System.exit(0);

        }
        return false;
    }
    public User save(User user) throws SQLException {
        int i= 0;
        try {
            Connection connection = PostegresConfig.getConnection();
            Statement statement = connection.createStatement();

            String query = String.format("INSERT INTO users(first_name, last_name, username, email, gender, pass_word,dob,status,categoryid) VALUES (" +
                    "'%s','%s','%s','%s','%s','%s','%s','%s',%d);", user.getFname(), user.getLname(), user.getUsername(), user.getEmail(), user.getGender(), user.getPassword(),user.getDob(),user.getStatus(),user.getCategoryID());


            i = statement.executeUpdate(query);


            statement.close();
            connection.close();
        } catch (SQLException e) {

            Component.showErrorMessage(e.getMessage());

        }
        if(i > 0) {
            new ReportsServices().insertUserReport();
            return user;
        }
        return null;
    }
    /**
     * Method for logging in
     * */
    public User login(AuthInput input) throws SQLException{
        try{
            Connection connection = PostegresConfig.getConnection();
            Statement statement =  connection.createStatement();

            String query = String.format("SELECT * FROM users where username  = '%s' and  pass_word = '%s';",input.getUsername(),input.getPassword());
            ResultSet rs = statement.executeQuery(query);

            if(rs.next()){
                new ReportsServices().insertVisitsReport();
                User returnUser =  new User(rs.getInt("user_id"),rs.getString("first_name"),rs.getString("last_name"),
                        rs.getString("pass_word"),rs.getString("email"),rs.getString("dob"),
                        rs.getString("username"),rs.getString("gender"),rs.getInt("categoryid"),
                        rs.getString("status"),rs.getString("created_at"),rs.getString("updated_at"));
                return returnUser;
            }
            else{

            }
        }
        catch ( Exception e ) {
            Component.showErrorMessage(e.getMessage());
         }
        return null;
    }


    /**
     * Method for getting users search list
     * */
    public List<User> getUserSearchList(String search) throws SQLException {
        try{
            Connection connection = PostegresConfig.getConnection();
            String query="SELECT * FROM users where first_name =? or last_name = ? or username = ? and status = 'ACTIVE' ORDER BY user_id ASC;";
            PreparedStatement statement=connection.prepareStatement(query);

            statement.setString(1, "%" + search + "%");
            statement.setString(2,"%" + search + "%");
            statement.setString(3, "%" + search + "%");
            ResultSet rs=statement.executeQuery();
            List<User> users=new ArrayList<User>();
            while(rs.next()){
                users.add(new User(rs.getInt("user_id"),rs.getString("first_name"),rs.getString("last_name"),
                        rs.getString("pass_word"),rs.getString("email"),rs.getString("dob"),
                        rs.getString("username"),rs.getString("gender"),rs.getInt("categoryid"),
                        rs.getString("status"),rs.getString("created_at"),rs.getString("updated_at")));
            }
            return users;
        }
        catch ( Exception e ) {

            System.err.println( e.getClass().getName()+": "+ e.getMessage() );

            System.exit(0);

        }
        return null;
    }
    /**
     * Method for getting all other users
     * */
    public List<User> getAllInactiveUsers() throws SQLException{
        try{
            Connection connection = PostegresConfig.getConnection();
            Statement statement =  connection.createStatement();

            String query = String.format("SELECT * FROM users where status = 'INACTIVE' ORDER BY user_id ASC;");
            ResultSet rs = statement.executeQuery(query);
            List<User> users=new ArrayList<User>();
            while(rs.next()){
                users.add(new User(rs.getInt("user_id"),rs.getString("first_name"),rs.getString("last_name"),
                        rs.getString("pass_word"),rs.getString("email"),rs.getString("dob"),
                        rs.getString("username"),rs.getString("gender"),rs.getInt("categoryid"),
                        rs.getString("status"),rs.getString("created_at"),rs.getString("updated_at")));
            }
            return users;
        }
        catch ( Exception e ) {

            System.err.println( e.getClass().getName()+": "+ e.getMessage() );

            System.exit(0);

        }
        return null;
    }
    public List<User> getAllOtherUsers(int id) throws SQLException{
        try{
            Connection connection = PostegresConfig.getConnection();
            Statement statement =  connection.createStatement();

            String query = String.format("SELECT * FROM users where user_id != '%d' AND status = 'ACTIVE' ORDER BY user_id ASC;",id);
            ResultSet rs = statement.executeQuery(query);
            List<User> users=new ArrayList<User>();
            while(rs.next()){
                users.add(new User(rs.getInt("user_id"),rs.getString("first_name"),rs.getString("last_name"),
                        rs.getString("pass_word"),rs.getString("email"),rs.getString("dob"),
                        rs.getString("username"),rs.getString("gender"),rs.getInt("categoryid"),
                        rs.getString("status"),rs.getString("created_at"),rs.getString("updated_at")));
            }
            return users;
        }
        catch ( Exception e ) {

            System.err.println( e.getClass().getName()+": "+ e.getMessage() );

            System.exit(0);

        }
        return null;
    }

    /**
     * Method for getting all user by id
     * */
    public User getUserById(int userID) throws SQLException{
        try{
            Connection connection = PostegresConfig.getConnection();


            String query = String.format("SELECT * FROM users  WHERE user_id = '%d' ;",userID);
            Statement statement =  connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if(rs.next()){

                User returnUser =  new User(rs.getInt("user_id"),rs.getString("first_name"),rs.getString("last_name"),
                        rs.getString("pass_word"),rs.getString("email"),rs.getString("dob"),
                        rs.getString("username"),rs.getString("gender"),rs.getInt("categoryid"),
                        rs.getString("status"));
                return returnUser;
            }
            else{

            }
        }
        catch ( Exception e ) {

            System.err.println( e.getClass().getName()+": "+ e.getMessage() );

            System.exit(0);

        }
        return null;
    }

    /**
     * Method for updating user which accepts the user to update as input and his/her id
     * */
    public User updateUser(User user,int userId) throws SQLException{

        int i= 0;
        try {
            Connection connection = PostegresConfig.getConnection();
            Statement statement = connection.createStatement();

            String query = String.format("UPDATE users SET first_name ='%s' , last_name = '%s', username = '%s'," +
                            " email = '%s', gender = '%s', pass_word = '%s' ,dob = '%s',status = '%s'," +
                            "categoryid = %d WHERE user_id = %d", user.getFname(), user.getLname(), user.getUsername(), user.getEmail(), user.getGender(),
                    user.getPassword(),user.getDob(),user.getStatus(),user.getCategoryID(),userId);


            i = statement.executeUpdate(query);


            statement.close();
            connection.close();
        } catch (SQLException e) {
            Component.showErrorMessage(e.getMessage());
        }
        if(i > 0) {
            return user;
        }
        return null;
       
    }
    /**
     * Activating user method
     * */
    public boolean ActivateUser(int userId) throws SQLException{

        int i= 0;
        try {
            Connection connection = PostegresConfig.getConnection();
            Statement statement = connection.createStatement();

            String query = String.format("UPDATE users SET status ='ACTIVE' WHERE user_id = %d",userId);


            i = statement.executeUpdate(query);


            statement.close();
            connection.close();
        } catch (SQLException e) {
            Component.showErrorMessage(e.getMessage());
        }
        if(i > 0) {
            return true;
        }
        return false;

    }
    public boolean DeActivateUser(int userId) throws SQLException{

        int i= 0;
        try {
            Connection connection = PostegresConfig.getConnection();
            Statement statement = connection.createStatement();

            String query = String.format("UPDATE users SET status ='INACTIVE' WHERE user_id = %d",userId);


            i = statement.executeUpdate(query);


            statement.close();
            connection.close();
        } catch (SQLException e) {
            Component.showErrorMessage(e.getMessage());
        }
        if(i > 0) {
            return true;
        }
        return false;

    }
    /**
     * Method for deleting user using id
     * */
    public int deleteUser(int userId) throws SQLException{

          int affectedRows = 0;

          Connection connection = PostegresConfig.getConnection();
          String query = String.format("DELETE FROM users WHERE user_id = ? ;");
          PreparedStatement statement = connection.prepareStatement(query);
          statement.setInt(1, userId);
          return affectedRows;
    }
    /**
     * Method for deactivating user
     * */
    public int deactivateUser(int userId) throws SQLException{
        int affectedRows = 0;
        Connection connection = PostegresConfig.getConnection();
        String query = String.format("UPDATE users SET status = 'INACTIVE' WHERE user_id = '%d';",userId);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,userId);

        return affectedRows;
    }

    /**
     * changing user password
     *
     * @param userId user id
     * @param password user new proposed password
     * @return true when password is updated, false when password not updated
     * @throws SQLException throws sql exception for any error
     * @author Ntwari Clarance Liberiste
     */
    public boolean changePasswordById(int userId, String password) throws SQLException{
        Connection connection = PostegresConfig.getConnection();
        String query = "UPDATE users SET pass_word = ? WHERE user_id = ?;";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1,password);
        statement.setInt(2,userId);

        int affectedRows = statement.executeUpdate();

        return affectedRows > 0;
    }

    /**
     *  changing user password
     *
     * @param email user email
     * @param password new user proposed password
     * @return true when password is updated, false when password not updated
     * @throws SQLException throws sql exception for any error
     * @author Ntwari Clarance Liberiste
     */
    public boolean changePasswordByEmail(String email, String password) throws SQLException{
        Connection connection = PostegresConfig.getConnection();
        String query = "UPDATE users SET pass_word = ? WHERE email = ?;";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1,password);
        statement.setString(2,email);

        int affectedRows = statement.executeUpdate();

        return affectedRows > 0;
    }


    /**
     * changing user password
     *
     * @param username user username
     * @param password new user proposed password
     * @return true when password is updated, false when password not updated
     * @throws SQLException throws sql exception for any error
     * @author Ntwari Clarance Liberiste
     */
    public boolean changePasswordByUsername(String username, String password) throws SQLException{
        Connection connection = PostegresConfig.getConnection();
        String query = "UPDATE users SET pass_word = ? WHERE username = ?;";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1,password);
        statement.setString(2,username);

        int affectedRows = statement.executeUpdate();

        return affectedRows > 0;
    }


}


