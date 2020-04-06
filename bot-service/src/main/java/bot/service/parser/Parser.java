package bot.service.parser;

import bot.domain.parsing.ParsedInfo;

/**
 * @author Artyom Konashchenko
 * @since 06.04.2020
 */
public abstract class Parser<T extends ParsedInfo> {
    protected abstract T parse(String incomeText);
}
