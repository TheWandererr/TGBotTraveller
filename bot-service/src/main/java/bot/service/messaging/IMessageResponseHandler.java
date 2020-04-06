package bot.service.messaging;


import bot.service.initializer.Bot;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */
public interface IMessageResponseHandler {
    void sendResponse(Object o, Bot botInstance);
}
