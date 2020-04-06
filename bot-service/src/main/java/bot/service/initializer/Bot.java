package bot.service.initializer;

import bot.service.initializer.thread.ThreadInitializer;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.PostConstruct;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */
@Component
@Getter
@PropertySource("classpath:bot.properties")
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private ThreadInitializer threadInitializer;

    private final Queue<Object> sendData = new LinkedBlockingQueue<>();
    private final Queue<Object> receiveData = new LinkedBlockingQueue<>();

    @Value("${bot.token}")
    private String botToken;

    @Value("${bot.username}")
    private String botUsername;

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        receiveData.add(update);
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @PostConstruct
    private void init() {
        threadInitializer.registerBot(this);
    }
}
