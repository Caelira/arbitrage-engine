package com.caelira.arbitrageengine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class linkController {

    @GetMapping("/")
    public String showIndex(){

        return "index";
    }

}
