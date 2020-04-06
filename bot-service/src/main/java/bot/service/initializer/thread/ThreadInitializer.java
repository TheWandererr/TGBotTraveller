package bot.service.initializer.thread;

import bot.service.initializer.Bot;
import bot.service.messaging.IMessageReceiverThreadService;
import bot.service.messaging.IMessageSenderThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */

@Component
public class ThreadInitializer {

    @Autowired
    private IMessageReceiverThreadService messageReceiverThreadService;

    @Autowired
    private IMessageSenderThreadService messageSenderThreadService;

    private static final int SENDER_PRIORITY = 1;
    private static final String SENDER_NAME = "MessageSenderThread";
    private static final int RECEIVER_PRIORITY = 2;
    private static final String RECEIVER_NAME = "MessageReceiverThread";

    public void registerBot(Bot bot) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(bot);
            initMessagingThreads();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void initMessagingThreads() {
        startThread(messageReceiverThreadService, true, RECEIVER_NAME, RECEIVER_PRIORITY);
        startThread(messageSenderThreadService, true, SENDER_NAME, SENDER_PRIORITY);
    }

    public void startThread(Runnable target, boolean daemon, String name, int priority) {
        Thread thread = new Thread(target);
        thread.setDaemon(daemon);
        thread.setName(name);
        thread.setPriority(priority);
        thread.start();
    }
}
