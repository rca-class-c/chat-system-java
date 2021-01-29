package services;

import models.User;
import models.AuthInput;
import repositories.UserRepository;

import java.sql.SQLException;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    public User saveUser(User user) throws SQLException {
        return userRepository.save(user);
    }
    public User loginUser(AuthInput input) throws SQLException{
        return userRepository.login(input);
    }
}
