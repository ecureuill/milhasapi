package ecureuill.milhasapi.domain.destination;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long>{

    List<Destination> findAllByNameStartingWith(String name);
    
}
