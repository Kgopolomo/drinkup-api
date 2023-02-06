package za.co.drinkup.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.drinkup.api.entity.Venue;
import za.co.drinkup.api.repository.VenueRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapService {
    @Autowired private final VenueRepository venueRepository;

    public MapService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }


    public List<Venue> getVenuesInProximity(double latitude, double longitude, int radius) {
        List<Venue> venues = venueRepository.findAll();
        List<Venue> filteredVenues = new ArrayList<>();
        for (Venue venue : venues) {
            double distance = calculateDistance(latitude, longitude, venue.getLatitude(), venue.getLongitude());
            if (distance <= radius) {
                filteredVenues.add(venue);
            }
        }
        return filteredVenues;
    }

    private double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        // Haversine formula to calculate distance between two coordinates
        double earthRadius = 6371; // radius of the earth in km
        double deltaLatitude = Math.toRadians(latitude2 - latitude1);
        double deltaLongitude = Math.toRadians(longitude2 - longitude1);
        latitude1 = Math.toRadians(latitude1);
        latitude2 = Math.toRadians(latitude2);

        double a = Math.sin(deltaLatitude / 2) * Math.sin(deltaLatitude / 2)
                + Math.cos(latitude1) * Math.cos(latitude2)
                * Math.sin(deltaLongitude / 2) * Math.sin(deltaLongitude / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }
}

