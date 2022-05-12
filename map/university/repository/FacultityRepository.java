package uz.avazbek.map.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.avazbek.map.university.entity.Facultity;

public interface FacultityRepository extends JpaRepository<Facultity,Integer> {
}
