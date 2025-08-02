package ru.netology.i18n;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;

class LocalizationServiceImplTest {

    @BeforeAll
    static void initAll() {
        System.out.println("Начало тестирования");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Конец.");
    }

    @ParameterizedTest
    @DisplayName("Проверка метода locale для RUSSIA")
    @EnumSource(value = Country.class, names = "RUSSIA")
    void testLocalizationService_whenLocaleRussia_thenAnswerRu(Country country) {
        //arrange
        LocalizationService localizationService = new LocalizationServiceImpl();
        String expected = "Добро пожаловать";
        //String expected2 = "Welcome";
        //act
        String result = localizationService.locale(country);
        //assert
        Assertions.assertEquals(expected, result);
        //Assertions.assertEquals(expected2,result);
    }

    @ParameterizedTest
    @DisplayName("Проверка метода locale для NOT RUSSIA")
    @EnumSource(value = Country.class, names = "RUSSIA", mode = EnumSource.Mode.EXCLUDE)
    void testLocalizationService_whenLocaleNotRussia_thenAnswerEng(Country country) {
        //arrange
        LocalizationService localizationService = new LocalizationServiceImpl();
        String expected2 = "Welcome";
        //act
        String result = localizationService.locale(country);
        //assert
        Assertions.assertEquals(expected2,result);
    }
}