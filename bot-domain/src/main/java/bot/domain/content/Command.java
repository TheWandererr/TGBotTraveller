package bot.domain.content;

import bot.domain.Enumerated;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */
public enum Command implements Enumerated {
    START,
    HELP,
    UNDEFINED;

    public static Map<String, Command> values;

    static {
        values = new HashMap<>();
        values.put("START", START);
        values.put("HELP", HELP);
    }


    public static Command safeDefine(String cmd) {
        if (!StringUtils.isBlank(cmd)) {
            String cmdUpper = cmd.toUpperCase();
            if (Command.values.containsKey(cmdUpper)) {
                return values.get(cmdUpper);
            }
        }
        return UNDEFINED;
    }
}
