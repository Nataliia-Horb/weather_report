package com.app.weather_report.controller;

import com.app.weather_report.entity.WeatherInfo;
import com.app.weather_report.entity.enums.DayOfWeek;
import org.springframework.ui.Model;
import com.app.weather_report.service.impl.WeatherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherServiceImpl weatherService;

    @GetMapping
    public String weatherForm() {
        return "weather";
    }

    @GetMapping("/getWeather")
    public String getWeatherByDayOfWeek(@RequestParam("dayOfWeek") String dayOfWeek, Model model) {
        WeatherInfo weatherInfo = weatherService.getWeatherByDayOfWeek(dayOfWeek);
        List<WeatherInfo> weeklyWeather = weatherService.findNext7WeatherInfoByDayOfWeek(dayOfWeek);

        if (weatherInfo == null) {
            // Wenn die Prognose nicht gefunden wird, können Sie eine Fehlermeldung senden
            model.addAttribute("error", "Die Wettervorhersage wurde nicht gefunden");
        } else {
            // Füge den ausgewählten Wochentag zum Modell hinzu
            model.addAttribute("selectedDayOfWeek", dayOfWeek);
            // Füge Wetterinformationen zum Modell hinzu
            model.addAttribute("weatherInfo", weatherInfo);
            model.addAttribute("weeklyWeather", weeklyWeather);
        }
        return "weather";
    }

    @ModelAttribute("daysOfWeek")
    public List<String> populateDaysOfWeek() {
        DayOfWeek[] days = DayOfWeek.values();
        List<String> daysList = new ArrayList<>();
        for (int i = 0; i < days.length; i++) {
            daysList.add(days[i].name());
        }
        return daysList;
    }
}