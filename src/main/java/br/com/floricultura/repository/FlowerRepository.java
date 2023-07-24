package br.com.floricultura.repository;

import br.com.floricultura.model.Flower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerRepository extends CrudRepository<Flower, Long> {
   Flower findByName(String name) ;
}
