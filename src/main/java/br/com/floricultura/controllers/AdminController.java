package br.com.floricultura.controllers;

import br.com.floricultura.DTO.FlowerDTO;
import br.com.floricultura.model.Flower;
import br.com.floricultura.repository.FlowerRepository;
import br.com.floricultura.services.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/floricultura")
public class AdminController {

    private final FlowerService flowerService;
    private final FlowerRepository flowerRepository;

    @Autowired
    public AdminController(FlowerService flowerService, FlowerRepository flowerRepository) {
        this.flowerService = flowerService;
        this.flowerRepository = flowerRepository;
    }

    @GetMapping("/admin")
    public String showRegistration() {
        return "admin";
    }

    @PostMapping("/admin")
    public String registerUser(@Valid @ModelAttribute FlowerDTO flowerDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin";
        }

        Flower flowerName = flowerRepository.findByName(flowerDTO.getName());
        Flower flowerLink = flowerRepository.findByLink(flowerDTO.getLink());

        if (flowerName == null && flowerLink == null) {
            flowerService.addFlower(flowerDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Flor cadastrada com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Já existe uma flor com este nome/link!");
        }

        return "redirect:/floricultura/admin";
    }
}
