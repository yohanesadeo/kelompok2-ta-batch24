package com.juaracoding.dikahadir.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverService {
    private WebDriver driver;

    public DriverService() {
        // Create an object of Firefox Options class
        // FirefoxOptions options = new FirefoxOptions();
        // Set Firefox Headless mode as TRUE
        // options.addArguments("-headless");

        // Create an object of WebDriver class and pass the Firefox Options object
        // as an argument
        driver = new FirefoxDriver(); // masukan options jika ingin headless
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        driver.quit();
    }
}
