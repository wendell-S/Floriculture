package br.com.floricultura.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/floricultura")

public class screenPrincipalController {
    @GetMapping("/mensagem")
    public String showPagina() {
        return "screenPrincipal";
    }
}