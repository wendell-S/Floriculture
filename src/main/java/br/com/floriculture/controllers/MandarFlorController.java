package br.com.floriculture.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/floricultura")
public class MandarFlorController {
    @RequestMapping("/mandar-flor")
    public String showPaginaMandarFlor() {
        return "messageScreen";
    }

}
