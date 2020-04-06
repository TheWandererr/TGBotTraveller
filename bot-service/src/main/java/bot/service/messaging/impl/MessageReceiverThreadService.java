package bot.service.messaging.impl;

import bot.service.initializer.Bot;
import bot.service.messaging.IMessageReceiveHandler;
import bot.service.messaging.IMessageReceiverThreadService;
import bot.service.messaging.AbstractMessageThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */

@Service
public class MessageReceiverThreadService extends AbstractMessageThreadService implements IMessageReceiverThreadService {

    @Autowired
    private Bot botInstance;

    @Autowired
    private IMessageReceiveHandler messageReceiveHandler;


    @Override
    public void run() {
        Queue<Object> receiveData = botInstance.getReceiveData();
        runWith(receiveData);
    }

    public void process(Object received) {
        messageReceiveHandler.process(received, botInstance);
    }
}