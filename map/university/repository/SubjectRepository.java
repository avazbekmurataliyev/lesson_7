package uz.avazbek.map.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.avazbek.map.university.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {

}
