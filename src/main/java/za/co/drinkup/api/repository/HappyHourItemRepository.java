package za.co.drinkup.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import za.co.drinkup.api.entity.HappyHourDetails;
import za.co.drinkup.api.entity.HappyHourItem;

public interface HappyHourItemRepository extends JpaRepository<HappyHourItem, Long>, JpaSpecificationExecutor<HappyHourItem> {
}
