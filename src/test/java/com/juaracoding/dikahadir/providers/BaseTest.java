package com.juaracoding.dikahadir.providers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest {
    protected WebDriver driver;
    protected static ExtentReports extent;
    private static final String REPORT_PATH = "src/test/resources/reports/";

    protected WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void setUpSuite() {
        // Create directories if they don't exist
        new File(REPORT_PATH).mkdirs();

        // Initialize ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH + "ExtentReport.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("TAJuaraCoding Test Report - Hadir Application");
        sparkReporter.config().setReportName("Selenium Automation Test Results");
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Hadir - Magang System");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
        extent.setSystemInfo("Base URL", "https://magang.dikahadir.com");
    }

    @BeforeMethod
    @Parameters("baseURL")
    public void setDriver(String baseURL) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
