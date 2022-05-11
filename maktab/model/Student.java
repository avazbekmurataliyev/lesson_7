package uz.maktab.maktab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false)
    private String firstName ;

    @Column(nullable = false)
    private String lastName ;

    @ManyToOne
    private Mark mark ;

    @OneToOne
    private Address address ;
}
