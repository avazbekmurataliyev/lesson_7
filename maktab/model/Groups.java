package uz.maktab.maktab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false,unique = true)
    private String name ;

    @OneToMany
    private List<Student> students ;

    @ManyToMany
    private List<Teacher> teacherList ;

}
