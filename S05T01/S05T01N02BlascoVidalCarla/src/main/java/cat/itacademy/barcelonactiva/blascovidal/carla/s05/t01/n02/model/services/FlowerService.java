package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n02.model.services;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n02.model.domain.FlowerEntity;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n02.model.repository.IRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlowerService implements  IFlowerService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IRepository repository;

    @Override
    public FlowerDTO save(FlowerDTO flowerDTO) {
        repository.save(modelMapper.map(flowerDTO, FlowerEntity.class));
        return flowerDTO;
    }

    @Override
    public FlowerDTO update(FlowerDTO flowerDTO) {
        FlowerEntity flower = convertDTOToEntity(flowerDTO);
        repository.save(flower);
        return flowerDTO;
    }

    @Override
    public FlowerDTO findById(int iD) {
        Optional<FlowerEntity> optionalFlower = repository.findById(iD);
        FlowerDTO flowerDTO= null;
        if (optionalFlower.isPresent()) {
            flowerDTO = convertEntityToDTO(optionalFlower.get());
        }
        return flowerDTO;
    }

    @Override
    public List<FlowerDTO> findByNameContaining(String flowerName) {
        return repository.findByFlowerNameContaining(flowerName)
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FlowerDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(int iD) {
        repository.deleteById(iD);
    }

    public FlowerDTO convertEntityToDTO(FlowerEntity flower) {
        return  modelMapper.map(flower, FlowerDTO.class);
    }

    public FlowerEntity convertDTOToEntity(FlowerDTO flowerDTO) {
        return  modelMapper.map(flowerDTO, FlowerEntity.class);
    }
}
