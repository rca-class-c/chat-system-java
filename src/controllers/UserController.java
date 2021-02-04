package controllers;

import models.AuthInput;
import models.File;
import models.User;
import services.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserController {
    private final UserService userService = new UserService();
    public User saveUser(User user) throws SQLException {
        return this.userService.saveUser(user);
    }
    public User loginUser(AuthInput input) throws SQLException {
        return this.userService.loginUser(input);
    }
    public List<User> getAllUser() throws SQLException {
        return this.userService.getAllUser();
    }
    public User getUserById(int userId) throws SQLException{
        return this.userService.getUserById(userId);
    }
    public int updateUser(User user,int userId) throws SQLException{
        return this.userService.updateUser(user,userId);
    }
    public int deleteUser(int userId) throws SQLException{
        return this.userService.deleteUser(userId);
    }
    public int deactiveUser(int userId) throws SQLException{
        return this.userService.deactiveUser(userId);
    }


}

