package za.co.drinkup.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.drinkup.api.entity.HappyHourDetails;
import za.co.drinkup.api.repository.HappyHourDetailsRepository;

import java.util.List;
import java.util.Optional;
@Service
public class HappyHourDetailsService {

    @Autowired private HappyHourDetailsRepository happyHourDetailsRepository;

    public HappyHourDetailsService(HappyHourDetailsRepository happyHourDetailsRepository) {
        this.happyHourDetailsRepository = happyHourDetailsRepository;
    }

    public List<HappyHourDetails> getAllHappyHours() {
        return happyHourDetailsRepository.findAll();
    }

    public Optional<HappyHourDetails> getHappyHourById(Long id) {
        return happyHourDetailsRepository.findById(id);
    }
    public HappyHourDetails addHappyHour(HappyHourDetails happyHour) {
        return happyHourDetailsRepository.save(happyHour);
    }

    public HappyHourDetails updateHappyHour(long id, HappyHourDetails happyHourDetails) {
        Optional<HappyHourDetails> existingHappyHour = happyHourDetailsRepository.findById(id);
        if (existingHappyHour.isPresent()) {
            HappyHourDetails updatedHappyHour = existingHappyHour.get();
            updatedHappyHour.setStartTime(happyHourDetails.getStartTime());
            updatedHappyHour.setEndTime(happyHourDetails.getEndTime());
            updatedHappyHour.setName(happyHourDetails.getName());
            updatedHappyHour.setDescription(happyHourDetails.getDescription());
            return happyHourDetailsRepository.save(updatedHappyHour);
        }
        return null;
    }

    public Object deleteHappyHour(Long id) {
        happyHourDetailsRepository.deleteById(id);
        return null;
    }
}
