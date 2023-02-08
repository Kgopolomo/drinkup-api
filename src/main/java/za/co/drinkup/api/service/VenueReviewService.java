package za.co.drinkup.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.drinkup.api.entity.Venue;
import za.co.drinkup.api.entity.VenueReview;
import za.co.drinkup.api.repository.VenueRepository;
import za.co.drinkup.api.repository.VenueReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VenueReviewService {

    @Autowired
    private VenueReviewRepository venueReviewRepository;

    @Autowired
    private VenueRepository venueRepository;

    public VenueReview createReview(VenueReview review, Long venueId) {
        Optional<Venue> venue = venueRepository.findById(venueId);
        if (venue.isPresent()) {
            review.setVenue(venue.get());
            return venueReviewRepository.save(review);
        }
        return null;
    }

    public List<VenueReview> getAllReviewsByVenue(Long venueId) {
        Optional<Venue> venue = venueRepository.findById(venueId);
        if (venue.isPresent()) {
            return venueReviewRepository.findByVenue(venue.get());
        }
        return null;
    }

    public VenueReview getReviewById(Long reviewId) {
        Optional<VenueReview> review = venueReviewRepository.findById(reviewId);
        if (review.isPresent()) {
            return review.get();
        }
        return null;
    }

    public VenueReview updateReview(VenueReview review, Long reviewId) {
        Optional<VenueReview> existingReview = venueReviewRepository.findById(reviewId);
        if (existingReview.isPresent()) {
            existingReview.get().setDescription(review.getDescription());
            existingReview.get().setRating(review.getRating());
            return venueReviewRepository.save(existingReview.get());
        }
        return null;
    }

    public void deleteReview(Long reviewId) {
        venueReviewRepository.deleteById(reviewId);
    }
}
