package com.sail99.spring_prac01.controller;

import com.sail99.spring_prac01.security.UserDetailsImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Secured("ROLE_USER")
    @GetMapping("/gogo")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("action", "true");
        return "index";
    }

    @GetMapping("/")
    public String test(){
        return "index";
    }

    @GetMapping("admin")
    public String admin(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("admin",true);
        return  "index";

    }
}
