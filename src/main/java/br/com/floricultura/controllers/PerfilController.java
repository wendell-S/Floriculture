package br.com.floricultura.controllers;

import br.com.floricultura.model.User;
import br.com.floricultura.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/floricultura")
public class PerfilController {
    private final UserService userService;

    public PerfilController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/perfil")
    public String showProfilePage(Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);


        model.addAttribute("name", user.getName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("username", user.getUsername());

        return "profileScreen";
    }
}
