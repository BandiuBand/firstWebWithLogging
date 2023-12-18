package com.bandiu.fileBr.model;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class LoginController {
    @GetMapping("/login")
    public String showLoginForm(Model model){
        return "login";
    }


}
