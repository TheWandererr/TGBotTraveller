package bot.domain.parsing;

import bot.domain.content.Command;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */

@Getter
@Setter
@SuperBuilder
public class ParsedCommandInfo extends ParsedInfo<Command> {

}
