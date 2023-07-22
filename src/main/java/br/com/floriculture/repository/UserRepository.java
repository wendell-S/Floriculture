package br.com.floriculture.repository;

import br.com.floriculture.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, Long> {
    User findByUsername(String username);
}
