package com.floriculture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/floricultura")
public class WebController {
    


    @RequestMapping("/ver-flores")
    public String showPaginaVerFlores() {
        return "html/TelaFlores.html";
    }

    @RequestMapping("/mandar-flor")
    public String showPaginaMandarFlor() {
        return "html/TelaMensagem.html";
    }



}