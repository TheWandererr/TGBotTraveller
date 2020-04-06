package bot.service.utils;

import java.util.Arrays;

import static bot.service.utils.CollectionUtils.safeArrayFromVarArgsAndFilter;

/**
 * @author Artyom Konashchenko
 * @since 05.04.2020
 */
public class StringUtils {

    private StringUtils() {

    }

    public static String[] safeArrayFrom(final String str, String regex) {
        Object[] arr = safeArrayFromVarArgsAndFilter(org.apache.commons.lang3.StringUtils::isNotEmpty, str.split(regex));
        return Arrays.copyOf(arr, arr.length, String[].class);
    }
}
