package com.app.weather_report.entity.enums;

public enum DayOfWeek {
    MONTAG(0),
    DIENSTAG(1),
    MITTWOCH(2),
    DONNERSTAG(3),
    FREITAG(4),
    SAMSTAG(5),
    SONNTAG(6);
    private final int value;

    DayOfWeek(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}