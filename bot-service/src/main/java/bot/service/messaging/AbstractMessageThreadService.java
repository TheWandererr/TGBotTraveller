package bot.service.messaging;

import java.util.Queue;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */
public abstract class AbstractMessageThreadService {

    protected void runWith(Queue<Object> data) {
        while (true) {
            for (Object o = data.poll(); o != null; o = data.poll()) {
                process(o);
            }
        }
    }

    protected abstract void process(Object o);
}
