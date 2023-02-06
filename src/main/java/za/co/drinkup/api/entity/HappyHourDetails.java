package za.co.drinkup.api.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="happyHours")
public class HappyHourDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime startTime;
    private LocalTime endTime;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
}
