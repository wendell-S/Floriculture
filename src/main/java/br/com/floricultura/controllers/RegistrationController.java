package br.com.floricultura.controllers;

import br.com.floricultura.DTO.UserDTO;
import br.com.floricultura.model.User;
import br.com.floricultura.repository.UserRepository;
import br.com.floricultura.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@RestController
@RequestMapping("/floricultura")
public class RegistrationController {

    @GetMapping("/registration")
    public ModelAndView showRegistration(){
        return new ModelAndView("registration");
    }
    private final UserService userService;
    private UserRepository userRepository;

    @Autowired
    public RegistrationController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/registration")
    public RedirectView registerUser(@Valid @ModelAttribute UserDTO userRegistrationDTO,
                                     RedirectAttributes redirectAttributes) {
        User userUsername = userRepository.findByUsername(userRegistrationDTO.getUsername());
        User userEmail = userRepository.findByEmail(userRegistrationDTO.getEmail());
        User userName = userRepository.findByName(userRegistrationDTO.getName());


        if (userUsername == null && userEmail == null && userName == null) {
            userService.registerUser(userRegistrationDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Conta cadastrada com sucesso!");
            return new RedirectView("/floricultura/registration");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Já existe uma conta com alguma dessas credenciais!!!");
            return new RedirectView("/floricultura/registration");
        }

    }
}
