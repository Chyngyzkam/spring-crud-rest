package com.peaksost.springcrudrest.controller;

import com.peaksost.springcrudrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api")
public class ControllerUser {


    private final UserService userService;
    @Autowired
    public ControllerUser(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String admin(){
        return "index1";
    }

    @GetMapping("/user")
    public String User(){
        return "user";
    }
}
