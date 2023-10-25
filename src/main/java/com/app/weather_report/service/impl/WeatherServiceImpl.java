package com.app.weather_report.service.impl;

import com.app.weather_report.entity.enums.DayOfWeek;
import com.app.weather_report.service.WeatherService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.app.weather_report.entity.WeatherInfo;
import com.app.weather_report.repository.WeatherRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    @Override
    public WeatherInfo getWeatherByDayOfWeek(String dayOfWeek) {
        DayOfWeek dayOfWeekEnum = DayOfWeek.valueOf(dayOfWeek.toUpperCase());
        WeatherInfo weatherInfo = weatherRepository.findFirstByDayOfWeekOrderByidAsc(dayOfWeekEnum);
        if (weatherInfo == null) {
            throw new EntityNotFoundException("Das Wetter für den angegebenen Wochentag wurde nicht gefunden");
        }
        return weatherInfo;
    }

    @Override
    public List<WeatherInfo> findNext7WeatherInfoByDayOfWeek(String dayOfWeek) {
        DayOfWeek dayOfWeekEnum = DayOfWeek.valueOf(dayOfWeek.toUpperCase());
        return weatherRepository.findNext7WeatherInfoByDayOfWeek(dayOfWeekEnum);
    }
}