package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n03.model.services;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n03.model.dto.FlowerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFlowerService {

    Mono<Void> save(FlowerDTO flowerDTO);

    Mono<Void> update(FlowerDTO flowerDTO);

    Mono<FlowerDTO> findById(int iD);

    Flux<FlowerDTO> findByNameContaining(String flowerName);

    Flux<FlowerDTO> findAll();

    Mono<Void> deleteById(int iD);

}
