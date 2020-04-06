package bot.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Artyom Konashchenko
 * @since 05.04.2020
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "city_places", schema = "public")
@AttributeOverride(name = "id", column = @Column(name = "place_id"))
public class CityPlaceEntity extends Identity {

    @Column(name = "place_name")
    private String placeName;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private CityEntity location;
}
