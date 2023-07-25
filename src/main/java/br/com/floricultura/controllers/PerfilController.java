package br.com.floricultura.controllers;

import br.com.floricultura.model.User;
import br.com.floricultura.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/floricultura")
public class PerfilController {
    @Autowired
    private UserService userService;

    @RequestMapping("/perfil")
    public String showPaginaPerfil(Authentication authentication, Model model) {
        String users  = authentication.getName();
        User user = userService.getUserByUsername(users);

        model.addAttribute("name", user.getName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("username", user.getUsername());

        return "profileScreen";
    }

}
