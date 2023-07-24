package br.com.floricultura.services;

import br.com.floricultura.DTO.FlowerDTO;
import br.com.floricultura.model.Flower;
import br.com.floricultura.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlowerService {

    private final FlowerRepository flowerRepository;

    @Autowired
    public FlowerService(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    public List<FlowerDTO> getAllFlowers() {
        List<Flower> flowers = (List<Flower>) flowerRepository.findAll();
        return flowers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void addFlower(FlowerDTO flowerDTO) {

        Flower flower = new Flower(flowerDTO.getName(), flowerDTO.getLink());

        flowerRepository.save(flower);
    }

    private FlowerDTO convertToDTO(Flower flower){
        FlowerDTO flowerDTO =  new FlowerDTO();
        flowerDTO.setName(flower.getName());
        flowerDTO.setLink(flower.getLink());
        return flowerDTO;
    }


    public List<Flower> listarFlores() {
        return (List<Flower>) flowerRepository.findAll();
    }
}
