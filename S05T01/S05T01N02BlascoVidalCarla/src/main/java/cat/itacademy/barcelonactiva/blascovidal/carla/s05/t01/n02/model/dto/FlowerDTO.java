package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n02.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class FlowerDTO {

    private int pkFlowerID;
    private String flowerName;
    private String flowerCountry;
    private String flowerType;

    @JsonIgnore
    private static List<String> membersUE = new ArrayList<>(Arrays.asList("Austria", "Belgium", "Bulgaria", "Croatia",
            "Republic of Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland", "France",
            "Germany", "Greece", "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg",
            "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia", "Slovenia",
            "Spain", "Sweden"));


    public FlowerDTO(String flowerName, String flowerCountry) {
        this.flowerName = flowerName;
        this.flowerCountry = flowerCountry;
        setFlowerType();
    }

    public void setFlowerCountry(String flowerCountry) {
        this.flowerCountry = flowerCountry;
        setFlowerType();
    }

    public void setFlowerType() {
        if (membersUE.contains(this.flowerCountry)) {
            this.flowerType = "UE";
        } else {
            this.flowerType = "Fora UE";
        }
    }
}
