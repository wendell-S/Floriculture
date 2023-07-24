package br.com.floricultura.controllers;

import br.com.floricultura.DTO.FlowerDTO;
import br.com.floricultura.model.Flower;
import br.com.floricultura.services.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/floricultura")
public class VerFloresController {
    private FlowerService flowerService;

    @GetMapping("/ver-flores")
    public String verFlores(Model model) {
        List<Flower> flores = flowerService.listarFlores(); // Método para buscar as flores do banco de dados
        model.addAttribute("flores", flores);
        return "flowerScreen"; // Nome da página HTML que irá exibir as flores
    }

    @Autowired
    public void setFlowerService(FlowerService flowerService) {
        this.flowerService = flowerService;
    }
}
