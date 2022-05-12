    package uz.avazbek.map.university.entity;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import uz.avazbek.map.entity.Address;

    import javax.persistence.*;
    import java.util.List;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public class Universty {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id ;
        @Column(nullable = false)
        private String universityName ;

        @ManyToMany
        private List<Facultity> facultities ;

        @OneToOne
        private Address address ;

    }
