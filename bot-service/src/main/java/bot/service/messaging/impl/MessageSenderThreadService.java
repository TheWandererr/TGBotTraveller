package bot.service.messaging.impl;

import bot.service.initializer.Bot;
import bot.service.messaging.IMessageResponseHandler;
import bot.service.messaging.IMessageSenderThreadService;
import bot.service.messaging.AbstractMessageThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */
@Service
public class MessageSenderThreadService extends AbstractMessageThreadService implements IMessageSenderThreadService {

    @Autowired
    private Bot botInstance;

    @Autowired
    private IMessageResponseHandler messageResponseHandler;

    @Override
    public void run() {
        Queue<Object> sendData = botInstance.getSendData();
        runWith(sendData);
    }

    public void process(Object toSend) {
        messageResponseHandler.sendResponse(toSend, botInstance);
    }
}
