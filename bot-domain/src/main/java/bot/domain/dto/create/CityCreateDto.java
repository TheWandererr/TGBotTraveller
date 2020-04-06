package bot.domain.dto.create;

import bot.domain.dto.CityPlaceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */


@Getter
@AllArgsConstructor
@NoArgsConstructor
//@SuperBuilder
public class CityCreateDto {

    @NotEmpty
    private String name;

    @NotBlank
    private String country;

    @NotNull
    @Min(1L)
    private Long population;

    @NotNull
    @Min(1L)
    private Double square;

    @NotBlank
    private String image;
}
