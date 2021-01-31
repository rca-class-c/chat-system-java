package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.Config;

public class login {
    public static int login( String email,String password) throws SQLException, ClassNotFoundException {
        Connection conn=Config.getConnection();
        int ok=0;

        final  String query="SELECT email,pass_word from users WHERE email=? OR pass_word=?";
        PreparedStatement preparedStatement= conn.prepareStatement(query);
        preparedStatement.setString(1,email);
        preparedStatement.setString(2,password);
        ResultSet resultSet= preparedStatement.executeQuery();
if (resultSet.next()){
    String email1=resultSet.getString("email");
            String  pass1=resultSet.getString("pass_word");
            if(email1.equals(email)&&pass1.equals(password)){
                System.out.println("Hello user: "+email1);
                ok=1;
            }
            else if(!email1.equals(email)){
                System.out.println("Not valid");
            }
                else{
                System.out.println("Wrong credentials");
            }

}

else{
            System.out.println("Not valid");
        }
        conn.close();
        if(ok==1){

            return 1;
        }
        else {
            return 0;
        }
    }
}
