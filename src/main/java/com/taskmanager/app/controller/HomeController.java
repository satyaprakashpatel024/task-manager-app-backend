package com.taskmanager.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class HomeController {
    @GetMapping("home")
    public String getHome(){
        return "<h1>Home</h1></br><h2>Welcome to Home page</h2>";
    }
}
