package za.co.drinkup.api.dto;

import lombok.Getter;
import lombok.Setter;
import za.co.drinkup.api.entity.HappyHourDetails;

import java.util.List;
@Getter
@Setter
public class VenueDto {
    private Long id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String province;
    private String city;
    private String restrictions;
    private List<HappyHourDetails> happyHourDetails;
}
