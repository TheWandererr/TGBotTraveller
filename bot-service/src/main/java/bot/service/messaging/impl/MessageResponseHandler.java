package bot.service.messaging.impl;

import bot.service.initializer.Bot;
import bot.service.messaging.IMessageResponseHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */

@Component
public class MessageResponseHandler implements IMessageResponseHandler {

    @Override
    public void sendResponse(Object o, Bot botInstance) {

        if (o instanceof SendMessage) {
            processSendingMessage((SendMessage) o, botInstance);
        } else if (o instanceof SendPhoto) {
            processSendingPhoto((SendPhoto) o, botInstance);
        }
    }

    private void processSendingMessage(SendMessage msg, Bot bot) {
        try {
            bot.execute(msg);
        } catch (TelegramApiException tae) {
            System.out.println("Error! " + tae.getMessage());
        }
    }

    private void processSendingPhoto(SendPhoto msg, Bot bot) {
        try {
            bot.execute(msg);
        } catch (TelegramApiException tae) {
            System.out.println("Error! " + tae.getMessage());
        }
    }
}
