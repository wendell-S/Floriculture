package com.floriculture.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.floriculture.mysql.Flores;

public interface FlorRepository extends JpaRepository<Flores, Integer> {

	 Optional<Flores> findById(Long id);
}
