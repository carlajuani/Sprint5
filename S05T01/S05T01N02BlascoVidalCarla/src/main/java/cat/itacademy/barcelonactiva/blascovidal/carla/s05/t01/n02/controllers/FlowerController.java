package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n02.controllers;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n02.model.services.IFlowerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flowers")
public class FlowerController {

    @Autowired
    IFlowerService service;

    @PostMapping("/add")
    @Operation(
            summary = "add new flower",
            description = "It requires a request body of a flower",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "json with two parameters:\n"
                    +"name of the flower (String flowerName) and country (String flowerCountry)")
    )
    public ResponseEntity<FlowerDTO> createFlower(@RequestBody FlowerDTO flowerDTO) {
        try {
            return new ResponseEntity<>(service.save(flowerDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/update/{id}")
    @Operation(
            summary = "update flower",
            description = "It requires the original flower's id parameter (int pkFlowerID) and a request body of a flower with the updated parameters: ",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "json with two parameters:\n"
                    +"name of the flower (String flowerName) and country (String flowerCountry)")
    )
    public ResponseEntity<FlowerDTO> updateFruit(@PathVariable("id") int id, @RequestBody FlowerDTO flowerDTO) {
        try {
            FlowerDTO flowerData = service.findById(id);
            flowerData.setFlowerName(flowerDTO.getFlowerName());
            flowerData.setFlowerCountry(flowerDTO.getFlowerCountry());
            return new ResponseEntity<>(service.save(flowerData), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "delete flower",
            description = "It requires a flower's id path variable (int pkFlowerID)"
    )
    public ResponseEntity<HttpStatus> deleteFruit(@PathVariable("id") int id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getOne/{id}")
    @Operation(
            summary = "get flower",
            description = "It requires a flower's id path variable (int pkFlowerID)"
    )
    public ResponseEntity<FlowerDTO> getFruitById(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    @Operation(
            summary = "get all flowers",
            description = "It has a flower's name as optional parameter (String flowerName) to get all the flowers" +
                    "\n"+" with that name. " +
                    "\n"+"If there is no name it returns all flowers.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "optional parameter:\n"
            +"name of the flower (String flowerName)")
    )
    public ResponseEntity<List<FlowerDTO>> getAllFruits(@RequestParam(required = false) String flowerName) {
        try {
            List<FlowerDTO> flowers;
            if (flowerName == null) {
                flowers = service.findAll();
            } else {
                flowers = service.findByNameContaining(flowerName);
            }
            if (flowers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(flowers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
