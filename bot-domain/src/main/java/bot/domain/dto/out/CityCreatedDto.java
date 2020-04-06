package bot.domain.dto.out;

import bot.domain.dto.CityPlaceDto;
import bot.domain.dto.IdentityDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */
@Setter
@Getter
@SuperBuilder
public class CityCreatedDto extends IdentityDto {
    private Long population;
    private Double square;
    private String name;
    private String country;
    private String image;
    private List<CityPlaceDto> places;
}
