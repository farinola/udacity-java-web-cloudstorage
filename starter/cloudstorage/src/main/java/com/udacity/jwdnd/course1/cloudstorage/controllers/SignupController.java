package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signupView() {
        return "signup";
    }

    @PostMapping
    public String signupUser(@ModelAttribute User user, Model model) {
        String error = null;

        if (!userService.isUsernameAvailable(user.getUsername())) {
            error = "The username is taken!";
        }

        if (error == null) {
            int userId = userService.createUser(user);
            if (userId < 0) {
                error = "There was an error signing you up. Please try again.";
            }
        }

        if (error == null) {
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", error);
        }
        return "signup";
    }
}
