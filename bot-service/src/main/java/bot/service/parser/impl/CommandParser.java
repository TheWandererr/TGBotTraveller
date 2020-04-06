package bot.service.parser.impl;

import bot.domain.content.Command;
import bot.domain.parsing.ParsedCommandInfo;
import bot.service.parser.Parser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static bot.domain.content.Command.safeDefine;
import static bot.service.messaging.MessageTemplates.EMPTY_MESSAGE;
import static bot.service.messaging.MessageTemplates.ERROR_PARSING_COMMAND;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */
@Service
public class CommandParser implements Parser<ParsedCommandInfo> {

    private static final int NOT_PRESENT = -1;
    private static final String COMMAND_PREFIX = "/";

    @Override
    public ParsedCommandInfo parse(String incomeText) {
        ParsedCommandInfo result = ParsedCommandInfo.builder().build();
        Optional.ofNullable(incomeText).ifPresentOrElse(text -> {
            if (StringUtils.isBlank(incomeText) || !isCommand(incomeText)) {
                parsingFailed(result, ERROR_PARSING_COMMAND);
                return;
            }
            StringBuilder sb = new StringBuilder(incomeText.trim());
            Command command = cutCommand(sb);
            parsingSuccessful(result, command, sb.toString());
        }, () -> {
            parsingFailed(result, EMPTY_MESSAGE);
        });
        return result;
    }

    private Command cutCommand(StringBuilder incomeText) {
        int spaceIndex = incomeText.indexOf(" ");
        if (NOT_PRESENT != spaceIndex) {
            Command cmd = safeDefine(incomeText.substring(COMMAND_PREFIX.length(), spaceIndex));
            incomeText.delete(COMMAND_PREFIX.length(), spaceIndex);
            return cmd;
        }
        return safeDefine(incomeText.substring(COMMAND_PREFIX.length()));
    }

    private void parsingSuccessful(ParsedCommandInfo target, Command body, String otherMessage) {
        target.setOtherMessage(otherMessage);
        target.setParsedBody(body);
    }

    private void parsingFailed(ParsedCommandInfo target, String reason) {
        target.setFailReason(reason);
        target.setParsingFailed(true);
    }

    public static boolean isCommand(String text) {
        return text != null && text.startsWith(COMMAND_PREFIX);
    }
}
