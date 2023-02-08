package za.co.drinkup.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.drinkup.api.entity.HappyHourDetails;
import za.co.drinkup.api.entity.HappyHourItem;
import za.co.drinkup.api.entity.Location;
import za.co.drinkup.api.entity.Venue;
import za.co.drinkup.api.repository.HappyHourDetailsRepository;
import za.co.drinkup.api.repository.VenueRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class VenueService {

    @Autowired private final VenueRepository venueRepository;
    @Autowired private final LocationService locationService;

    @Autowired private final HappyHourDetailsService happyHourDetailsService;

    @Autowired private final HappyHourDetailsRepository hourDetailsRepository;

    public VenueService(VenueRepository venueRepository, LocationService locationService, HappyHourDetailsService happyHourDetailsService, HappyHourDetailsRepository hourDetailsRepository) {
        this.venueRepository = venueRepository;
        this.locationService = locationService;
        this.happyHourDetailsService = happyHourDetailsService;
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

    public List<Venue> getAllVenuesByLocation(Double latitude, Double longitude) {
        Location userLocation = locationService.getLocationByLatitudeAndLongitude(latitude, longitude);
        List<Venue> venues = venueRepository.findAll();
        List<Venue> venuesInProximity = new ArrayList<>();

        for (Venue venue : venues) {
            Location venueLocation = locationService.getLocationByLatitudeAndLongitude(venue.getLocation().getLatitude(), venue.getLocation().getLongitude());
            double distance = locationService.getDistanceBetweenLocations(userLocation, venueLocation);

            if (distance <= 10) {
                venuesInProximity.add(venue);
            }
        }

        return venuesInProximity;
    }



    public void deleteVenue(Long id) {
        venueRepository.deleteById(id);
    }

    public void deleteHappyHour(Long id) {
        hourDetailsRepository.deleteById(id);
    }

    public List<Venue> searchVenues(String searchTerm, String typeOfSpecial, String city, Double rating, Double proximity) {

        return null;
    }

    public List<HappyHourDetails> getVenuesByBudget(double budget) {
        List<HappyHourDetails> allHappyHours = happyHourDetailsService.getAllHappyHours();
        List<HappyHourDetails> filteredHappyHours = new ArrayList<>();
        for (HappyHourDetails happyHour : allHappyHours) {
            double totalPrice = 0.0;
            for (HappyHourItem item : happyHour.getHappyHourItems()) {
                totalPrice += item.getPrice();
            }
            if (totalPrice <= budget) {
                filteredHappyHours.add(happyHour);
            }
        }
        return filteredHappyHours;
    }
}
