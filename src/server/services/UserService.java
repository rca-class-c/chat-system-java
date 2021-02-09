package server.services;

import server.models.User;
import server.models.AuthInput;
import server.repositories.UserRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * User Services provider
 * @author: Shallon Kobusinge
 */

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    public User saveUser(User user) throws SQLException {
        return userRepository.save(user);
    }
    public User loginUser(AuthInput input) throws SQLException{
        return userRepository.login(input);
    }
    public List<User> getAllUsers() throws SQLException{
        return userRepository.getAllUsers();
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
    public int deactiveUser(int userId) throws SQLException{
        return userRepository.deactivateUser(userId);
    }
}
