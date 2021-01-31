package controllers;

import models.AuthInput;
import models.File;
import models.User;
import services.UserService;

import java.sql.SQLException;

public class UserController {
    private final UserService userService = new UserService();
    public User saveUser(User user) throws SQLException {
        return this.userService.saveUser(user);
    }
    public User loginUser(AuthInput input) throws SQLException {
        return this.userService.loginUser(input);
    }
}

