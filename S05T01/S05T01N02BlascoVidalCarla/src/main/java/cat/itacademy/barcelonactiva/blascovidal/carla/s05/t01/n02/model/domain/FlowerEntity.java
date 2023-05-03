package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n02.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Flower")
public class FlowerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pkFlowerID;

    @Column(name = "flowerName")
    private String flowerName;

    @Column(name = "flowerCountry")
    private String flowerCountry;

}
