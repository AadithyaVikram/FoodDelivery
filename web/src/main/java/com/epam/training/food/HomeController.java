package com.epam.training.food;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    @GetMapping
    public String  homeApi(){
        return "home";
    }
}
