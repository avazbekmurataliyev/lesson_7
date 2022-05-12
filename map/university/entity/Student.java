    package uz.avazbek.map.university.entity;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import uz.avazbek.map.entity.Address;

    import javax.persistence.*;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public class Student {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id ;
        @Column(nullable = false)
        private String studentName ;
        @Column(nullable = false)
        private String studentSurname ;

        @OneToOne(optional = false)
        private Address address ;


    }
