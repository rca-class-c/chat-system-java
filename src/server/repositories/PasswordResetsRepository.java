package server.repositories;


import server.config.Config;
import server.models.PasswordResets;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * passwords resets repository
 *
 * @author Ntwari Clarance Liberiste
 */
public class PasswordResetsRepository {

    public static long create(PasswordResets pr) throws SQLException{

        try{

            Connection connection = Config.getConnection();
            Statement statement = connection.createStatement();

            String query = String.format("INSERT INTO user_password_resets (email, otp, expiration_date, status, created_at, update_at)" +
                    " VALUES ('%s', '%s', '%s', '%s', '%s', '%s'); ", pr.getEmail(), pr.getOtp(), pr.getExpiration_date(), pr.getStatus(), pr.getCreated_at(), pr.getUpdated_at());

            long createdUser = statement.executeLargeUpdate(query);

            statement.close();
            connection.close();

            return createdUser;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return 0;
    }


    public static void main(String[] args) throws Exception{
        PasswordResets passwordResetInstance= new PasswordResets("liberintwari@gmail.com",343394, Instant.now().plus(1, ChronoUnit.DAYS).toString());

        PasswordResetsRepository.create(passwordResetInstance);
    }
}
