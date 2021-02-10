package server.repositories;


import server.config.Config;
import server.models.PasswordResets;
import server.models.enums.PasswordResetsStatusesEnum;
import utils.Mailing;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/**
 * passwords resets repository
 *
 * @author Ntwari Clarance Liberiste
 * @since 1.0
 */
public class PasswordResetsRepository {

    /**
     * creating password reset record and sending email
     *
     * @param pr Password Reset object
     * @return number of created rows, usually 1 as it will create only one record at time
     * @throws SQLException throws an sql exception
     * @author Ntwari Clarance Liberiste
     */
    public long create(PasswordResets pr) throws SQLException{

        try{

            Connection connection = Config.getConnection();
            Statement statement = connection.createStatement();

            String query = String.format("INSERT INTO user_password_resets (email, otp, expiration_date, status, created_at, update_at)" +
                    " VALUES ('%s', '%s', '%s', '%s', '%s', '%s'); ", pr.getEmail(), pr.getOtp(), pr.getExpiration_date(), pr.getStatus(), pr.getCreated_at(), pr.getUpdated_at());

            long createdPasswordReset = statement.executeLargeUpdate(query);


            statement.close();
            connection.close();

//            !c@L#2a0s2s1&C%6

            //TODO send email on password reset
            if(createdPasswordReset > 0){
                String mailSubject = "Chat System Password Reset";
                String mailContent = "You have requested password reset on our servers" +
                        "so use " + pr.getOtp() + " to reset";


                try (FileInputStream f = new FileInputStream("src/server/server.properties")) {

                    // load the properties file
                    Properties pros = new Properties();
                    pros.load(f);

                    // assign migrations.sql parameters
                    String MailerEmail = pros.getProperty("MailerEmail");
                    String MailerPassword = pros.getProperty("MailerPassword");

                    System.out.println("Sending otp to " + pr.getEmail() + "...");
                    Mailing mail = new Mailing(MailerEmail,MailerPassword,pr.getEmail(),mailSubject,mailContent);

                    mail.send();

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }


            }

            return createdPasswordReset;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }


        return 0;
    }

    /**
     * get all password resets
     *
     * @return List, of type PasswordResets
     * @throws SQLException sql exception
     * @author Ntwari Clarance Liberi
     */
    public List<PasswordResets> getAllPasswordResets() throws SQLException{
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


    /**
     * get all password resets filtered by status
     * @param status status to filter password resets
     * @return List of type PasswordRests, list contains all queried result
     * @throws SQLException throws sql exception
     * @author Ntwari Clarance Liberiste
     */
    public List<PasswordResets> getAllPasswordResets(PasswordResetsStatusesEnum status) throws SQLException{
        try{
            Connection connection = Config.getConnection();
            Statement statement = connection.createStatement();

            String query = String.format("SELECT * FROM user_password_resets WHERE status = '%s'",status);
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

                System.out.println("email: " + rs.getString("email") + "status: " + rs.getString("status"));

            }
            return passwordResets;

        }catch (Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);

        }

