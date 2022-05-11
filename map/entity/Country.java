    package uz.avazbek.map.entity;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import javax.persistence.*;
    import java.util.List;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public class Country {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id ;
        @Column(nullable = false)
        private String countryName ;
        @OneToMany
        private List<Region> regions ;

    }
