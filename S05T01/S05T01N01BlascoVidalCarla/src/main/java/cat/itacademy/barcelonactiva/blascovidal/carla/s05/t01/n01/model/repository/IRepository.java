package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n01.model.repository;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n01.model.domain.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepository extends JpaRepository <Sucursal, Integer>{
    List<Sucursal> findByNomSucursalContaining(String nomSucursal);
}
