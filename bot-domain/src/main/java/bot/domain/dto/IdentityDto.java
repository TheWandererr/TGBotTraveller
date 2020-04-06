package bot.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Artyom Konashchenko
 * @since 06.04.2020
 */

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class IdentityDto {
    Long id;
}
