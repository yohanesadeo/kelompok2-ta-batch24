package com.juaracoding.dikahadir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object Model untuk halaman Management -> Shifting.
 * Mengelola elemen dan interaksi di halaman daftar shift.
 */
public class ManagementShiftingPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private DashboardPage dashboardPage; // Objek untuk mengakses locator umum

    // ================== LOCATORS SPESIFIK UNTUK HALAMAN SHIFTING
    // ==================
    public By headerTitle = By.xpath("//p[normalize-space()='Shifting']"); // Header halaman
    public By searchBar = By.id("search"); // Search bar spesifik di halaman ini
    public By startDateInput = By.xpath("//input[@placeholder='Start Date']");
    public By endDateInput = By.xpath("//input[@placeholder='End Date']");
    public By filterButton = By.xpath("//button[.//svg[contains(@class, 'feather-filter')]]"); // Tombol filter dengan
    public By successMessage = By.xpath("//div[contains(@class, 'MuiAlert-message')]"); // Notifikasi sukses

    // ================== CONSTRUCTOR ==================
    public ManagementShiftingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.dashboardPage = new DashboardPage(driver); // Inisialisasi DashboardPage
    }

    // ================== PAGE ACTIONS / METHODS ==================

    /**
     * Mendapatkan teks judul header halaman.
     * 
     * @return String judul halaman ("Shifting").
     */
    public String getHeaderTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerTitle)).getText();
    }

    /**
     * Memasukkan keyword ke dalam search bar.
     * 
     * @param keyword Nama shift yang dicari.
     */
    public void inputSearch(String keyword) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        searchInput.clear();
        searchInput.sendKeys(keyword);
    }

    /**
     * Mengklik tombol "Search" (di sebelah search bar).
     */
    public void clickSearchButton() {
        // Menggunakan locator dari DashboardPage
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.searchButton)).click();
    }

    /**
     * Memasukkan tanggal mulai pada filter.
     * 
     * @param date Tanggal (format YYYY-MM-DD atau sesuai input).
     */
    public void inputStartDate(String date) {
        WebElement dateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(startDateInput));
        // Mungkin perlu clear atau interaksi kalender tergantung implementasi
        dateInput.sendKeys(date);
    }

    /**
     * Memasukkan tanggal selesai pada filter.
     * 
     * @param date Tanggal (format YYYY-MM-DD atau sesuai input).
     */
    public void inputEndDate(String date) {
        WebElement dateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(endDateInput));
        // Mungkin perlu clear atau interaksi kalender
        dateInput.sendKeys(date);
    }

    /**
     * Mengklik tombol "Filter" (ikon filter).
     */
    public void clickFilterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(filterButton)).click();
    }

    /**
     * Mengklik tombol "Reset" untuk membersihkan filter/search.
     */
    public void clickResetButton() {
        // Menggunakan locator dari DashboardPage
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.resetButton)).click();
    }

    /**
     * Mengklik tombol "Tambahkan" untuk menambah shift baru.
     */
    public void clickTambahButton() {
        // Menggunakan locator dari DashboardPage
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.tambahButton)).click();
    }

    /**
     * Mengklik ikon tiga titik (menu aksi) pada baris tabel berdasarkan nama shift.
     * 
     * @param namaShift Nama shift yang barisnya akan di klik.
     */
    private void clickActionMenuByName(String namaShift) {
        // XPath ini mencari <p> dengan nama shift, lalu naik ke <tr>, lalu cari tombol
        // aksi
        By actionButton = By
                .xpath(String.format("//p[text()='%s']/ancestor::tr//button[@aria-label='action']", namaShift));
        wait.until(ExpectedConditions.elementToBeClickable(actionButton)).click();
    }

    /**
     * Mengklik tombol 'Edit' setelah menu aksi terbuka.
     * 
     * @param namaShift Nama shift yang akan diedit.
     */
    public void clickEditButtonByName(String namaShift) {
        clickActionMenuByName(namaShift); // Buka menu aksi dulu
        // Gunakan locator Edit dari DashboardPage
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.actionEdit)).click();
    }

    /**
     * Mengklik tombol 'Delete' setelah menu aksi terbuka.
     * 
     * @param namaShift Nama shift yang akan dihapus.
     */
    public void clickDeleteButtonByName(String namaShift) {
        clickActionMenuByName(namaShift); // Buka menu aksi dulu
        // Gunakan locator Delete dari DashboardPage
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.actionDelete)).click();
    }

    /**
     * Mengklik tombol halaman berikutnya di pagination.
     */
    public void clickNextPage() {
        // Menggunakan locator dari DashboardPage
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.nextPageButton)).click();
    }

    /**
     * Mengklik tombol halaman sebelumnya di pagination.
     */
    public void clickPreviousPage() {
        // Menggunakan locator dari DashboardPage
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.previousPageButton)).click();
    }

    /**
     * Mendapatkan pesan sukses setelah aksi (misal: tambah, edit, hapus).
     * 
     * @return String pesan notifikasi.
     */
    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }
}