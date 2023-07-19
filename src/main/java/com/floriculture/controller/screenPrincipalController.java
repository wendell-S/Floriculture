package com.floriculture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/floricultura")
public class screenPrincipalController {
    @GetMapping("/mensagem")
    public String showPagina() {
        return "html/screenPrincipal.html";
    }
}
