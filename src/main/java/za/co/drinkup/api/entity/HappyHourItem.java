package za.co.drinkup.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="happyHourItems")
public class HappyHourItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private String itemDescription;
    private double price;

    @ManyToOne
    @JoinColumn(name = "happyHour_id")
    private HappyHourDetails happyHour;
}
