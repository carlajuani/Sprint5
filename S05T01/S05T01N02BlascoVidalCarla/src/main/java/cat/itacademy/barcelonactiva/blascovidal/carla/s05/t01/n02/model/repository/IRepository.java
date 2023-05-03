package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n02.model.repository;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n02.model.domain.FlowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepository extends JpaRepository <FlowerEntity, Integer> {
    List<FlowerEntity> findByFlowerNameContaining(String flowerName);
}
