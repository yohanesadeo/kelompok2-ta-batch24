package com.juaracoding.dikahadir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ManagementJadwalPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private DashboardPage dashboardPage;

    // ================== LOCATORS SPESIFIK UNTUK HALAMAN INI ==================
    public By headerTitle = By.xpath("//h4[contains(text(), 'Jadwal')]");
    public By tambahButton = By.xpath("//button[contains(., 'Tambah Jadwal')]");

    // ================== CONSTRUCTOR ==================
    public ManagementJadwalPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.dashboardPage = new DashboardPage();
    }

    // ================== PAGE ACTIONS / METHODS ==================
    public String getHeaderTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerTitle)).getText();
    }

    public void clickTambahButton() {
        wait.until(ExpectedConditions.elementToBeClickable(tambahButton)).click();
    }
}