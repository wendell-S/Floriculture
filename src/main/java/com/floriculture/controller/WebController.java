package com.floriculture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    
    @RequestMapping("/mensagem")
    public String exibirPagina() {
        return "html/PrincipalTela.html";
    }

    @RequestMapping("/ver-flores")
    public String exibirPaginaVerFlores() {
        return "html/TelaFlores.html";
    }

    @RequestMapping("/mandar-flor")
    public String exibirPaginaMandarFlor() {
        return "html/TelaMensagem.html";
    }
}