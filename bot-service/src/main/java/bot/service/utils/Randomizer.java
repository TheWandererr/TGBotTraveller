package bot.service.utils;

import java.util.Random;

/**
 * @author Artyom Konashchenko
 * @since 05.04.2020
 */
public class Randomizer {

    private static final Random random = new Random();

    private Randomizer() {


    }

    public static int nextInt(int bound) {
        return random.nextInt(bound);
    }
}
