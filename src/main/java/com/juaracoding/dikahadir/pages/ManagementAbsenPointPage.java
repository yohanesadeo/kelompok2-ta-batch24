package com.juaracoding.dikahadir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ManagementAbsenPointPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private DashboardPage dashboardPage;

    // Header Halaman
    private By headerTitle = By.xpath("//h4[contains(text(), 'Absen Point')]");

    // Elemen Aksi Utama
    private By searchBar; // Akan diinisialisasi melalui dashboardPage
    private By searchButton; // Akan diinisialisasi melalui dashboardPage
    private By tambahButton = By.xpath("//button[contains(., 'Tambah Absen Point')]");

    // Kontrol Halaman (Pagination)
    private By nextPageButton; // Akan diinisialisasi melalui dashboardPage
    private By previousPageButton; // Akan diinisialisasi melalui dashboardPage

    // Notifikasi/Alert
    private By successMessage = By.xpath("//div[contains(@class, 'MuiAlert-message')]");

    public ManagementAbsenPointPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.dashboardPage = new DashboardPage(driver);
        // Inisialisasi locator yang bergantung pada dashboardPage
        this.searchBar = dashboardPage.searchBar;
        this.searchButton = dashboardPage.searchButton;
        this.nextPageButton = dashboardPage.nextPageButton;
        this.previousPageButton = dashboardPage.previousPageButton;
    }

    public String getHeaderTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerTitle)).getText();
    }

    public void inputSearch(String keyword) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        searchInput.clear();
        searchInput.sendKeys(keyword);
    }

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void clickTambahButton() {
        wait.until(ExpectedConditions.elementToBeClickable(tambahButton)).click();
    }

    public void clickEditButtonByName(String absenPointName) {
        By editButton = By.xpath(
                String.format("//td[text()='%s']/following-sibling::td//button[@aria-label='edit']", absenPointName));
        wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
    }

    public void clickDeleteButtonByName(String absenPointName) {
        By deleteButton = By.xpath(
                String.format("//td[text()='%s']/following-sibling::td//button[@aria-label='delete']", absenPointName));
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
    }

    public boolean isAbsenPointVisible(String absenPointName) {
        try {
            By absenPointCell = By.xpath(String.format("//td[text()='%s']", absenPointName));
            wait.until(ExpectedConditions.visibilityOfElementLocated(absenPointCell));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }

    public void clickNextPage() {
        wait.until(ExpectedConditions.elementToBeClickable(nextPageButton)).click();
    }

    public void clickPreviousPage() {
        wait.until(ExpectedConditions.elementToBeClickable(previousPageButton)).click();
    }
}
