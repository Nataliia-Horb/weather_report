package com.app.weather_report.util;

import com.app.weather_report.entity.WeatherInfo;
import com.app.weather_report.entity.enums.DayOfWeek;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;


@UtilityClass
public class WeatherForecastCreator {

    public static WeatherInfo createWeatherForecast(DayOfWeek dayOfWeek) {
        WeatherInfo weatherInfo = new WeatherInfo();

        switch (dayOfWeek) {
            case MONTAG:
                weatherInfo.setTemperature("+16 C");
                weatherInfo.setWind("leichter Wind aus Südwest (10 km/h | 2 Bft)");
                weatherInfo.setPrecipitation("40%");
                break;
            case DIENSTAG:
                weatherInfo.setTemperature("+15 C");
                weatherInfo.setWind("schwächerer Wind aus Südwest (15 km/h | 3 Bft)");
                weatherInfo.setPrecipitation("10%");
                break;
            case MITTWOCH:
                weatherInfo.setTemperature("+14 C");
                weatherInfo.setWind("leichter Wind aus Südwest (5 km/h | 2 Bft)");
                weatherInfo.setPrecipitation("20%");
                break;
            case DONNERSTAG:
                weatherInfo.setTemperature("+13 C");
                weatherInfo.setWind("leichter Wind aus Südwest (10 km/h | 2-3 Bft)");
                weatherInfo.setPrecipitation("90% | 2-5 mm | 2 Std");
                break;
            case FREITAG:
                weatherInfo.setTemperature("+12 C");
                weatherInfo.setWind("leichter Wind aus Südwest (10 km/h | 2-3 Bft)");
                weatherInfo.setPrecipitation("40%");
                break;
            case SAMSTAG:
                weatherInfo.setTemperature("+11 C");
                weatherInfo.setWind("schwächerer Wind aus Südwest (15 km/h | 3 Bft)");
                weatherInfo.setPrecipitation("80% | 2-5 mm | 2 Std");
                break;
            case SONNTAG:
                weatherInfo.setTemperature("+10 C");
                weatherInfo.setWind("leichter Wind aus Südwest (10 km/h | 2 Bft)");
                weatherInfo.setPrecipitation("35%");
                break;
            default:
                // Nicht unterstützten Wochentag behandeln
                throw new IllegalArgumentException("Ungültiger Wochentag");
        }

        return weatherInfo;
    }

    public static List<WeatherInfo> createWeatherForecastList() {
        List<WeatherInfo> weatherInfoList = new ArrayList<>();

        // Wettervorhersagen für jeden Wochentag erstellen und zur Liste hinzufügen
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            WeatherInfo weatherInfo = createWeatherForecast(dayOfWeek);
            weatherInfoList.add(weatherInfo);
        }

        return weatherInfoList;
    }
}