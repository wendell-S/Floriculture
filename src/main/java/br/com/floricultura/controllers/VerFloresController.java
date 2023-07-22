package br.com.floricultura.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/floricultura")
public class VerFloresController {
    @RequestMapping("/ver-flores")
    public String showPaginaVerFlores() {
        return "flowerScreen";
    }
}
