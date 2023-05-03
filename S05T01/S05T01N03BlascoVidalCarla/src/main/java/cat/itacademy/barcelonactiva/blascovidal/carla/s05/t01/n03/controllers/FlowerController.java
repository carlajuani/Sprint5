package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n03.controllers;

/*http://localhost:9002/flor/clientFlorsAdd
http://localhost:9002/flor/clientFlorsUpdate
http://localhost:9002/flor/clientFlorsDelete/{id}
http://localhost:9002/flor/clientFlorsGetOne/{id}
http://localhost:9002/flor/clientFlorsAll*/

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n03.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n03.model.services.IFlowerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/flowers")
public class FlowerController {

    @Autowired
    IFlowerService service;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "add new flower",
            description = "It requires a request body of a flower",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "json with two parameters:\n"
                    +"name of the flower (String flowerName) and country (String flowerCountry)")
    )
    public ResponseEntity<Mono<Void>> createFlower(@RequestBody FlowerDTO flowerDTO) throws Exception {
        ResponseEntity response;
        try {
           response = new ResponseEntity<>(service.save(flowerDTO), HttpStatus.OK);
        } catch (Exception e){
            throw  new Exception("ERROR fruit not found or created",e.getCause());
        }
         return response;
    }


    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "update flower",
            description = "It requires the original flower's id parameter (int pkFlowerID) and a request body of a flower with the updated parameters: ",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "json with two parameters:\n"
            +"name of the flower (String flowerName) and country (String flowerCountry)")
    )
    public ResponseEntity<Mono<Void>> updateFruit(@PathVariable("id") int id, @RequestBody FlowerDTO flowerDTO) throws Exception {
        ResponseEntity response;
        try {
            response = new ResponseEntity<>(service.update(flowerDTO), HttpStatus.OK);
        } catch (Exception e){
            throw  new Exception("ERROR fruit not found by id or updated",e.getCause());
        }
        return response;
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(
            summary = "delete flower",
            description = "It requires a flower's id path variable (int pkFlowerID)"
    )
    public ResponseEntity<Mono<Void>> deleteFruit(@PathVariable("id") int id) throws Exception {
        ResponseEntity response;
        try {
            response = new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
        } catch (Exception e){
            throw  new Exception("ERROR id's fruit not found",e.getCause());
        }
        return response;
    }

    @GetMapping(value = "/getOne/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "get flower",
            description = "It requires a flower's id path variable (int pkFlowerID)"
    )
    public ResponseEntity<Mono<FlowerDTO>> getFruitById(@PathVariable("id") int id) throws Exception{
        ResponseEntity response;
        try {
            response = new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (Exception e){
            throw new Exception("ERROR id's fruit not found",e.getCause());
        }
        return response;

    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "get all flowers",
            description = "It has a flower's name as optional parameter (String flowerName) to get all the flowers with that name.\n "
                    +"If there is no name it returns all flowers.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "optional parameter:\n"
                    +"name of the flower (String flowerName)")
    )
    public ResponseEntity<Flux<FlowerDTO>> getAllFruits(@RequestParam(required = false) String flowerName) throws Exception {
        ResponseEntity response;
        try {
            if (flowerName == null) {
                response = new ResponseEntity<>(service.findAll(), HttpStatus.OK);
            } else {
                response =  new ResponseEntity<>(service.findByNameContaining(flowerName), HttpStatus.OK);
            }
        } catch (Exception e){
            throw  new Exception("ERROR getting all fruits",e.getCause());
        }
        return response;
    }
}
