package bot.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "city_params", schema = "public")
@AttributeOverride(name = "id", column = @Column(name = "city_params_id"))
public class CityParamsEntity extends Identity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @Column(name = "square")
    private Double square;

    @Column(name = "population")
    private Long population;
}
