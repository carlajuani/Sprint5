package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n03.model.services;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n03.model.dto.FlowerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FlowerService implements IFlowerService {

    @Autowired
    private WebClient webClient;

    @Override
    public Mono<Void> save(FlowerDTO flowerDTO) {
        return webClient.post()
                        .uri("/add")
                        .body(Mono.just(flowerDTO), FlowerDTO.class)
                        .retrieve()
                        .bodyToMono(Void.class);
    }

    @Override
    public Mono<Void> update(FlowerDTO flowerDTO) {
        return webClient.put()
                .uri("/update/" + flowerDTO.getPkFlowerID())
                .body(Mono.just(flowerDTO), FlowerDTO.class)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public Mono<FlowerDTO> findById(int iD) {
        return webClient.get()
                        .uri("/getOne/" + iD)
                        .retrieve()
                        .bodyToMono(FlowerDTO.class);
    }

    @Override
    public Flux<FlowerDTO> findByNameContaining(String flowerName) {
        return webClient.get()
                        .uri(uriBuilder -> uriBuilder.path("/getAll").queryParam("flowerName",flowerName).build())
                        .retrieve()
                        .bodyToFlux(FlowerDTO.class);
    }

    @Override
    public Flux<FlowerDTO> findAll() {
        return webClient.get()
                        .uri("/getAll")
                        .retrieve()
                        .bodyToFlux(FlowerDTO.class);
    }

    @Override
    public Mono<Void> deleteById(int iD) {
        return webClient.delete()
                        .uri("/delete/" + iD)
                        .retrieve()
                        .bodyToMono(Void.class);
    }

}
