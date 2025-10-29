package com.juaracoding.dikahadir.utils;

import org.openqa.selenium.WebDriver;

public class DriverUtil {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            DriverService ds = new DriverService();
            driver = ds.getDriver();
        }

        return driver;
    }

    public static void openDriver() {
        getDriver();
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
