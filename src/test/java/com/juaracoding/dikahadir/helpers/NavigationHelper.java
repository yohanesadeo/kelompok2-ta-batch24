package com.juaracoding.dikahadir.helpers;

import org.openqa.selenium.WebDriver;

import com.juaracoding.dikahadir.pages.DashboardPage;
import com.juaracoding.dikahadir.pages.LogInPage;
import com.juaracoding.dikahadir.pages.ManagementShiftingPage;

public class NavigationHelper {
    private WebDriver driver;

    // Default credential (biar reusable dan ga duplikat di step definition)
    private final String DEFAULT_EMAIL = "admin@hadir.com";
    private final String DEFAULT_PASSWORD = "MagangSQA_JC@123";

    public NavigationHelper(WebDriver driver) {
        this.driver = driver;
    }


    /**
     * Flow login dengan default credential lalu masuk ke halaman Shifting
     */
    public ManagementShiftingPage loginAndGoToShifting() {
        // buka halaman login
        driver.get("https://magang.dikahadir.com/authentication/login");

        // login pakai default credential
        LogInPage loginPage = new LogInPage(driver);
        loginPage.login(DEFAULT_EMAIL, DEFAULT_PASSWORD);

        // akses dashboard
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickButtonMenuManagement();

        // masuk ke submenu Shifting
        ManagementShiftingPage shiftingPage = new ManagementShiftingPage(driver);
        shiftingPage.clikSubMenuShifting();

        return shiftingPage;
    }
}