package com.app.weather_report.service;


import com.app.weather_report.entity.WeatherInfo;

import java.util.List;

public interface WeatherService {
 WeatherInfo getWeatherByDayOfWeek(String  dayOfWeek);

 List<WeatherInfo> findNext7WeatherInfoByDayOfWeek(String  dayOfWeek);
}