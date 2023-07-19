package com.floriculture.repository;

import com.floriculture.mysql.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {


    Users findByEmailAndPassword(String email, String password);

    Users findByEmail(String email);
}
