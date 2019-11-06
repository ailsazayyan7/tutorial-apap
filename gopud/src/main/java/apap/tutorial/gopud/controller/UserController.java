package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.model.UserModel;
import apap.tutorial.gopud.repository.UserDB;
import apap.tutorial.gopud.service.RoleService;
import apap.tutorial.gopud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserModel user, Model model){
        userService.addUser(user);
        model.addAttribute("listRole", roleService.findAll());
        return "home";
    }

    @RequestMapping(value = "/update-password", method = RequestMethod.GET)
    private String updatePasswordForm(Model model){
        model.addAttribute("text", "");
        return "form-update-password";
    }

    @RequestMapping(value = "/update-password", method = RequestMethod.POST)
    private String updatePasswordSubmit(@RequestParam String oldPass, String newPass, String confirmPass, String username, Model model){
        UserModel user = userService.getUserByUsername(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(oldPass, user.getPassword())){
            if(newPass.equals(confirmPass)){
                userService.updatePassword(user, newPass);
                model.addAttribute("status","Password berhasil diubah");
            }
            else{
                model.addAttribute("status", "Password baru tidak sesuai");
            }
        }
        else{
            model.addAttribute("status", "Password lama salah");
        }
        return "form-update-password";
    }

}
