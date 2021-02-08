package server.repositories;


import server.config.Config;
import server.models.PasswordResets;
import server.models.User;
import server.models.enums.PasswordResetsStatusesEnum;
import utils.Mailing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * passwords resets repository
 *
 * @author Ntwari Clarance Liberiste
 */
public class PasswordResetsRepository {

    /**
     * creating password reset record and sending email
     *
     * @param pr Password Reset object
     * @return number of created rows
     * @throws SQLException throws an sql exception
     * @author Ntwari Clarance Liberiste
     */
    public static long create(PasswordResets pr) throws SQLException{

        try{

            Connection connection = Config.getConnection();
            Statement statement = connection.createStatement();

            String query = String.format("INSERT INTO user_password_resets (email, otp, expiration_date, status, created_at, update_at)" +
                    " VALUES ('%s', '%s', '%s', '%s', '%s', '%s'); ", pr.getEmail(), pr.getOtp(), pr.getExpiration_date(), pr.getStatus(), pr.getCreated_at(), pr.getUpdated_at());

            long createdPasswordReset = statement.executeLargeUpdate(query);

            statement.close();
            connection.close();

            //TODO send email on password reset
            if(createdPasswordReset > 0){
                String mailSubject = "Chat System Password Reset";
                String mailContent = "You have requested password reset on our servers" +
                        "so use " + pr.getOtp() + " to reset";

                Mailing mail = new Mailing("ntwaricliberi@gmail.com",pr.getEmail(),mailSubject,mailContent);

                mail.sendMailText("text");
            }

            return createdPasswordReset;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return 0;
    }

    /**
     * get all password reset values as recorded
     *
     * @return List, of type PasswordResets
     * @throws SQLException sql exception
     * @author Ntwari Clarance Liberi
     */
    public static List<PasswordResets> getAllPasswordResets() throws SQLException{
        try{
            Connection connection = Config.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM user_password_resets";
            ResultSet rs = statement.executeQuery(query);

            System.out.println("Reading passwordResets ....");
            List<PasswordResets> passwordResets= new ArrayList<PasswordResets>();
            while(rs.next()){
                passwordResets.add(new PasswordResets(
                        rs.getString("email"),
                        rs.getInt("otp"),
                        rs.getString("expiration_date"),
                        PasswordResetsStatusesEnum.valueOf(rs.getString("status")))
                );
            }
            return passwordResets;

        }catch (Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);

        }

        return null;
    }

    public static PasswordResets getPasswordResetsById(int passwordResetsId) throws SQLException{
        try{
            Connection connection = Config.getConnection();
            Statement statement =  connection.createStatement();


            String query = String.format("SELECT * FROM user_password_resets  WHERE id = '%d' ",passwordResetsId);
            ResultSet rs = statement.executeQuery(query);

            System.out.println("Reading password reset ....");
            if(rs.next()){
                System.out.println("Password Reset Found");

                return  new PasswordResets(
                        rs.getString("email"),
                        rs.getInt("otp"),
                        rs.getString("expiration_date"),
                        PasswordResetsStatusesEnum.valueOf(rs.getString("status")));

            }
            else{
                System.out.println("Password Reset not found");
            }
        }
        catch ( Exception e ) {

            System.err.println( e.getClass().getName()+": "+ e.getMessage() );

            System.exit(0);

        }
        return null;
    }


}
