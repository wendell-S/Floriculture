package br.com.floriculture.controllers;

import br.com.floriculture.model.Role;
import br.com.floriculture.model.User;
import br.com.floriculture.repository.RoleRepository;
import br.com.floriculture.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;

@RestController
@RequestMapping("/floricultura")
public class RegistrationController {

    @GetMapping("/registration")
    public ModelAndView showRegistration(){
        ModelAndView modelAndView = new ModelAndView("registration");
        return modelAndView;
    }
    @Autowired
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registration")
    public RedirectView registrateUser(@RequestParam("email") String email,
                                       @RequestParam("password") String password,
                                       @RequestParam("name") String name,
                                       @RequestParam("last_name") String lastName,
                                       @RequestParam("username") String username,
                                       RedirectAttributes redirectAttributes) {


        Role userRole = roleRepository.findByRole("USER");
        if (userRole == null) {
            userRole = new Role("USER");
            roleRepository.save(userRole);
        }

        User user = new User(email, passwordEncoder.encode(password), name, lastName, true, username);
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        redirectAttributes.addFlashAttribute("successMessage", "Conta Cadastrada com sucesso!");
        return new RedirectView("/floricultura/login");
    }
}
