package br.com.floricultura.services;

import br.com.floricultura.DTO.UserDTO;
import br.com.floricultura.model.Role;
import br.com.floricultura.model.User;
import br.com.floricultura.repository.RoleRepository;
import br.com.floricultura.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserDTO userRegistrationDTO) {
        if (userRegistrationDTO.getUsername() == null || userRegistrationDTO.getUsername().isEmpty()
                || userRegistrationDTO.getPassword() == null || userRegistrationDTO.getPassword().isEmpty()
                || userRegistrationDTO.getEmail() == null || userRegistrationDTO.getEmail().isEmpty()
                || userRegistrationDTO.getName() == null || userRegistrationDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Todos os campos são obrigatórios.");
        }

        if (userRegistrationDTO.getUsername().trim().isEmpty() || userRegistrationDTO.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome de usuário e senha inválidos.");
        }

        Role userRole = roleRepository.findByRole("USER");
        if (userRole == null) {
            userRole = new Role("USER");
            roleRepository.save(userRole);
        }

        User user = new User(userRegistrationDTO.getEmail(),
                passwordEncoder.encode(userRegistrationDTO.getPassword()),
                userRegistrationDTO.getName(),
                userRegistrationDTO.getUsername());

        user.setRoles(Collections.singletonList(userRole));
        userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
