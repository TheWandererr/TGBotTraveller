package bot.service.messaging;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */
public class MessageTemplates {
    private MessageTemplates() {

    }

    public static final String ERROR_PARSING_COMMAND = "Ууупс, %s, что-то не так с твоей командой, давай-ка еще раз!";
    public static final String HELP_COMMAND_TEXT = "Пиши названия городов и посмотри что получится!";
    public static final String START_COMMAND_TEXT = "Привет, я туристический телеграм бот. Давай начнём!";
    public static final String INCORRECT_COMMAND_TEXT = "Хмм, такой команды нет";

    public static final String IMPOSSIBLE_ACTION = "Ой, я тебя не понял";

    public static final String CITY_NOT_FOUND = "%s, я не смог найти такой город, попробуй другой";
    public static final String ERROR_PARSING_CITY = "У меня не вышло разобрать город. Не издевайся!";

    public static final String EMPTY_MESSAGE = "Пустые сообщения мне не понять";
    public static final String TOO_LONG_MESSAGE = "Слишком много текста, я запутался!";

    public static final List<String> cityShortAnswer;
    public static final List<String> cityMediumAnswer;
    public static final List<String> cityLongAnswer;

    public static final String CITY_WITHOUT_PLACES_CAPTION = "Про интересные места этого города я не знаю. Но вот что тебе расскажу," +
            " население этого места - %s человек, а площадь %s кв. м. Запоминай!";

    static {
        cityShortAnswer = new ArrayList<>();
        cityShortAnswer.add("Прекрасный город, обязательно посетите \"%s\". %s");
        cityShortAnswer.add("Ты там можешь найти \"%s\". Люди считают, что это невероятно красивое место. Лично от себя добавлю: %s");

        cityMediumAnswer = new ArrayList<>();
        cityMediumAnswer.add("Я тебе посоветую посетить \"%s\". %s, я считаю. Если ты там еще не был - вперед! Еще людям нравится \"%s\". Говорят, что это %s, но я лично туда б ни ногой :)");
        cityMediumAnswer.add("\"%s\" - такое сказочное место!!! %s. К тому же сгоняй в \"%s\", %s");

        cityLongAnswer = new ArrayList<>();
        cityLongAnswer.add("\"%s\" - мечта каждого. Я б на твоем месте уже собирал вещи, потому что там %s. " +
                "А еще тут есть \"%s\", говорят, что там %s. Ах да, чуть не забыл - \"%s\". Сюда не вздумай идти! Однако это мое мнение, вот что говорят люди: \"%s\"");
    }
}
