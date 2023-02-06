package za.co.drinkup.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import za.co.drinkup.api.entity.HappyHourDetails;
import za.co.drinkup.api.entity.Venue;

import java.util.List;

public interface HappyHourDetailsRepository extends JpaRepository<HappyHourDetails, Long>, JpaSpecificationExecutor<HappyHourDetails> {
    List<HappyHourDetails> findByNameContaining(String name);

    List<HappyHourDetails> findByVenue(Venue venue);
}
