    package uz.avazbek.map.university.entity;

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
    private Integer id ;
    @Column(nullable = false)
    private String groupName ;
     @OneToMany
    private List<Teacher> teachers ;

    @OneToMany
    private List<Student> students ;

    @ManyToMany
    private List<Subject> subjects ;


    }
