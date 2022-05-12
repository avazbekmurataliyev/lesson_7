package uz.avazbek.map.university.univerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GroupDTO extends StudentDTO{
    private String groupName ;
    private String subjectName ;

    private String teacherName ;
    private String specialization ;
}
