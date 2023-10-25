package com.app.weather_report.repository;

import com.app.weather_report.entity.WeatherInfo;
import com.app.weather_report.entity.enums.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherInfo, Long> {
    // Abfrage für das erste Wetterinfo-Objekt eines bestimmten Wochentags, sortiert nach ID in aufsteigender
    // Reihenfolge
    String FIRST_BY_DAY_OF_WEEK_QUERY = "SELECT w FROM WeatherInfo w WHERE w.dayOfWeek = :dayOfWeek ORDER BY w.id" +
            " ASC LIMIT 1";

    // Abfrage für die nächsten 7 Wetterinfo-Objekte desselben Wochentags, sortiert nach ID in aufsteigender
    // Reihenfolge
    String NEXT_7_BY_DAY_OF_WEEK_QUERY = "SELECT w FROM WeatherInfo w WHERE w.id > (SELECT MIN(w2.id) FROM " +
            "WeatherInfo w2 WHERE w2.dayOfWeek = :dayOfWeek) ORDER BY w.id ASC LIMIT 7";


    @Query(FIRST_BY_DAY_OF_WEEK_QUERY)
    WeatherInfo findFirstByDayOfWeekOrderByidAsc(@Param("dayOfWeek") DayOfWeek dayOfWeek);

    @Query(NEXT_7_BY_DAY_OF_WEEK_QUERY)
    List<WeatherInfo> findNext7WeatherInfoByDayOfWeek(@Param("dayOfWeek") DayOfWeek dayOfWeek);
}