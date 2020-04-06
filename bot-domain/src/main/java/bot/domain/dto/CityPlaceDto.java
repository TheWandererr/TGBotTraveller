package bot.domain.dto;

import bot.domain.dto.create.CityPlaceCreateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Artyom Konashchenko
 * @since 05.04.2020
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CityPlaceDto extends IdentityDto {

    @NotNull
    private CityPlaceCreateDto place;
}
