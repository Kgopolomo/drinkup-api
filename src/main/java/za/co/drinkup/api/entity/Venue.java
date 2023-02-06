package za.co.drinkup.api.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="venues")
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String province;
    private String city;
    private String restrictions;
    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    private List<HappyHourDetails> happyHourDetails;
}
