package com.app.weather_report;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({"com.app.weather_report.controller", "com.app.weather_report.service"})
public class TestSuite {
}