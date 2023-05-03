package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n01.model.dto.SucursalDTO;
import java.util.List;

public interface ISucursalService {

    void save(SucursalDTO sucursalDTO);

    void update(SucursalDTO sucursal);

    SucursalDTO findById(int id);

    List<SucursalDTO> findByNomSucursalContaining(String nomSucursal);

    List<SucursalDTO> findAll();

    void deleteById(int id);

}
