package com.app.weather_report.controller;

import com.app.weather_report.entity.WeatherInfo;
import com.app.weather_report.service.impl.WeatherServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@WebMvcTest(WeatherController.class)
@DisplayName("WeatherController test class")
class WeatherControllerTest {

    @Autowired
    WeatherController weatherController;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WeatherServiceImpl weatherService;

    @Test
    public void testWeatherForm() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("weather"));
    }

    @Test
    public void testGetWeatherByDayOfWeek() throws Exception {
        // Einen Stub für WeatherInfo erstellen
        String dayOfWeek = "SomeDay";
        WeatherInfo weatherInfo = new WeatherInfo();
        List<WeatherInfo> weeklyWeather = new ArrayList<>();

        when(weatherService.getWeatherByDayOfWeek(anyString())).thenReturn(weatherInfo);
        when(weatherService.findNext7WeatherInfoByDayOfWeek(anyString())).thenReturn(weeklyWeather);

        mvc.perform(get("/getWeather").param("dayOfWeek", dayOfWeek))
                .andExpect(status().isOk())
                .andExpect(view().name("weather"))
                .andExpect(model().attribute("selectedDayOfWeek", dayOfWeek))
                .andExpect(model().attribute("weatherInfo", weatherInfo))
                .andExpect(model().attribute("weeklyWeather", weeklyWeather));
    }

    @Test
    public void testGetWeatherByDayOfWeekNotFound() throws Exception {
        List<WeatherInfo> weeklyWeather = new ArrayList<>();
        when(weatherService.getWeatherByDayOfWeek(anyString())).thenReturn(null);
        when(weatherService.findNext7WeatherInfoByDayOfWeek(anyString())).thenReturn(weeklyWeather);

        String dayOfWeek = "SomeDay";
        mvc.perform(get("/getWeather").param("dayOfWeek", dayOfWeek))
                .andExpect(status().isOk())
                .andExpect(view().name("weather"))
                .andExpect(model().attribute("error", "Die Wettervorhersage wurde nicht gefunden"));
    }
}