package br.com.floricultura.controllers;

import br.com.floricultura.DTO.FlowerDTO;
import br.com.floricultura.model.Flower;
import br.com.floricultura.repository.FlowerRepository;
import br.com.floricultura.services.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@RestController
@RequestMapping("/floricultura")
public class FlowerController {

    private final FlowerService flowerService;
    private final FlowerRepository flowerRepository;
    @Autowired
    public FlowerController(FlowerService flowerService, FlowerRepository flowerRepository) {
        this.flowerService = flowerService;
        this.flowerRepository = flowerRepository;
    }


    @GetMapping("/admin")
    public ModelAndView showRegistration(){
        return new ModelAndView("admin");
    }


    @PostMapping("/admin")
    public RedirectView registerUser(@Valid @ModelAttribute FlowerDTO flowerDTO,
                                     RedirectAttributes redirectAttributes) {
        Flower flowerName = flowerRepository.findByName(flowerDTO.getName());
        Flower flowerLink = flowerRepository.findByLink(flowerDTO.getLink());


        if (flowerName == null && flowerLink == null) {
            flowerService.addFlower(flowerDTO);
            redirectAttributes.addFlashAttribute("successMessage", "flor cadastrada com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Já existe uma flor com este nome/link!!");
            return new RedirectView("/floricultura/admin");
        }

        return new RedirectView("/floricultura/admin");
    }
}
