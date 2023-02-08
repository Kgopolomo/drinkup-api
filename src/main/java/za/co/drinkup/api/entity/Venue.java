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
    private String phoneNumber;
    private String website;
    private Integer rating;
    private String restrictions;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")

    private Location location;
    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    private List<HappyHourDetails> happyHourDetails;






}
