package bot.service.messaging;

import bot.domain.parsing.ParsedCommandInfo;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */
public interface IResponsePreparatoryService {
    SendMessage prepareResponseForCommand(ParsedCommandInfo parsedCommandInfo, Message message);

    PartialBotApiMethod<Message> prepareResponseForTextMessage(Message message);
}
