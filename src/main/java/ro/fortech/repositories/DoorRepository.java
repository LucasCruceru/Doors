package ro.fortech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fortech.entities.Door;

import java.util.Optional;

@Repository
public interface DoorRepository extends JpaRepository<Door, Long> {
    Optional<Door> findByName(String name);
}
