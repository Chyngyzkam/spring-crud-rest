package com.peaksost.springcrudrest.controller;

import com.peaksost.springcrudrest.entity.User;
import com.peaksost.springcrudrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/api")
public class ControllerUser {

    @Autowired
    UserService userService;
    @GetMapping("/admin")
    public String admin(){
        return "index1";
    }

    @GetMapping("/user")
    public String User(Principal principal, Model model){
        User user =  userService.findByUsername(principal.getName());
        model.addAttribute("user",user);

        return "user";
    }
}
