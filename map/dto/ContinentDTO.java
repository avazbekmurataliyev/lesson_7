package uz.avazbek.map.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ContinentDTO extends CountryDTO{
    private String continentName ;
}
