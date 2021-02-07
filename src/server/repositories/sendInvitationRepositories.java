package server.repositories;
import  server.config.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sendInvitationRepositories {
    public int searchForAdmin(String email) throws SQLException {
        int id=0;
        Connection connection=Config.getConnection();
        final  String query="SELECT user_id,email from users WHERE email=?";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,email);
        ResultSet resultSet= preparedStatement.executeQuery();
        while (resultSet.next()){
            String email1=resultSet.getString("email");
             id = resultSet.getInt("user_id");
            if (email1.equals(email)){
                System.out.println("Admin found");
            }
            else {
                System.out.println("Admin Not Found");
            }
        }
        connection.close();
        return id;
    }
   public  void InsertAnInvitation(int id,String email,int verificationCode) throws SQLException {
       Connection conn=Config.getConnection();
       final  String sql="INSERT INTO sent_invitations(admin_id,sent_to,verificationcode) VALUES(?,?,?)";
       PreparedStatement prepared=conn.prepareStatement(sql);
           prepared.setInt(1,id);
           prepared.setString(2,email);
           prepared.setInt(3,verificationCode);
           int row= prepared.executeUpdate();
           if (row!=-1){
               System.out.println("Done");
           }
           else {
               System.out.println("Failed");
           }
       conn.close();
   }
}
