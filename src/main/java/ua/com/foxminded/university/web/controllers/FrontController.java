package ua.com.foxminded.university.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class FrontController {
    
    @GetMapping
    public String root() {
        return "index";
    }
    
    @GetMapping("/cruds")
    public ModelAndView gotoCruds() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cruds");
        return mv;
    }
}
