package com.juaracoding.dikahadir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ManagementJabatanPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private DashboardPage dashboardPage;

    // ================== LOCATORS SPESIFIK UNTUK HALAMAN INI ==================
    public By headerTitle = By.xpath("//h4[contains(text(), 'Jabatan')]");
    public By tambahButton = By.xpath("//button[contains(., 'Tambah Jabatan')]");
    public By successMessage = By.xpath("//div[contains(@class, 'MuiAlert-message')]");

    // ================== CONSTRUCTOR ==================
    public ManagementJabatanPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.dashboardPage = new DashboardPage();
    }

    // ================== PAGE ACTIONS / METHODS ==================
    public String getHeaderTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerTitle)).getText();
    }

    public void inputSearch(String keyword) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardPage.searchBar));
        searchInput.clear();
        searchInput.sendKeys(keyword);
    }

    public void clickSearchButton() {
        driver.findElement(dashboardPage.searchButton).click();
    }

    public void clickTambahButton() {
        wait.until(ExpectedConditions.elementToBeClickable(tambahButton)).click();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }
}