package br.com.floricultura.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/floricultura")
public class AboutUs {

    @RequestMapping("/sobre-Nos")
    public String showPaginaAboutUs() {
        return "aboutUs";
    }

}
