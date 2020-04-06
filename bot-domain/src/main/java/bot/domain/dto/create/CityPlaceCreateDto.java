package bot.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

/**
 * @author Artyom Konashchenko
 * @since 06.04.2020
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CityPlaceCreateDto {

    private String description;

    @NotBlank
    private String signature;
}
