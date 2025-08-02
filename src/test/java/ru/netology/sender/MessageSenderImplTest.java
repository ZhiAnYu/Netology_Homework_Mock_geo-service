package ru.netology.sender;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.Mockito;
import ru.netology.LocationArgumentsProvider;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {
    @BeforeAll
    static void initAll() {
        System.out.println("Начало тестирования\n");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("\n Конец теста.");
    }

    @ParameterizedTest
    @DisplayName("Проверка метода send")
    @ArgumentsSource(LocationArgumentsProvider.class )
    void testMessageSenderImpl_whenSend_thenResult(String ip, Location location, String string) {
        //arrange
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(location);

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(location.getCountry())).thenReturn(string);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        //act
        String result = messageSender.send(headers);
        String excpected = string;
        //assert
        Assertions.assertEquals(excpected,result);
    }
}