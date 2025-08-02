package ru.netology.geo;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ru.netology.LocationArgumentsProvider;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GeoServiceImplTest {
    GeoService geoService;

    //    @Override
//    public boolean equals(Object object) {
//        //проверка на идентичность самому себе
//        if (this == object) {
//            return true;
//        }
//        //Защитить от ситуации с null и убедиться в совместимости типов
//        if (object == null || getClass() != object.getClass()) {
//            return false;
//        }
//        //Сравнить поля, предварительно произведя приведение типов:
//        Location other = (Location) object;
//        return builing == location.builing && Objects.equals(city, location.city) && country == location.country && Objects.equals(street, location.street);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(city, country, street, builing);
//    }
    @BeforeAll
    static void initAll() {
        System.out.println("Начало тестирования");
    }

    @BeforeEach
    void setUp() {
        geoService = new GeoServiceImpl();
    }


    @AfterAll
    static void tearDownAll() {
        System.out.println("Конец тестирования.");
    }

    @ParameterizedTest
    @DisplayName("Проверка метода byIp")
    @ArgumentsSource(LocationArgumentsProvider.class)
    void testGeoServiceImpl_whenByIp_returnResult(String ip, Location location, String string) {
        //arrange
        Location expected = location;
        //act
        Location result = geoService.byIp(ip);
        //assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Проверка метода byIp для ip - вне указанного диапазона")
    void testGeoServiceImpl_whenByIp_returnNull() {
        //arrange
        //act
        Location result = geoService.byIp("32.0.0.0");
        //assert
        Assertions.assertNull(result);
    }

    @Test
    @DisplayName("проверка на исключение в методе byCoordinates")
    void testGeoService_whenByCoordinates_thenException() {
        //arrange

        //assert
        assertThrows(RuntimeException.class, () -> geoService.byCoordinates(1.0, 1.0));
    }
}