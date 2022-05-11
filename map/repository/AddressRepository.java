package uz.avazbek.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.avazbek.map.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {

}
