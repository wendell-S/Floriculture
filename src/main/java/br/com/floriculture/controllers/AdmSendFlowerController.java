package br.com.floriculture.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/floricultura")
public class AdmSendFlowerController {
    @RequestMapping("/admin")
    public String showPagCreateFlower() {
        return "adminSendFlower";
    }
}
