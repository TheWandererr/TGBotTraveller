package bot.service.messaging.impl;

import bot.service.messaging.IResponseCreatingService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;

import static bot.service.utils.MessageUtils.createCaptionPhotoMessage;
import static bot.service.utils.MessageUtils.createSendMessage;

/**
 * @author Artyom Konashchenko
 * @since 06.04.2020
 */
@Service
public class ResponseCreatingService implements IResponseCreatingService {
    @Override
    public SendMessage createSendMessageResponse(Message msg, String template, String... params) {
        return createSendMessage(msg, template, params);
    }

    @Override
    public SendPhoto createSendPhotoResponse(Message msg, String url, String caption, String... params) {
        return createCaptionPhotoMessage(msg, caption, url, params);
    }
}
