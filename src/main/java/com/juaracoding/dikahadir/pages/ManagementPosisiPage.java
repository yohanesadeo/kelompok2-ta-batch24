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
    public By searchBar = By.xpath("//input[@id='search']");
    public By searchButton = By.xpath("//button[@type='submit']");
    public By tambahkanButton = By.xpath("//button[@type='button' and normalize-space(text())='Tambahkan']");
    public By inputNamaPosisi = By.xpath("//input[@id='name' and @name='name' and @placeholder='Nama Position']");
    public By listbox = By.xpath("//div[@role='combobox' and @aria-haspopup='listbox']");
    public By optionListbox = By.xpath("//li[@role='option' and normalize-space(text())='Sales']");
    public By successMessage = By.xpath("//div[contains(@class, 'MuiAlert-message')]");

    // ================== CONSTRUCTOR ==================
    public ManagementPosisiPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.dashboardPage = new DashboardPage(driver);
    }

    // ================== PAGE ACTIONS / METHODS ==================

    // Mendapatkan judul header halaman
    public String getHeaderTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerTitle)).getText();
    }

    // Input pencarian posisi berdasarkan keyword
    public void inputSearch(String keyword) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        searchInput.clear();
        searchInput.sendKeys(keyword);
    }

    // Klik tombol search
    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    // Klik tombol "Tambahkan"
    public void clickTambahButton() {
        wait.until(ExpectedConditions.elementToBeClickable(tambahkanButton)).click();
    }

    // Input nama posisi baru
    public void inputNamaPosisi(String namaPosisi) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(inputNamaPosisi));
        input.clear();
        input.sendKeys(namaPosisi);
    }

    // Klik dropdown combobox (listbox)
    public void clickListbox() {
        wait.until(ExpectedConditions.elementToBeClickable(listbox)).click();
    }

    // Pilih salah satu opsi dalam listbox (misalnya "Sales")
    public void selectOptionListbox() {
        wait.until(ExpectedConditions.elementToBeClickable(optionListbox)).click();
    }

    // Klik tombol aksi (titik tiga) berdasarkan nama posisi tertentu
    private void clickActionMenuByName(String positionName) {
        By actionButton = By.xpath(
                String.format("//h6[text()='%s']/ancestor::tr//button[@aria-label='action']", positionName)
        );
        wait.until(ExpectedConditions.elementToBeClickable(actionButton)).click();
    }

    // Klik tombol edit pada posisi tertentu
    public void clickEditButtonByName(String positionName) {
        clickActionMenuByName(positionName);
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.actionEdit)).click();
    }

    // Klik tombol delete pada posisi tertentu
    public void clickDeleteButtonByName(String positionName) {
        clickActionMenuByName(positionName);
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.actionDelete)).click();
    }

    // Mendapatkan pesan sukses (berhasil tambah/edit/delete)
    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }
}
