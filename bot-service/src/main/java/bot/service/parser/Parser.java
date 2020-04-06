package bot.service.parser;

import bot.domain.parsing.ParsedInfo;

/**
 * @author Artyom Konashchenko
 * @since 06.04.2020
 */
public interface Parser <T extends ParsedInfo> {
    T parse(String incomeText);
}
