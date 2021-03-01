package server.services;

import server.models.User;
import server.models.AuthInput;
import server.repositories.UserRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * User Services provider
 * @author Shallon Kobusinge
 */

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    public User saveUser(User user) throws SQLException {
        return userRepository.save(user);
    }
    public User loginUser(AuthInput input) throws SQLException{
        return userRepository.login(input);
    }
    public List<User> getAllInactiveUsers() throws SQLException{
        return userRepository.getAllInactiveUsers();
    }
    public List<User> getAllOtherUsers(int id) throws SQLException{
        return userRepository.getAllOtherUsers(id);
    }
    public User getUserById(int userId) throws SQLException{
        return userRepository.getUserById(userId);
    }
    public List<User> SearchUsers(String search_data) throws SQLException {
        return userRepository.getUserSearchList(search_data);
    }
    public User updateUser(User user,int userId) throws SQLException{
        return userRepository.updateUser(user,userId);
    }
    public int deleteUser(int userId) throws SQLException{
        return userRepository.deleteUser(userId);
    }

    /**
     * changing user password
     *
     * @param userId user id
     * @param password user new proposed password
     * @return true when password is updated, false when password not updated
     * @throws SQLException throws sql exception for any error
     * @author Ntwari Clarance Liberiste
     */
    public boolean changePasswordById(int userId, String password) throws SQLException{
        return userRepository.changePasswordById(userId, password);
    }

    /**
     *  changing user password
     *
     * @param email user email
     * @param password new user proposed password
     * @return true when password is updated, false when password not updated
     * @throws SQLException throws sql exception for any error
     * @author Ntwari Clarance Liberiste
     */
    public boolean changePasswordByEmail(String email,String password) throws SQLException{
        return userRepository.changePasswordByEmail(email,password);
    }

    /**
     * changing user password
     *
     * @param username user username
     * @param password new user proposed password
     * @return true when password is updated, false when password not updated
     * @throws SQLException throws sql exception for any error
     * @author Ntwari Clarance Liberiste
     */
    public boolean changePasswordByUsername(String username, String password) throws SQLException{
        return userRepository.changePasswordByUsername(username, password);
    }
}
