package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n02.model.services;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n02.model.domain.FlowerEntity;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n02.model.dto.FlowerDTO;

import java.util.List;

public interface IFlowerService {

    FlowerDTO save(FlowerDTO flowerDTO);

    FlowerDTO update(FlowerDTO flowerDTO);

    FlowerDTO findById(int iD);

    List<FlowerDTO> findByNameContaining(String flowerName);

    List<FlowerDTO> findAll();

    void deleteById(int iD);

    FlowerDTO convertEntityToDTO(FlowerEntity flower);

    FlowerEntity convertDTOToEntity(FlowerDTO flowerDTO);
}
