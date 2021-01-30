package repositories;

import config.Config;
import models.AuthInput;
import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
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
    public User login(AuthInput input) throws SQLException{
        try{
            Connection connection = Config.getConnection();
            Statement statement =  connection.createStatement();

            String query = String.format("SELECT * FROM users where username  = '%s' and  pass_word = '%s';",input.getUsername(),input.getPassword());
            ResultSet rs = statement.executeQuery(query);
            System.out.println("Reading users ....");
            if(rs.getRow()<=0){
                System.out.println("User Found!");
            while(rs.next()){
                System.out.println("Fname: "+rs.getString("first_name")+"\nLname: "+rs.getString("last_name")+"\nEmail: "+rs.getString("email"));
            }
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
}
