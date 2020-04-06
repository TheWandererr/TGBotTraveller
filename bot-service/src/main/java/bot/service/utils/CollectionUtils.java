package bot.service.utils;


import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Artyom Konashchenko
 * @since 04.04.2020
 */
public class CollectionUtils {

    private CollectionUtils() {

    }

    public static <T, V> List<V> map(Collection<T> collection, Function<T, V> function) {
        return safeStream(collection)
                .map(function)
                .collect(Collectors.toList());
    }

    public static <T, U, V> List<V> map(Collection<T> collection, BiFunction<T, U, V> biFunction, U functionParameter) {
        return safeStream(collection)
                .map(el -> biFunction.apply(el, functionParameter))
                .collect(Collectors.toList());
    }

    public static <T, V> List<V> map(Iterable<T> collection, Function<T, V> function) {
        return safeStream(collection)
                .map(function)
                .collect(Collectors.toList());
    }

    public static <T> Stream<T> safeStream(Collection<T> collection) {
        return Optional.ofNullable(collection).stream().filter(Objects::nonNull).flatMap(Collection::stream);
    }

    public static <T> Stream<T> safeStream(Iterable<T> iterable) {
        Optional<Iterable<T>> o = Optional.ofNullable(iterable);
        if (o.isPresent()) {
            return StreamSupport.stream(iterable.spliterator(), false);
        }
        return Stream.empty();
    }

    public static <T> List<T> listFrom(Iterable<T> iterable) {
        return safeStream(iterable).collect(Collectors.toList());
    }

    @SafeVarargs
    public static <T> Object[] safeArrayFromVarArgs(T... args) {
        return Stream.of(args).filter(Objects::nonNull).toArray();
    }

    @SafeVarargs
    public static <T> Object[] safeArrayFromVarArgsAndFilter(Predicate<T> predicate, T... args) {
        return Stream.of(args).filter(Objects::nonNull).filter(predicate).toArray();
    }

    /*public static <T> Collection<T> deepClone(Collection<T> collection) {
        List<T> out = new ArrayList<>();
        return (Collection<T>)out;
    }*/
}
