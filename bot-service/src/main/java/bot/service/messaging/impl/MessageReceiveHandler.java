package bot.service.messaging.impl;

import bot.domain.content.MessageType;
import bot.domain.parsing.ParsedCommandInfo;
import bot.service.initializer.Bot;
import bot.service.messaging.IMessageReceiveHandler;
import bot.service.messaging.IResponseCreatingService;
import bot.service.messaging.IResponsePreparatoryService;
import bot.service.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static bot.service.messaging.MessageTemplates.IMPOSSIBLE_ACTION;
import static bot.service.parser.impl.CommandParser.isCommand;
import static bot.service.utils.MessageUtils.defineMessageType;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */
@Component
public class MessageReceiveHandler implements IMessageReceiveHandler {

    @Autowired
    private Parser<ParsedCommandInfo> commandParser;

    @Autowired
    private IResponsePreparatoryService responsePreparatoryService;

    @Autowired
    private IResponseCreatingService responseCreatingService;

    @Override
    public void process(Object received, Bot botInstance) {
        // TODO others
        if (received instanceof Update) {
            processUpdate((Update) received, botInstance);
        }
    }

    private void processUpdate(Update update, Bot botInstance) {
        Message msg = update.getMessage();
        PartialBotApiMethod<Message> data = null;

        MessageType messageType = defineMessageType(msg);

        switch (messageType) {
            case TEXT: {
                data = isCommand(msg.getText()) ? processCommandTextMessage(msg) : processNonCommandTextMessage(msg);
                break;
            }
            case UNDEFINED: {
                data = responseCreatingService.createSendMessageResponse(msg, IMPOSSIBLE_ACTION);
            }
        }
        botInstance.getSendData().add(data);
    }

    private SendMessage processCommandTextMessage(Message msg) {
        String incomeText = msg.getText();
        ParsedCommandInfo commandInfo = commandParser.parse(incomeText);
        if (commandInfo.isParsingFailed()) {
            return responseCreatingService.createSendMessageResponse(msg, commandInfo.getFailReason(), msg.getFrom().getFirstName());
        } else {
            return responsePreparatoryService.prepareResponseForCommand(commandInfo, msg);
        }
    }

    private PartialBotApiMethod<Message> processNonCommandTextMessage(Message msg) {
        return responsePreparatoryService.prepareResponseForTextMessage(msg);
    }
}
