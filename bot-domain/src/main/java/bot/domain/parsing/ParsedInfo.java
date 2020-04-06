package bot.domain.parsing;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Artyom Konashchenko
 * @since 06.04.2020
 */
@Getter
@Setter
@SuperBuilder
public abstract class ParsedInfo <T> {
    T parsedBody;
    boolean isParsingFailed;
    String otherMessage;
    String failReason;
}
