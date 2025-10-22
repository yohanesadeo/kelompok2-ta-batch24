package com.juaracoding.dikahadir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions; // <-- Import Actions
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddEditShiftingModalPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ================== LOCATORS MODAL ==================
    public By modalTitle = By.xpath("//h2[contains(text(), 'Tambah Shifting')]");
    public By inputNamaShift = By.xpath("//input[@placeholder='Nama jam kerja']");
    public By inputCodeShifting = By.xpath("//input[@placeholder='Code Shifting']");
    public By dropdownPilihUnit = By.xpath("//label[text()='Pilih Unit']/following-sibling::div/div");
    public By iconJamMasuk = By.xpath("//label[text()='Jam Masuk']/..//button");
    public By iconJamKeluar = By.xpath("//label[text()='Jam Keluar']/..//button");
    public By iconBreakStart = By.xpath("//label[text()='Break Start']/..//button");
    public By iconBreakEnd = By.xpath("//label[text()='Break End']/..//button");
    // Tombol OK tidak diperlukan lagi
    // private By clockOkButton = By.xpath("//button[normalize-space()='OK']");
    public By tambahButton = By.xpath("//button[normalize-space()='Tambah']");
    public By batalButton = By.xpath("//button[normalize-space()='Batal']");
    public By closeButton = By.xpath("//button[@aria-label='close']");

    // Locator untuk titik tengah jam (pin) - PENTING untuk drag
    private By clockPin = By.xpath("//div[contains(@class,'MuiClock-pin')]");

    // ================== CONSTRUCTOR ==================
    public AddEditShiftingModalPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ================== MODAL ACTIONS / METHODS ==================

    // ... (isModalDisplayed, inputNamaShift, inputCodeShifting, selectUnit tetap
    // sama) ...
    public boolean isModalDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(modalTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void inputNamaShift(String nama) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputNamaShift)).sendKeys(nama);
    }

    public void inputCodeShifting(String code) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputCodeShifting)).sendKeys(code);
    }

    public void selectUnit(String unitName) {
        wait.until(ExpectedConditions.elementToBeClickable(dropdownPilihUnit)).click();
        By unitOption = By.xpath(String.format("//ul[@role='listbox']//li[text()='%s']", unitName));
        wait.until(ExpectedConditions.elementToBeClickable(unitOption)).click();
    }

    /**
     * Metode internal untuk memilih jam dan menit menggunakan drag-and-drop
     * Actions.
     * (PERBAIKAN: Menggunakan Actions clickAndHold...release)
     */
    private void selectTime(By clockIconLocator, String hour, String minute) {
        try {
            // 1. Klik ikon jam untuk membuka picker
            wait.until(ExpectedConditions.elementToBeClickable(clockIconLocator)).click();

            // 2. Tunggu picker muncul (tunggu pin tengah) dan beri jeda animasi
            WebElement pinElement = wait.until(ExpectedConditions.visibilityOfElementLocated(clockPin));
            Thread.sleep(500); // Jeda untuk animasi

            // 3. Siapkan Actions
            Actions actions = new Actions(driver);

            // 4. Tentukan locator untuk ANGKA jam target
            By clockHourTarget = By.xpath(String.format("//span[@aria-label='%s hours']", hour));
            WebElement hourTargetElement = wait.until(ExpectedConditions.presenceOfElementLocated(clockHourTarget));

            // 5. Lakukan drag dari pin tengah ke angka jam target (Kemungkinan Gagal karena
            // Mask)
            actions.clickAndHold(pinElement) // Klik dan tahan pin tengah
                    .moveToElement(hourTargetElement) // Geser ke angka jam
                    .release() // Lepaskan di angka jam
                    .perform(); // Lakukan aksi

            // 6. Beri jeda untuk transisi ke menit
            Thread.sleep(500);

            // 7. Tentukan locator untuk ANGKA menit target
            By clockMinuteTarget = By.xpath(String.format("//span[@aria-label='%s minutes']", minute));
            WebElement minuteTargetElement = wait.until(ExpectedConditions.presenceOfElementLocated(clockMinuteTarget));

            // 8. Lakukan drag dari pin tengah ke angka menit target (Kemungkinan Gagal
            // karena Mask)
            // Pin element mungkin perlu dicari ulang jika DOM berubah
            pinElement = wait.until(ExpectedConditions.visibilityOfElementLocated(clockPin));
            actions.clickAndHold(pinElement) // Klik dan tahan pin tengah lagi
                    .moveToElement(minuteTargetElement) // Geser ke angka menit
                    .release() // Lepaskan di angka menit
                    .perform(); // Lakukan aksi

            // 9. Tidak perlu klik OK, picker menutup otomatis

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread sleep interrupted: " + e.getMessage());
        } catch (Exception e) {
            // Tangkap exception lain (misal: ElementClickIntercepted) dan laporkan
            System.err.println("Error during selectTime using Actions: " + e.getMessage());
        }
    }

    // Metode publik untuk mempermudah pemanggilan
    public void selectJamMasuk(String hour, String minute) {
        selectTime(iconJamMasuk, hour, minute);
    }

    public void selectJamKeluar(String hour, String minute) {
        selectTime(iconJamKeluar, hour, minute);
    }

    public void selectBreakStart(String hour, String minute) {
        selectTime(iconBreakStart, hour, minute);
    }

    public void selectBreakEnd(String hour, String minute) {
        selectTime(iconBreakEnd, hour, minute);
    }

    public void clickTambahButton() {
        wait.until(ExpectedConditions.elementToBeClickable(tambahButton)).click();
    }

    public void clickBatalButton() {
        wait.until(ExpectedConditions.elementToBeClickable(batalButton)).click();
    }

    public void clickCloseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
    }
}

// Jangan lupa tambahkan: import org.testng.Assert; (atau JUnit) di bagian atas
// file test Anda