package uz.maktab.maktab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.maktab.maktab.model.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
