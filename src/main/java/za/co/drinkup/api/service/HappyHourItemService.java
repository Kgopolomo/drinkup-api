package za.co.drinkup.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.drinkup.api.entity.HappyHourItem;
import za.co.drinkup.api.repository.HappyHourItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HappyHourItemService {
    @Autowired
    private HappyHourItemRepository happyHourItemRepository;

    public List<HappyHourItem> getAllHappyHourItems() {
        return happyHourItemRepository.findAll();
    }

    public Optional<HappyHourItem> getHappyHourItemById(Long id) {
        return happyHourItemRepository.findById(id);
    }

    public HappyHourItem addHappyHourItem(HappyHourItem happyHourItem) {
        return happyHourItemRepository.save(happyHourItem);
    }

    public HappyHourItem updateHappyHourItem(Long id, HappyHourItem happyHourItem) {
        return happyHourItemRepository.save(happyHourItem);
    }

    public Object deleteHappyHourItem(Long id) {
        happyHourItemRepository.deleteById(id);
        return null;
    }
}
