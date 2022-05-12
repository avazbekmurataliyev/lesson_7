package uz.avazbek.map.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.avazbek.map.university.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

}
