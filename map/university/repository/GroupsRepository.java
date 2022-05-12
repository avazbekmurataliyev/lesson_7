package uz.avazbek.map.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.avazbek.map.university.entity.Groups;

public interface GroupsRepository extends JpaRepository<Groups,Integer> {
}
