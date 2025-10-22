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

    // ================== LOCATORS KHUSUS HALAMAN JABATAN ==================
    public By headerTitle = By.xpath("//h4[contains(text(),'Jabatan')]");
    public By inputNamaJabatan = By.id("position_name");
    public By inputLevelJabatan = By.id("level");
    public By buttonTambah = By.xpath("//button[normalize-space()='Tambahkan']");
    public By buttonSimpan = By.xpath("//button[normalize-space()='Simpan']");
    public By successMessage = By.xpath("//div[contains(@class, 'MuiAlert-message')]");
    public By confirmDelete = By.xpath("//button[normalize-space()='Hapus']");

    // ================== CONSTRUCTOR ==================
    public ManagementJabatanPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.dashboardPage = new DashboardPage(driver);
    }

    // ================== ACTION METHODS ==================

    public void waitUntilPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(headerTitle));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardPage.tambahButton));
    }

    public String getHeaderTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerTitle)).getText();
    }

    public void clickTambahButton() {
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.tambahButton)).click();
    }

    public void inputNamaJabatan(String nama) {
        WebElement namaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(inputNamaJabatan));
        namaInput.clear();
        namaInput.sendKeys(nama);
    }

    public void inputLevelJabatan(String level) {
        WebElement levelInput = wait.until(ExpectedConditions.visibilityOfElementLocated(inputLevelJabatan));
        levelInput.clear();
        levelInput.sendKeys(level);
    }

    public void clickSimpanButton() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonSimpan)).click();
    }

    public void clickEditButtonByName(String jabatanName) {
        By actionButton = By.xpath(
            String.format("//h6[text()='%s']/ancestor::tr//button[@aria-label='action']", jabatanName)
        );
        wait.until(ExpectedConditions.elementToBeClickable(actionButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.actionEdit)).click();
    }

    public void clickDeleteButtonByName(String jabatanName) {
        By actionButton = By.xpath(
            String.format("//h6[text()='%s']/ancestor::tr//button[@aria-label='action']", jabatanName)
        );
        wait.until(ExpectedConditions.elementToBeClickable(actionButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.actionDelete)).click();
    }

    public void confirmDelete() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmDelete)).click();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }

    public void clickResetButton() {
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.resetButton)).click();
    }

    public void clickNextPage() {
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.nextPageButton)).click();
    }
}
