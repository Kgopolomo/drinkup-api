package za.co.drinkup.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import za.co.drinkup.api.entity.UserProfile;
import za.co.drinkup.api.entity.Venue;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>, JpaSpecificationExecutor<UserProfile> {
    UserProfile findByEmail(String email);

    UserProfile findByUsername(String username);
}
