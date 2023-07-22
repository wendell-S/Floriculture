package br.com.floricultura.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/floricultura")
public class PerfilController {

    @RequestMapping("/perfil")
    public String showPaginaPerfil() {
        return "profileScreen";
    }



}
