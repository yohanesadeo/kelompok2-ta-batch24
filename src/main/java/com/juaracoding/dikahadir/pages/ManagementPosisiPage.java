package com.juaracoding.dikahadir.pages;

<<<<<<< HEAD
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
=======
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
>>>>>>> acbb1637a287b0ff342121fe2c8b809f1f2b4166

public class ManagementPosisiPage {

    private WebDriver driver;
<<<<<<< HEAD
    private DashboardPage dashboardPage;

    // Constructor
    public ManagementPosisiPage(WebDriver driver) {
        this.driver = driver;
        this.dashboardPage = new DashboardPage(driver);
    }

    // =================== LOCATORS ===================
    private By inputSearch = By.xpath("//input[@placeholder='cari berdasarkan nama']");
    private By btnReset = By.xpath("//button[normalize-space()='Reset']");
    private By btnSearch = By.xpath("//button[normalize-space()='Search']");
    private By btnTambah = By.xpath("//button[normalize-space()='Tambahkan']");

    private By tableRows = By.xpath("//table/tbody/tr");
    private By namaDivisiColumn = By.xpath("//table/tbody/tr/td[1]");
    private By tanggalBuatColumn = By.xpath("//table/tbody/tr/td[2]");
    private By tanggalUpdateColumn = By.xpath("//table/tbody/tr/td[3]");

    // =================== METHODS ===================

    /** 
     * Navigasi ke menu Management → Posisi
     */
    public void goToManagementPosisi() {
        dashboardPage.clickButtonMenuManagement();
        dashboardPage.clickSubMenuPosisi();
    }

    /**
     * Mengisi kolom pencarian dan menekan tombol Search
     */
    public void searchPosisi(String namaDivisi) {
        WebElement searchField = driver.findElement(inputSearch);
        searchField.clear();
        searchField.sendKeys(namaDivisi);
        driver.findElement(btnSearch).click();
    }

    /**
     * Klik tombol Reset untuk mengembalikan tampilan default tabel
     */
    public void resetSearch() {
        driver.findElement(btnReset).click();
    }

    /**
     * Klik tombol Tambahkan untuk membuka form input posisi baru
     */
    public void clickTambah() {
        driver.findElement(btnTambah).click();
    }

    /**
     * Mengambil jumlah total data posisi yang tampil di tabel
     */
    public int getJumlahData() {
        List<WebElement> rows = driver.findElements(tableRows);
        return rows.size();
    }

    /**
     * Mengambil nama divisi dari baris pertama tabel
     */
    public String getNamaDivisiPertama() {
        return driver.findElement(namaDivisiColumn).getText().trim();
    }

    /**
     * Mengambil tanggal buat dari baris pertama tabel
     */
    public String getTanggalBuatPertama() {
        return driver.findElement(tanggalBuatColumn).getText().trim();
    }

    /**
     * Mengambil tanggal update dari baris pertama tabel
     */
    public String getTanggalUpdatePertama() {
        return driver.findElement(tanggalUpdateColumn).getText().trim();
    }

    /**
     * Mengecek apakah hasil pencarian sesuai keyword
     */
    public boolean isHasilSearchTampil(String keyword) {
        List<WebElement> listNama = driver.findElements(namaDivisiColumn);
        for (WebElement el : listNama) {
            if (el.getText().toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Mengecek apakah tabel tidak memiliki data
     */
    public boolean isTableKosong() {
        return driver.findElements(tableRows).isEmpty();
    }

    public void clickTambahPosisi() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clickTambahPosisi'");
    }

    public void fillNamaPosisi(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fillNamaPosisi'");
    }

    public void clickSimpanPosisi() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clickSimpanPosisi'");
    }

    public boolean isPosisiDisplayed(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isPosisiDisplayed'");
    }

    public boolean isManagementPosisiPageDisplayed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isManagementPosisiPageDisplayed'");
    }
}
=======
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
>>>>>>> acbb1637a287b0ff342121fe2c8b809f1f2b4166
