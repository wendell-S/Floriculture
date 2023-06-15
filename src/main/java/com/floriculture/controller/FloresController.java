package com.floriculture.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.floriculture.mysql.Flores;
import com.floriculture.repository.FlorRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/flores")
public class FloresController {

    @Autowired
    private FlorRepository florRepository;

    @GetMapping("/florN/{id}")
    public ResponseEntity<String> getFlorName(@PathVariable Long id) {
        Flores flor = florRepository.findById(id).orElse(null);
        if (flor != null) {
            return ResponseEntity.ok(flor.getNome());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/florQ/{id}")
    public ResponseEntity<Integer> getFlorQuant(@PathVariable Long id) {
        Flores flor = florRepository.findById(id).orElse(null);
        if (flor != null) {
            return ResponseEntity.ok(flor.getQuantidade());
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping
    public List<Flores> getCursos() {
        return florRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Flores> createCurso(@Valid @RequestBody Flores flores) {
        Flores savedCurso = florRepository.save(flores);
        return new ResponseEntity<>(savedCurso, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flores> getCursoById(@PathVariable Long id) {
        Optional<Flores> flores = florRepository.findById(id);
        return flores.map(ResponseEntity::ok).orElseThrow(() -> new NoSuchElementException("Curso não encontrado com o ID: " + id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flores> updateFlores(@PathVariable Long id, @Valid @RequestBody Flores floresDetails) {
        Flores flores = florRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Não foi encontrada uma flor com o ID: " + id));

        flores.setNome(floresDetails.getNome());
        flores.setQuantidade(floresDetails.getQuantidade());

        Flores updatedFlores = florRepository.save(flores);
        return ResponseEntity.ok(updatedFlores);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        Flores flores = florRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Não foi encontrada uma flor com o ID: " + id));

        florRepository.delete(flores);
        return ResponseEntity.noContent().build();
    }
}
