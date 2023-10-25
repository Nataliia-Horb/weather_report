package com.app.weather_report.service.impl;

import com.app.weather_report.entity.WeatherInfo;
import com.app.weather_report.repository.WeatherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.app.weather_report.util.WeatherForecastCreator;
import com.app.weather_report.entity.enums.DayOfWeek;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test class for WeatherServiceImpl")
@ExtendWith(MockitoExtension.class)
class WeatherServiceImplTest {

    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private WeatherServiceImpl weatherService;


    @Test
    void testGetWeatherByDayOfWeek() {

        // Nachahmung des Repository-Verhaltens einrichten
        for (DayOfWeek day : DayOfWeek.values()) {
            Mockito.when(weatherRepository.findFirstByDayOfWeekOrderByidAsc(day)).
                    thenReturn(WeatherForecastCreator.createWeatherForecast(day));
        }

        // Erster Fall: MONTAG
        WeatherInfo montagInfo = weatherService.getWeatherByDayOfWeek("MONTAG");
        assertEquals("+16 C", montagInfo.getTemperature());
        assertEquals("leichter Wind aus Südwest (10 km/h | 2 Bft)", montagInfo.getWind());
        assertEquals("40%", montagInfo.getPrecipitation());

        // Zweiter Fall: DIENSTAG
        WeatherInfo dienstagInfo = weatherService.getWeatherByDayOfWeek("DIENSTAG");
        assertEquals("+15 C", dienstagInfo.getTemperature());
        assertEquals("schwächerer Wind aus Südwest (15 km/h | 3 Bft)", dienstagInfo.getWind());
        assertEquals("10%", dienstagInfo.getPrecipitation());

        // Dritter Fall: MITTWOCH
        WeatherInfo mittwochInfo = weatherService.getWeatherByDayOfWeek("MITTWOCH");
        assertEquals("+14 C", mittwochInfo.getTemperature());
        assertEquals("leichter Wind aus Südwest (5 km/h | 2 Bft)", mittwochInfo.getWind());
        assertEquals("20%", mittwochInfo.getPrecipitation());

        // Vierter Fall: DONNERSTAG
        WeatherInfo donnerstagInfo = weatherService.getWeatherByDayOfWeek("DONNERSTAG");
        assertEquals("+13 C", donnerstagInfo.getTemperature());
        assertEquals("leichter Wind aus Südwest (10 km/h | 2-3 Bft)", donnerstagInfo.getWind());
        assertEquals("90% | 2-5 mm | 2 Std", donnerstagInfo.getPrecipitation());

        // Fünfter Fall: FREITAG
        WeatherInfo freitagInfo = weatherService.getWeatherByDayOfWeek("FREITAG");
        assertEquals("+12 C", freitagInfo.getTemperature());
        assertEquals("leichter Wind aus Südwest (10 km/h | 2-3 Bft)", freitagInfo.getWind());
        assertEquals("40%", freitagInfo.getPrecipitation());

        // Sechster Fall: SAMSTAG
        WeatherInfo samstagInfo = weatherService.getWeatherByDayOfWeek("SAMSTAG");
        assertEquals("+11 C", samstagInfo.getTemperature());
        assertEquals("schwächerer Wind aus Südwest (15 km/h | 3 Bft)", samstagInfo.getWind());
        assertEquals("80% | 2-5 mm | 2 Std", samstagInfo.getPrecipitation());

        // Siebter Fall: SONNTAG
        WeatherInfo sonntagInfo = weatherService.getWeatherByDayOfWeek("SONNTAG");
        assertEquals("+10 C", sonntagInfo.getTemperature());
        assertEquals("leichter Wind aus Südwest (10 km/h | 2 Bft)", sonntagInfo.getWind());
        assertEquals("35%", sonntagInfo.getPrecipitation());
    }

    @Test
    void testFindNext7WeatherInfoByDayOfWeek() {
        // Einen Stub für das Repository erstellen, der Daten für den Test zurückgibt
        DayOfWeek dayOfWeek = DayOfWeek.SONNTAG;
        List<WeatherInfo> mockWeatherInfoList = WeatherForecastCreator.createWeatherForecastList();


        // Richten Sie das Verhalten des Mocks für die Methode findNext7WeatherInfoByDayOfWeek ein
        Mockito.when(weatherRepository.findNext7WeatherInfoByDayOfWeek(dayOfWeek)).thenReturn(mockWeatherInfoList);

        List<WeatherInfo> result = weatherService.findNext7WeatherInfoByDayOfWeek(dayOfWeek.toString());

        assertEquals(mockWeatherInfoList, result);
    }


    @Test
    void testGetWeatherByDayOfWeekNotFound() {
        // Konfigurieren des Repository-Verhaltens, um null zurückzugeben
        Mockito.when(weatherRepository.findFirstByDayOfWeekOrderByidAsc(DayOfWeek.MONTAG)).thenReturn(null);

        // Rufen Sie die Methode auf, die wir testen, und erwarten eine EntityNotFoundException-Ausnahme
        assertThrows(EntityNotFoundException.class, () -> weatherService.getWeatherByDayOfWeek("MONTAG"));
    }
}