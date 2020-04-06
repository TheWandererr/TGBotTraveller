package bot.service.parser.impl;

import bot.domain.parsing.ParsedCityInfo;
import bot.service.parser.Parser;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static bot.service.messaging.MessageTemplates.*;
import static bot.service.utils.StringUtils.safeArrayFrom;

/**
 * @author Artyom Konashchenko
 * @since 06.04.2020
 */
@Service
public class CityParser implements Parser<ParsedCityInfo> {

    private static final int EMPTY = 0;
    private static final int CITY_NAME_COMPOSITE = 2;
    private static final int CITY_NAME_SIMPLE = 1;

    @Override
    public ParsedCityInfo parse(String incomeText) {
        ParsedCityInfo parsedCityInfo = ParsedCityInfo.builder().build();
        Optional.ofNullable(incomeText).ifPresentOrElse(text -> {
                    if (!StringUtils.isBlank(text)) {
                        processFetchingCity(parsedCityInfo, text.trim());
                    } else {
                        parsingFailed(parsedCityInfo, EMPTY_MESSAGE);
                    }
                }, () -> {
                    parsingFailed(parsedCityInfo, EMPTY_MESSAGE);
                }
        );
        return parsedCityInfo;
    }

    private void parsingFailed(ParsedCityInfo info, String reason) {
        info.setParsingFailed(true);
        info.setFailReason(reason);
    }

    private void parsingSuccessful(ParsedCityInfo info, String cityName, String otherMessage) {
        info.setOtherMessage(otherMessage);
        info.setParsedBody(cityName);
    }

    private void processFetchingCity(ParsedCityInfo result, String text) {
        String[] words = safeArrayFrom(text.trim(), "[ ;,`*/\"'.]+");
        if (words.length == EMPTY) {
            parsingFailed(result, ERROR_PARSING_CITY);
            return;
        }
        String city = getCityName(words);
        if (city != null) {
            parsingSuccessful(result, city, text.replaceAll(city, ""));
            return;
        }
        parsingFailed(result, TOO_LONG_MESSAGE);
    }

    private String getCityName(String[] words) {
        if (words.length > CITY_NAME_COMPOSITE) {
            return null;
        }
        if (words.length == CITY_NAME_SIMPLE) {
            ArrayUtils.remove(words, 0);
            return words[0];
        }
        ArrayUtils.removeAll(words, 0, 1);
        return words[0] + " " + words[1];
    }
}
