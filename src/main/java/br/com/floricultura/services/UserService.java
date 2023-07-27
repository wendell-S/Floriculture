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
        validateUserDTO(userRegistrationDTO);

        Role userRole = roleRepository.findByRole("USER");
        if (userRole == null) {
            userRole = new Role("USER");
            roleRepository.save(userRole);
        }

        User user = new User(
                userRegistrationDTO.getEmail(),
                passwordEncoder.encode(userRegistrationDTO.getPassword()),
                userRegistrationDTO.getName(),
                userRegistrationDTO.getUsername()
        );

        user.setRoles(Collections.singletonList(userRole));
        userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean validateUserDTO(UserDTO userDTO) {
        return isEmailValid(userDTO.getEmail()) && isNameValid(userDTO.getName()) && isUsernameValid(userDTO.getUsername());
    }

    public boolean isEmailValid(String email) {
        return email != null && !email.trim().isEmpty() && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }


    public boolean isNameValid(String name) {
        return name != null && !name.isEmpty();
    }

    public boolean isUsernameValid(String username) {
        return username != null && !username.isEmpty();
    }

    public User getUserIfExists(String username, String email, String name) {
        User userByUsername = userRepository.findByUsername(username);
        User userByEmail = userRepository.findByEmail(email);
        User userByName = userRepository.findByName(name);

        if (userByUsername != null) {
            return userByUsername;
        }

        if (userByEmail != null) {
            return userByEmail;
        }

        if (userByName != null) {
            return userByName;
        }

        return null;
    }

}
