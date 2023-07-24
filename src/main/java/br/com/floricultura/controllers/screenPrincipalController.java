package br.com.floricultura.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/floricultura")

public class screenPrincipalController {
    @GetMapping("/mensagem")
    public String screenPrincipal(Authentication authentication, Model model) {
        String username = authentication.getName(); // Obtém o nome do usuário autenticado
        model.addAttribute("username", username);
        return "screenPrincipal";
    }
}
