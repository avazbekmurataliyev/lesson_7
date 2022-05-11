package uz.avazbek.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.avazbek.map.entity.Country;

public interface CountryRepository extends JpaRepository<Country,Integer> {

}
