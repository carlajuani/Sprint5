package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n01.model.repository.IRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SucursalServiceImpl implements ISucursalService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IRepository repository;

    @Override
    public void save(SucursalDTO sucursalDTO) {
        repository.save(modelMapper.map(sucursalDTO, Sucursal.class));
    }

    @Override
    public void update(SucursalDTO sucursalDTO) {
        Sucursal sucursal = convertDTOToEntity(sucursalDTO);
        repository.save(sucursal);
    }

    @Override
    public SucursalDTO findById(int id) {
        Optional<Sucursal> optionalSucursal = repository.findById(id);
        SucursalDTO sucursalDTO= null;
        if (optionalSucursal.isPresent()) {
            sucursalDTO = convertEntityToDTO(optionalSucursal.get());
        }
        return sucursalDTO;
    }


    @Override
    public List<SucursalDTO> findByNomSucursalContaining(String nomSucursal) {
        return repository.findByNomSucursalContaining(nomSucursal)
                         .stream()
                         .map(this::convertEntityToDTO)
                         .collect(Collectors.toList());
    }

    @Override
    public List<SucursalDTO> findAll() {
        return repository.findAll()
                         .stream()
                         .map(this::convertEntityToDTO)
                         .collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public SucursalDTO convertEntityToDTO(Sucursal sucursal) {
        return  modelMapper.map(sucursal, SucursalDTO.class);
    }

    public Sucursal convertDTOToEntity(SucursalDTO sucursalDTO) {
        return  modelMapper.map(sucursalDTO, Sucursal.class);
    }
}
