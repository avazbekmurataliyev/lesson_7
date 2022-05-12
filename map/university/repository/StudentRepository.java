package uz.avazbek.map.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.avazbek.map.university.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
