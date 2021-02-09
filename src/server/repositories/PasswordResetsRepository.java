package server.repositories;


import server.config.Config;
import server.models.PasswordResets;
import server.models.enums.PasswordResetsStatusesEnum;
import utils.Mailing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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



}
