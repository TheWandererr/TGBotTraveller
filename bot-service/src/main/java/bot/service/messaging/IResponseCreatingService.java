package bot.service.messaging;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Artyom Konashchenko
 * @since 06.04.2020
 */
public interface IResponseCreatingService {
    SendMessage createSendMessageResponse(Message msg, String template, String... params);

    SendPhoto createSendPhotoResponse(Message msg, String url, String caption, String... params);
}
