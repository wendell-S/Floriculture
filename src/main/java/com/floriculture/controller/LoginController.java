package com.floriculture.controller;

import com.floriculture.mysql.Users;
import com.floriculture.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/floricultura")
public class LoginController {

    @Autowired
    private UsersRepository userRepository;

    @RequestMapping("/login")
    public String showPaginaLogin() {
        return "html/login.html";
    }

    @PostMapping("/login")
    public String processLoginForm(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        Users userOptional = userRepository.findByEmailAndPassword(email, password);

        if (userOptional != null) {
            return "redirect:/floricultura/mensagem";
        }else {
                String errorMessage = "O email não foi encontrado";
                model.addAttribute("errorMessage", errorMessage);
                return "html/login.html";
            }

        }
    }


