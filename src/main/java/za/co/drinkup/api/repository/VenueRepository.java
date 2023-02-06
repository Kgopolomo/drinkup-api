package za.co.drinkup.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import za.co.drinkup.api.entity.Venue;

import java.util.List;

public interface VenueRepository extends JpaRepository<Venue, Long>, JpaSpecificationExecutor<Venue> {

    List<Venue> findByNameContaining(String name);

}
