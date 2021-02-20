package server.services;


import server.config.PostegresConfig;
import server.models.PasswordResets;
import server.models.enums.PasswordResetsStatusesEnum;
import server.repositories.PasswordResetsRepository;
import server.repositories.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Password resets service
 *
 * @since 1.0
 * @author Ntwari Clarance Liberiste
 */
public class PasswordResetService {

    private final PasswordResetsRepository passwordResetsRepository = new PasswordResetsRepository();


    /**
     * creating password reset record and sending email
     *
     * @param pr Password Reset object
     * @return number of created rows, usually 1 as it will create only one record at time
     * @throws SQLException throws an sql exception
     * @author Ntwari Clarance Liberiste
     */
    public PasswordResets create(PasswordResets pr) throws SQLException{
        return passwordResetsRepository.create(pr);
    }

    /**
     * get all password resets
     *
     * @return List, of type PasswordResets
     * @throws SQLException sql exception
     * @author Ntwari Clarance Liberi
     */
    public List<PasswordResets> getAllPasswordResets() throws SQLException{
        return passwordResetsRepository.getAllPasswordResets();
    }

    /**
     * get all password resets filtered by status
     * @param status status to filter password resets
     * @return List of type PasswordRests, list contains all queried result
     * @throws SQLException throws sql exception
     * @author Ntwari Clarance Liberiste
     */
    public List<PasswordResets> getAllPasswordResets(PasswordResetsStatusesEnum status) throws SQLException{
        return passwordResetsRepository.getAllPasswordResets(status);
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
        return passwordResetsRepository.getPasswordResetsById(passwordResetsId);
    }

    /**
     * get password reset by email
     * @param passwordResetsEmail email to be used while searching password reset email
     * @return PasswordResets object
     * @throws SQLException throws sql exception
     * @author Ntwari Clarance Liberiste
     */
    public PasswordResets getPasswordResetsByEmail(String passwordResetsEmail) throws SQLException{
        return passwordResetsRepository.getPasswordResetsByEmail(passwordResetsEmail);
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
        return passwordResetsRepository.getPasswordResetsByEmail(passwordResetsEmail,status);
    }

    /**
     * get all password resets record by email
     * @param passwordResetsEmail email to use while searching
     * @return List<PasswordResets>
     * @throws SQLException throws sql exception
     * @author Ntwari Clarance Liberiste
     */
    public List<PasswordResets> getAllPasswordResetsByEmail(String passwordResetsEmail) throws SQLException{
        return passwordResetsRepository.getAllPasswordResetsByEmail(passwordResetsEmail);
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
        return passwordResetsRepository.getAllPasswordResetsByEmail(passwordResetsEmail,status);
    }

    /**
     * changing password reset record status
     *
     * @param email email of the password reset record
     * @param otp OTP code of the password reset record
     * @param status new status of the password reset record
     * @return true when update, false when not updated
     * @throws SQLException throws an sql exception
     * @author Ntwari Clarance Liberiste
     */
    public boolean changePasswordResetStatus(String email,int otp, PasswordResetsStatusesEnum status) throws SQLException{
        return passwordResetsRepository.changePasswordResetStatus(email,otp,status);
    }


    /**
     * resetting password
     * @param userEmail user email to reset password reset for
     * @param otp OTP code sent to email, corresponds to user
     * @param newPassword new password
     * @return true when user password updated, false when not updated
     * @throws Exception sql exception is thrown
     * @author Ntwari Clarance Liberiste
     */
    public boolean resetPassword(String userEmail,int otp,String newPassword) throws Exception{
        return passwordResetsRepository.resetPassword(userEmail,otp,newPassword);
    }

}
