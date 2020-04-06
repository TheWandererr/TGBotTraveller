package bot.service.validation;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */
public class ExceptionStringTemplates {
    private ExceptionStringTemplates() {

    }

    public static final String CITY_NOT_FOUND = "city not found";
    public static final String CITY_EXIST = "city already exists";
    public static final String CITY_PLACE_NOT_FOUND = "place does not exist in that city";
}
