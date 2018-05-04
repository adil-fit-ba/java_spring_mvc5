package ba.fit.java.spring.mvc.helper;

import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MyCollectors {
    public static <T> Collector<T, ?, T> singleOrDefault() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() == 0)
                        return null;

                    if (list.size() > 1)
                        throw new IllegalStateException("Lista ne smije biti veća 1");


                    return list.get(0);
                }
        );
    }

    public static <T> Collector<T, ?, T> single() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() == 0)
                        throw new IllegalStateException("Lista ne smije biti prazna.");

                    if (list.size() > 1)
                        throw new IllegalStateException("Lista ne smije biti veća 1");

                    return list.get(0);
                }
        );
    }

    public static <T> Collector<T, ?, T> firstOrDefault() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() == 0)
                        return null;

                    return list.get(0);
                }
        );
    }

    public static <T> Collector<T, ?, T> first() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() == 0) {
                        throw new IllegalStateException("Lista ne smije biti prazna.");
                    }

                    return list.get(0);
                }
        );
    }
}