        return null;
    }


    /**
     * get password resets by id
     *
     * @param passwordResetsId id to be used to search password resets
     * @return PasswordResets object
     * @throws SQLException throws sql exception
     * @author Ntwari Clarance Liberiste
     */
    public PasswordResets getPasswordResetsById(int passwordResetsId) throws SQLException{
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


    /**
     * get password reset by email
     * @param passwordResetsEmail email to be used while searching password reset email
     * @return PasswordResets object
     * @throws SQLException throws sql exception
     * @author Ntwari Clarance Liberiste
     */
    public PasswordResets getPasswordResetsByEmail(String passwordResetsEmail) throws SQLException{
        try{
            Connection connection = Config.getConnection();
            Statement statement =  connection.createStatement();


            String query = String.format("SELECT * FROM user_password_resets  WHERE email = '%s' ",passwordResetsEmail);
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

    /**
     * get new password resets by email and status
     *
     * @param passwordResetsEmail email to be used while searching password reset
     * @param status status to be used when searching password reset
     * @return PasswordResets object
     * @throws SQLException throws sql Exception
     * @author Ntwari Clarance Liberiste
     */
    public PasswordResets getPasswordResetsByEmail(String passwordResetsEmail,PasswordResetsStatusesEnum status) throws SQLException{
        try{
            Connection connection = Config.getConnection();
            Statement statement =  connection.createStatement();


            String query = String.format("SELECT * FROM user_password_resets  WHERE email = '%s' AND status = '%s ORDER BY ID DESC LIMIT 1",passwordResetsEmail,status);
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


    /**
     * get all password resets record by email
     * @param passwordResetsEmail email to use while searching
     * @return List<PasswordResets>
     * @throws SQLException throws sql exception
     * @author Ntwari Clarance Liberiste
     */
    public List<PasswordResets> getAllPasswordResetsByEmail(String passwordResetsEmail) throws SQLException{
        try{
            Connection connection = Config.getConnection();
            Statement statement =  connection.createStatement();


            String query = String.format("SELECT * FROM user_password_resets  WHERE email = '%s' ",passwordResetsEmail);
            ResultSet rs = statement.executeQuery(query);

            System.out.println("Reading password reset ....");
            List<PasswordResets> passwordResets = new ArrayList<PasswordResets>();

            while(rs.next()){
                passwordResets.add(
                        new PasswordResets(
                                rs.getString("email"),
                                rs.getInt("otp"),
                                rs.getString("expiration_date"),
                                PasswordResetsStatusesEnum.valueOf(rs.getString("status")))

                );
            }

            if(passwordResets.size() > 0)
                System.out.println("All Password Resets Found");
            else
                System.out.println("No Password resets found with " + passwordResetsEmail);


            return passwordResets;

        }
        catch ( Exception e ) {

            System.err.println( e.getClass().getName()+": "+ e.getMessage() );

            System.exit(0);

        }
        return null;
    }


    /**
     * get all password resets records based on email and status
     *
     * @param passwordResetsEmail email to search
     * @param status status to use while searching
     * @return List<PasswordResets>
     * @throws SQLException throws an sql exception
     * @author Ntwari Clarance Liberiste
     */
    public List<PasswordResets> getAllPasswordResetsByEmail(String passwordResetsEmail,PasswordResetsStatusesEnum status) throws SQLException{
        try{
            Connection connection = Config.getConnection();
            Statement statement =  connection.createStatement();


            String query = String.format("SELECT * FROM user_password_resets  WHERE email = '%s' AND status = '%s';",passwordResetsEmail,status);
            ResultSet rs = statement.executeQuery(query);

            System.out.println("Reading password reset ....");
            List<PasswordResets> passwordResets  = new ArrayList<PasswordResets>();


            while(rs.next()){
                  passwordResets.add(
                          new PasswordResets(
                                  rs.getString("email"),
                                  rs.getInt("otp"),
                                  rs.getString("expiration_date"),
                                  PasswordResetsStatusesEnum.valueOf(rs.getString("status")))

                  );
            }
            if(passwordResets.size() > 0)
                System.out.println("All Password Resets Found");
            else
                System.out.println("No Password resets found with " + passwordResetsEmail + " and status : " + status);

            return passwordResets;
        }
        catch ( Exception e ) {

            System.out.println(" No password reset record with " + passwordResetsEmail);
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );

            System.exit(0);

        }
        return null;
    }

    public boolean changePasswordResetStatus(String email,int otp, PasswordResetsStatusesEnum status) throws SQLException{
        Connection connection = Config.getConnection();
        String query = "UPDATE user_password_resets SET status = ?::user_password_resets_statuses WHERE id IN (SELECT id FROM user_password_resets WHERE email = ? AND otp = ? ORDER BY id DESC LIMIT 1);";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1,status.toString());
        statement.setString(2,email);
        statement.setInt(3,otp);

        int statusUpdated = statement.executeUpdate();

        return statusUpdated > 0;

    }

    public boolean isOtpValid(String userEmail, int otp) throws SQLException{
        Connection connection = Config.getConnection();
        String query = "SELECT * FROM user_password_resets WHERE email = ? AND otp = ?  AND status = 'PENDING' ORDER BY created_at DESC LIMIT 1 ;";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1,userEmail);
        statement.setInt(2,otp);

        ResultSet passwordResetRecord = statement.executeQuery();

        passwordResetRecord.next();

        return passwordResetRecord.getRow() > 0;
    }

    public boolean isOtpExpired(String userEmail, int otp) throws SQLException{
        Connection connection = Config.getConnection();
        String query = "SELECT * FROM user_password_resets WHERE email = ? AND otp = ? ORDER BY created_at DESC LIMIT 1 ";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1,userEmail);
        statement.setInt(2,otp);
        //TODO update status to expired when OTP is expired

        ResultSet passwordResetRecord = statement.executeQuery();

        if(passwordResetRecord.next()){
            Timestamp otpExpirationDate = passwordResetRecord.getTimestamp("expiration_date");
            Timestamp currentNowTime = new Timestamp(System.currentTimeMillis());

            int timeComparison = currentNowTime.compareTo(otpExpirationDate);

            //when OTP have expired set status to EXPIRED
            if(timeComparison > 0)
                this.changePasswordResetStatus(userEmail,otp,PasswordResetsStatusesEnum.EXPIRED);

            return timeComparison > 0;

        } else{
            System.out.println("Not found");
        }

        return true;
    }

    public boolean resetPassword(String userEmail,int otp,String newPassword) throws Exception{

        if(!this.isOtpValid(userEmail,otp)){
            System.out.println("OTP not valid");
            return false;
        }

        if(this.isOtpExpired(userEmail,otp)){
            System.out.println("OTP have expired");
        }

        //automatically change password reset status to USED
        this.changePasswordResetStatus(userEmail,otp, PasswordResetsStatusesEnum.USED);

        UserRepository user = new UserRepository();

        return user.changePasswordByEmail(userEmail,newPassword);
    }

}
