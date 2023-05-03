package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n01.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/*Tenim una entitat anomenada Sucursal, que disposa de les següents propietats:
- Integer pk_SucursalID
- String nomSucursal
- String paisSucursal*/

@Data
@Entity
@Table(name = "Sucursal")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pkSucursalID;

    @Column(name = "nomSucursal")
    private String nomSucursal;

    @Column(name = "paisSucursal")
    private String paisSucursal;

}
