package com.floriculture.controller;

import com.floriculture.configuration.VerificadorUtil;
import com.floriculture.implement.UserService;
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
public class RegistrationController {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping("/registration")
    public String showPaginaLogin() {
        return "html/registration.html";
    }

    @PostMapping("/registration")
    public String processRegistrationForm(@RequestParam("name") String name, @RequestParam("email") String email,
                                          @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword,
                                          @RequestParam("phone") String phone, Model model) {

        Users userOptional = userRepository.findByEmail(email);


        if (VerificadorUtil.verificarErro(name, email, password, confirmPassword, phone )){



            String errorMessage = "algum dado invalido!";
            model.addAttribute("errorMessage", errorMessage);
            return "html/registration.html";
        } if (userOptional != null){
            String errorMessage = "já existe uma conta com o email usado!!";
            model.addAttribute("errorMessage", errorMessage);
            return "html/registration.html";
        }else {

            Users users = new Users(name, email, phone, confirmPassword);
            userService.save(users);
            model.addAttribute("successMessage", "Conta cadastrada com sucesso!");
            return "html/login.html";


        }

    }

}
