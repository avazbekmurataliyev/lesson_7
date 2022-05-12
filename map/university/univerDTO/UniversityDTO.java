package uz.avazbek.map.university.univerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UniversityDTO extends GroupDTO{

    private String facultityName ;
    private String universityName ;


}
