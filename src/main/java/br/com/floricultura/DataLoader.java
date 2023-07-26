package br.com.floricultura;
import br.com.floricultura.model.Flower;
import br.com.floricultura.model.Role;
import br.com.floricultura.model.User;
import br.com.floricultura.repository.FlowerRepository;
import br.com.floricultura.repository.RoleRepository;
import br.com.floricultura.repository.UserRepository;
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
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    FlowerRepository flowerRepository;


    @Override
    public void run(String... args) {


        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        if (adminRole == null && userRole == null){
            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));
        }


        User user = new User("admin@gmail.com", passwordEncoder.encode("admin"),"Admin",  "admin" );
        User user1 = new User("cris@gmail.com", passwordEncoder.encode("cris"),"cris",  "cris" );

       User user2 =  userRepository.findByName(user.getName());
        User user3 =  userRepository.findByName(user1.getName());

        if (user2 == null && user3 == null){
            user.setRoles(Arrays.asList(adminRole));
            userRepository.save(user);

            user1.setRoles(Arrays.asList(userRole));
            userRepository.save(user1);
        }

        Flower camelia = new Flower("camelia", "https://blog.twinshoes.es/wp-content/uploads/camelia.jpg");
        Flower crisantemo = new Flower("crisantemo", "https://blog.twinshoes.es/wp-content/uploads/crisantemo.jpg");
        Flower dalia = new Flower("dalia", "https://blog.twinshoes.es/wp-content/uploads/dalia.jpg");
        Flower geranio = new Flower("geranio", "https://blog.twinshoes.es/wp-content/uploads/geranio.jpg");
        Flower gladiolus = new Flower("gladiolus", "https://blog.twinshoes.es/wp-content/uploads/gladiolo-22.jpg");
        Flower jacinto = new Flower("jacinto", "https://blog.twinshoes.es/wp-content/uploads/jacinto.jpg");

     Flower flower1 = flowerRepository.findByName(camelia.getName());
     Flower flower2 = flowerRepository.findByName(crisantemo.getName());
     Flower flower3 = flowerRepository.findByName(dalia.getName());
     Flower flower4 = flowerRepository.findByName(geranio.getName());
     Flower flower5 = flowerRepository.findByName(gladiolus.getName());
     Flower flower6 = flowerRepository.findByName(jacinto.getName());

     if (flower1 == null && flower2 == null && flower3 == null && flower4 == null && flower5 == null && flower6 == null){
         flowerRepository.save(camelia);
         flowerRepository.save(crisantemo);
         flowerRepository.save(dalia);
         flowerRepository.save(geranio);
         flowerRepository.save(gladiolus);
         flowerRepository.save(jacinto);

     }






    }
}