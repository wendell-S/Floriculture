package com.floriculture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    
    @RequestMapping("/mensagem")
    public String exibirPagina() {
        return "html/TelaFlores.html";
    }
}