package uz.maktab.maktab.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(nullable = false)
    private String city ;
    @Column(nullable = false)
    private String district ;
    @Column(nullable = false)
    private String street ;

}