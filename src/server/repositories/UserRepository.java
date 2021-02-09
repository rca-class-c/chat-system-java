package server.repositories;

import server.config.Config;
import server.models.AuthInput;
import server.models.User;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class UserRepository {
    public User save(User user) throws SQLException {
        int i= 0;
        try {
            Connection connection = Config.getConnection();
            Statement statement = connection.createStatement();

            String query = String.format("INSERT INTO users(first_name, last_name, username, email, gender, pass_word,dob,status,categoryid) VALUES (" +
                    "'%s','%s','%s','%s','%s','%s','%s','%s',%d);", user.getFname(), user.getLname(), user.getUsername(), user.getEmail(), user.getGender(), user.getPassword(),user.getDob(),user.getStatus(),user.getCategoryID());


            i = statement.executeUpdate(query);
            System.out.println("Rows inserted: "+i);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(i > 0) {
            return user;
        }
        return null;
    }
    public User login(AuthInput input) throws SQLException{
        try{
            Connection connection = Config.getConnection();
            Statement statement =  connection.createStatement();

            String query = String.format("SELECT * FROM users where username  = '%s' and  pass_word = '%s';",input.getUsername(),input.getPassword());
            ResultSet rs = statement.executeQuery(query);
            System.out.println("Reading users ....");
            if(rs.next()){
                System.out.println("User Found!");
                User returnUser =  new User(rs.getInt("user_id"),rs.getString("first_name"),rs.getString("last_name"),
                        rs.getString("pass_word"),rs.getString("email"),rs.getString("dob"),
                        rs.getString("username"),rs.getString("gender"),rs.getInt("categoryid"),
                        rs.getString("status"),rs.getString("created_at"),rs.getString("updated_at"));
                System.out.println("Fname: "+rs.getString("first_name")+"\nLname: "+rs.getString("last_name")+"\nEmail: "+rs.getString("email"));
//            while(rs.next()){
//                System.out.println("Fname: "+rs.getString("first_name")+"\nLname: "+rs.getString("last_name")+"\nEmail: "+rs.getString("email"));
//            }
                return returnUser;
            }
            else{
                System.out.println("No users found");
            }
        }
        catch ( Exception e ) {

            System.err.println( e.getClass().getName()+": "+ e.getMessage() );

            System.exit(0);

         }
        return null;
    }


    public List<User> getAllUsers() throws SQLException{
        try{
            Connection connection = Config.getConnection();
            Statement statement =  connection.createStatement();

            String query = String.format("SELECT * FROM users;");
            ResultSet rs = statement.executeQuery(query);
            System.out.println("Reading users ....");
            List<User> users=new ArrayList<User>();
            while(rs.next()){
                users.add(new User(rs.getString("first_name"),rs.getString("last_name"),
                        rs.getString("pass_word"),rs.getString("email"),rs.getString("dob"),
                        rs.getString("username"),rs.getString("gender"),rs.getInt("categoryid"),
                        rs.getString("status")));
            }
            return users;
        }
        catch ( Exception e ) {

            System.err.println( e.getClass().getName()+": "+ e.getMessage() );

            System.exit(0);

        }
        return null;
    }
    public List<User> getUserSearchList(String search) throws SQLException {
        try{
            Connection connection = Config.getConnection();
            Statement statement =  connection.createStatement();

            String query = String.format("SELECT * FROM users where first_name = '%s' or last_name = '%s' username = '%s' ORDER BY user_id ASC;",search,search,search);
            ResultSet rs = statement.executeQuery(query);
            System.out.println("Reading users ....");
            List<User> users=new ArrayList<User>();
            while(rs.next()){
                users.add(new User(rs.getInt("user_id"),rs.getString("first_name"),rs.getString("last_name"),
                        rs.getString("pass_word"),rs.getString("email"),rs.getString("dob"),
                        rs.getString("username"),rs.getString("gender"),rs.getInt("categoryid"),
                        rs.getString("status"),rs.getString("created_at"),rs.getString("updated_at")));
            }
            System.out.println(users.size());
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
            Connection connection = Config.getConnection();
            Statement statement =  connection.createStatement();

            String query = String.format("SELECT * FROM users where user_id != '%d' ORDER BY user_id ASC;",id);
            ResultSet rs = statement.executeQuery(query);
            System.out.println("Reading users ....");
            List<User> users=new ArrayList<User>();
            while(rs.next()){
                users.add(new User(rs.getInt("user_id"),rs.getString("first_name"),rs.getString("last_name"),
                        rs.getString("pass_word"),rs.getString("email"),rs.getString("dob"),
                        rs.getString("username"),rs.getString("gender"),rs.getInt("categoryid"),
                        rs.getString("status"),rs.getString("created_at"),rs.getString("updated_at")));
            }
            System.out.println(users.size());
            return users;
        }
        catch ( Exception e ) {

            System.err.println( e.getClass().getName()+": "+ e.getMessage() );

            System.exit(0);

        }
        return null;
    }


    public User getUserById(int userID) throws SQLException{
        try{
            Connection connection = Config.getConnection();


            String query = String.format("SELECT * FROM users  WHERE user_id = '%d' ;",userID);
            Statement statement =  connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            System.out.println("Reading users ....");
            if(rs.next()){
                System.out.println("User Found!");
                User returnUser =  new User(rs.getString("first_name"),rs.getString("last_name"),
                        rs.getString("pass_word"),rs.getString("email"),rs.getString("dob"),
                        rs.getString("username"),rs.getString("gender"),rs.getInt("categoryid"),
                        rs.getString("status"));
      //System.out.println(" User By Id"+rs.getString("last_name"));
                return returnUser;
            }
            else{
                System.out.println("No users found");
            }
        }
        catch ( Exception e ) {

            System.err.println( e.getClass().getName()+": "+ e.getMessage() );

            System.exit(0);

        }
        return null;
    }


    public User updateUser(User user,int userId) throws SQLException{
        int affectedRows = 0;

            Connection connection = Config.getConnection();
            String query = String.format("UPDATE users SET first_name = ?,last_name = ?," +
                    "username=?,email=?,gender=?,pass_word=?,dob=?, categoryid = ?  WHERE user_id = ? ;");
            PreparedStatement statement =  connection.prepareStatement(query);
            statement.setString(1,user.getFname());
            statement.setString(2,user.getLname());
            statement.setString(3,user.getUsername());
            statement.setString(4, user.getEmail());
            statement.setString(5,user.getGender());
            statement.setString(6,user.getPassword());
            statement.setString(7,user.getDob());
            statement.setInt(8,user.getCategoryID());
            statement.setInt(user.getUserID(),userId);
            affectedRows = statement.executeUpdate();
            if(affectedRows > 0) {
                return user;
            }
            return null;
    }

    public int deleteUser(int userId) throws SQLException{

          int affectedRows = 0;

          Connection connection = Config.getConnection();
          String query = String.format("DELETE FROM users WHERE user_id = ? ;");
          PreparedStatement statement = connection.prepareStatement(query);
          statement.setInt(1, userId);
          if (affectedRows > 0) {
              System.out.println("  User deleted successfully   ");
          }
          return affectedRows;
    }

    public int deactivateUser(int userId) throws SQLException{
        int affectedRows = 0;
        Connection connection = Config.getConnection();
        String query = String.format("UPDATE users SET status = 'INACTIVE' WHERE user_id = '%d';",userId);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,userId);
        if(affectedRows>0){
            System.out.println(" User Deactived successfully");
        }
        return affectedRows;
    }

}


