package za.co.drinkup.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.drinkup.api.entity.HappyHourDetails;
import za.co.drinkup.api.entity.Venue;
import za.co.drinkup.api.repository.HappyHourDetailsRepository;
import za.co.drinkup.api.repository.VenueRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VenueService {

    @Autowired private final VenueRepository venueRepository;

    @Autowired private final HappyHourDetailsRepository hourDetailsRepository;

    public VenueService(VenueRepository venueRepository, HappyHourDetailsRepository hourDetailsRepository) {
        this.venueRepository = venueRepository;
        this.hourDetailsRepository = hourDetailsRepository;
    }

    public Object getVenues() {
        return venueRepository.findAll();
    }
    public Venue createVenue(Venue venue, List<HappyHourDetails> happyHourDetails) {
        venue.setHappyHourDetails(happyHourDetails);
        happyHourDetails.forEach(hhd -> hhd.setVenue(venue));
        return venueRepository.save(venue);
    }

    public Venue updateVenue(Venue venue, List<HappyHourDetails> happyHourDetails) {
        venue.setHappyHourDetails(happyHourDetails);
        happyHourDetails.forEach(hhd -> hhd.setVenue(venue));
        return venueRepository.save(venue);
    }

    public Venue getVenueById(Long id) {
        return venueRepository.findById(id).orElse(null);
    }

    public List<HappyHourDetails> getHappyHoursByVenueId(Long id) {
        Venue venue = venueRepository.findById(id).orElse(null);
        if (venue == null) {
            return null;
        }
        return hourDetailsRepository.findByVenue(venue);
    }



    public void deleteVenue(Long id) {
        venueRepository.deleteById(id);
    }

    public void deleteHappyHour(Long id) {
        hourDetailsRepository.deleteById(id);
    }

    public List<Venue> searchVenues(String searchTerm, String typeOfSpecial, String city, Double rating, Double proximity) {
        List<Venue> venues = venueRepository.findAll();

        if (searchTerm != null) {
            venues = venues.stream().filter(v -> v.getName().contains(searchTerm)).collect(Collectors.toList());
        }
        if (typeOfSpecial != null) {
            venues = venues.stream().filter(v -> v.getHappyHourDetails().contains(typeOfSpecial)).collect(Collectors.toList());
        }
        if (city != null) {
            venues = venues.stream().filter(v -> v.getCity().equals(city)).collect(Collectors.toList());
        }
//        if (rating != null) {
//            venues = venues.stream().filter(v -> v.getRating() >= rating).collect(Collectors.toList());
//        }
//        if (proximity != null) {
//            // Add logic to filter venues based on proximity
//        }

        return venues;
    }
}
