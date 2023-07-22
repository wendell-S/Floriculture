package br.com.floriculture;

import br.com.floriculture.model.Role;
import br.com.floriculture.model.User;
import br.com.floriculture.repository.RoleRepository;
import br.com.floriculture.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");

        User user = new User("admin@code.com", passwordEncoder.encode("224654"),"kaue", "Super", true, "kaue" );
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);



    }
}