package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n01.model.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*També tenim una DTO anomenada SucursalDTO, que tindrà les mateixes propietats que l’entitat Sucursal, afegint-ne una:
- String tipusSucursal.
Aquesta propietat, en funció del país de la sucursal, haurà d’indicar si és “UE” o “Fora UE”. Per a fer això,
pots tenir una llista privada a la mateixa DTO (per exemple: List<String> països), amb els països que formen part de la UE.*/

@Data
@NoArgsConstructor
public class SucursalDTO {

    private int pkSucursalID;
    private String nomSucursal;
    private String paisSucursal;
    private String tipusSucursal;

    private static List<String> membersUE = new ArrayList<>(Arrays.asList("Austria", "Belgium", "Bulgaria", "Croatia",
                                        "Republic of Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland", "France",
                                        "Germany", "Greece", "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg",
                                        "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia", "Slovenia",
                                        "Spain", "Sweden"));


    public SucursalDTO(String nomSucursal, String paisSucursal) {
        this.nomSucursal = nomSucursal;
        this.paisSucursal = paisSucursal;
        setTipusSucursal();
    }

    public void setPaisSucursal(String paisSucursal) {
        this.paisSucursal = paisSucursal;
        setTipusSucursal();
    }

    public void setTipusSucursal() {
        if (membersUE.contains(this.paisSucursal)) {
            this.tipusSucursal = "UE";
        } else {
            this.tipusSucursal = "Fora UE";
        }
    }
}
