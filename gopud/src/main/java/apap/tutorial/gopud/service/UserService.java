package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.UserModel;
import org.springframework.security.core.userdetails.User;

public interface UserService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
    public void updatePassword(UserModel user, String password);
    UserModel getUserByUsername(String username);
}
