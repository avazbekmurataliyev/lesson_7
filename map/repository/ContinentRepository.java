package uz.avazbek.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.avazbek.map.entity.Continent;

public interface ContinentRepository extends JpaRepository<Continent,Integer> {

}
