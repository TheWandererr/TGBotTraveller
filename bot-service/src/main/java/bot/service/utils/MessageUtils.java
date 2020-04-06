package bot.service.utils;

import bot.domain.content.MessageType;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;

import static bot.domain.content.MessageType.TEXT;
import static bot.domain.content.MessageType.UNDEFINED;
import static bot.service.utils.CollectionUtils.safeArrayFromVarArgs;

/**
 * @author Artyom Konashchenko
 * @since 05.04.2020
 */
public class MessageUtils {

    private MessageUtils() {

    }

    public static SendMessage createSendMessage(Message msg, String template, String... params) {
        return new SendMessage(msg.getChatId(),
                String.format(template, safeArrayFromVarArgs(params)));
    }

    public static SendPhoto createCaptionPhotoMessage(Message msg, String caption, String url, String... params) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setCaption(String.format(caption, safeArrayFromVarArgs(params)));
        sendPhoto.setChatId(msg.getChatId());
        sendPhoto.setPhoto(url);
        return sendPhoto;
    }

    public static MessageType defineMessageType(Message income) {
        if (income.getText() != null) {
            return TEXT;
        }
        return UNDEFINED;
    }

}
