package uz.avazbek.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.avazbek.map.entity.District;

public interface DistrictRepository extends JpaRepository<District,Integer> {
}
