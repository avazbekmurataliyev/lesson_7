package uz.maktab.maktab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.maktab.maktab.model.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {

}
