package za.co.drinkup.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import za.co.drinkup.api.entity.HappyHourItem;
import za.co.drinkup.api.entity.Venue;
import za.co.drinkup.api.entity.VenueReview;

import java.util.List;

@Repository
public interface VenueReviewRepository extends JpaRepository<VenueReview, Long>, JpaSpecificationExecutor<VenueReview> {
    List<VenueReview> findByVenue(Venue venue);
}
