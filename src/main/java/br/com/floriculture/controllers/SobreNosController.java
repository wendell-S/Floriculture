package br.com.floriculture.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/floricultura")
public class SobreNosController {

    @RequestMapping("/sobre-Nos")
    public String showPaginaAboutUs() {
        return "aboutUs";
    }

}
