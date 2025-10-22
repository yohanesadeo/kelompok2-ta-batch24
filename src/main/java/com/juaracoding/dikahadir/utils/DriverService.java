package com.juaracoding.dikahadir.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverService {
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";

    private static WebDriver driver; 

    public WebDriver getDriverChrome() {
        return new ChromeDriver();
    }

    public WebDriver getDriverFirefox() {
        return new FirefoxDriver();
    }

    public static WebDriver driverManager(String driverType) {
        DriverService ds = new DriverService();

        if (driverType.equalsIgnoreCase(CHROME)) {
            driver = ds.getDriverChrome(); 
        } else {
            driver = ds.getDriverFirefox();
        }

        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
