package za.co.drinkup.api.dto;

import lombok.Getter;
import lombok.Setter;
import za.co.drinkup.api.entity.Venue;

import java.time.LocalTime;
@Getter
@Setter
public class HappyHoursDto {
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private String name;
    private String description;
    private Venue venue;
}
