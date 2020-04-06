package bot.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */

@Entity
@Table(name = "city", schema = "public")
@NoArgsConstructor
@Setter
@Getter
@AttributeOverride(name = "id", column = @Column(name = "city_id"))
public class CityEntity extends Identity {

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "photo_url")
    private String image;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CityPlaceEntity> places;

    @OneToOne(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private CityParamsEntity cityParams;
}
