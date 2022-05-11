package uz.avazbek.map.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DistrictDTO {

    private String districtName ;

    // Address entity
    private String streetName ;

    private String homeNumber ;
}
