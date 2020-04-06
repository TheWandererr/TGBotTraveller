package bot.domain;

import java.util.EnumSet;

/**
 * @author Artyom Konashchenko
 * @since 05.04.2020
 */
public interface Enumerated {

    default <T extends Enum<T>> T safeDefine(String cmd, Class<T> enumClass) {
        if (cmd == null) return null;
        String cmdUpper = cmd.toUpperCase();
        for (T elem : EnumSet.allOf(enumClass)) {
            if (elem.name().contentEquals(cmdUpper)) {
                return elem;
            }
        }
        return null;
    }
}
