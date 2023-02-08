package za.co.drinkup.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.drinkup.api.entity.Location;
import za.co.drinkup.api.repository.LocationRepository;

import java.util.List;

@Service
public class LocationService {

    private static final int EARTH_RADIUS = 6371;
    @Autowired
    private LocationRepository locationRepository;

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location updateLocation(Location location) {
        return locationRepository.save(location);
    }

    public Object deleteLocation(Long locationId) {
         locationRepository.deleteById(locationId);
        return null;
    }

    public Location getLocation(Long locationId) {
        return locationRepository.findById(locationId).orElse(null);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationByLatitudeAndLongitude(double latitude, double longitude) {
        return locationRepository.findByLatitudeAndLongitude(latitude, longitude);
    }
    public Double getDistanceBetweenLocations(Location userLocation, Location venueLocation) {

        Double latDistance = toRadian(userLocation.getLatitude() - venueLocation.getLatitude());
        Double lonDistance = toRadian(userLocation.getLongitude() - venueLocation.getLongitude());
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRadian(venueLocation.getLatitude())) * Math.cos(toRadian(userLocation.getLatitude())) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return EARTH_RADIUS * c;
    }

    private static Double toRadian(Double value) {
        return value * Math.PI / 180;
    }
}
