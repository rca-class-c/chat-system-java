package server.repositories;
import server.config.PostegresConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sendInvitationRepositories {
    public int searchForAdmin(String email) throws SQLException {
        int id=0;
        Connection connection= PostegresConfig.getConnection();
        final  String query="SELECT user_id,email from users WHERE email=?";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,email);
        ResultSet resultSet= preparedStatement.executeQuery();
        while (resultSet.next()){
            String email1=resultSet.getString("email");
             id = resultSet.getInt("user_id");

        }
        connection.close();
        return id;
    }
   public  void InsertAnInvitation(int id,String email,int verificationCode) throws SQLException {
       Connection conn= PostegresConfig.getConnection();
       final  String sql="INSERT INTO sent_invitations(admin_id,sent_to,verificationcode) VALUES(?,?,?)";
       PreparedStatement prepared=conn.prepareStatement(sql);
           prepared.setInt(1,1);
           prepared.setString(2,email);
           prepared.setInt(3,verificationCode);
           int row= prepared.executeUpdate();

       conn.close();
   }
   public int SearchForInvited(int verificationCode) throws SQLException {
       Connection conn= PostegresConfig.getConnection();
       int id=0;
       final String query="Select sent_id from sent_invitations where verificationcode=?";
       PreparedStatement prepared=conn.prepareStatement(query);
       prepared.setInt(1,verificationCode);
       ResultSet resultSet= prepared.executeQuery();
       while(resultSet.next()){
           id=resultSet.getInt("sent_id");
       }
       return id;
   }
   public int AcceptingInvitation(int verificationCode) throws SQLException {
       Connection conn= PostegresConfig.getConnection();
       int id=SearchForInvited(verificationCode);
       final String sql="UPDATE sent_invitations SET status='ACTIVATED' , verificationcode=0 where sent_id=? ";
       PreparedStatement prepared=conn.prepareStatement(sql);
       prepared.setInt(1,id);
       int row = prepared.executeUpdate();
       prepared.close();
       conn.close();
       if (row!=-1) {
           return 1;
       }
       else {
           return 0;
       }
    }
}
