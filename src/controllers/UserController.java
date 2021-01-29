package controllers;

import models.File;
import models.User;
import services.UserService;

import java.sql.SQLException;

public class UserController {
    private final UserService userService = new UserService();
    public User saveUser(User user) throws SQLException {
        return this.userService.saveUser(user);
    }
}

