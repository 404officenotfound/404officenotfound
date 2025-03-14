package com.office.notfound.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping(value = {"/","/main"})
    public String main(ModelAndView mv) {
        mv.setViewName("main/main");

        return "main/main";
    }

}
