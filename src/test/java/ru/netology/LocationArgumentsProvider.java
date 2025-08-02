package ru.netology;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

public class LocationArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null,
                        null, null, 0), "Welcome"),
                Arguments.of("172.0.32.11", new Location("Moscow",
                        Country.RUSSIA, "Lenina", 15), "Добро пожаловать"),
                Arguments.of("96.44.183.149", new Location("New York",
                        Country.USA, " 10th Avenue", 32), "Welcome"),
                Arguments.of("172.0.0.0", new Location("Moscow",
                        Country.RUSSIA, null, 0), "Добро пожаловать"),
                Arguments.of("96.0.0.0", new Location("New York",
                        Country.USA, null, 0), "Welcome")
        );
    }
}