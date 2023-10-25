package com.app.weather_report.entity;

import com.app.weather_report.entity.enums.DayOfWeek;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "weather_info")
@NoArgsConstructor
@AllArgsConstructor
public class WeatherInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_of_week")
    @Enumerated(EnumType.ORDINAL)
    private DayOfWeek dayOfWeek;

    @Column(name = "temperature")
    private String temperature;

    @Column(name = "wind")
    private String wind;

    @Column(name = "precipitation")
    private String precipitation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherInfo that = (WeatherInfo) o;
        return Objects.equals(id, that.id) && dayOfWeek == that.dayOfWeek && Objects.equals(temperature,
                that.temperature) && Objects.equals(wind, that.wind) && Objects.equals(precipitation,
                that.precipitation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dayOfWeek, temperature, wind, precipitation);
    }
}