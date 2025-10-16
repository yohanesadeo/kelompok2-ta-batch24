package com.juaracoding.dikahadir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ManagementPosisiPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private DashboardPage dashboardPage;

    // ================== LOCATORS SPESIFIK UNTUK HALAMAN INI ==================
    public By headerTitle = By.xpath("//p[normalize-space()='Posisi']");
    public By searchBar = By.id("search"); // Menggunakan ID lebih stabil
    public By successMessage = By.xpath("//div[contains(@class, 'MuiAlert-message')]");

    // ================== CONSTRUCTOR ==================
    public ManagementPosisiPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.dashboardPage = new DashboardPage();
    }

    // ================== PAGE ACTIONS / METHODS ==================
    public String getHeaderTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerTitle)).getText();
    }

    public void inputSearch(String keyword) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        searchInput.clear();
        searchInput.sendKeys(keyword);
    }

    public void clickSearchButton() {
        driver.findElement(dashboardPage.searchButton).click();
    }

    public void clickTambahButton() {
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.tambahButton)).click();
    }

    private void clickActionMenuByName(String positionName) {
        By actionButton = By
                .xpath(String.format("//h6[text()='%s']/ancestor::tr//button[@aria-label='action']", positionName));
        wait.until(ExpectedConditions.elementToBeClickable(actionButton)).click();
    }

    public void clickEditButtonByName(String positionName) {
        clickActionMenuByName(positionName);
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.actionEdit)).click();
    }

    public void clickDeleteButtonByName(String positionName) {
        clickActionMenuByName(positionName);
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.actionDelete)).click();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }
}