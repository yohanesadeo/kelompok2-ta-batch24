package com.juaracoding.dikahadir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddEditShiftingModalPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ================== LOCATORS MODAL (SESUAI GAMBAR) ==================
    public By modalTitle = By.xpath("//h2[contains(text(), 'Tambah Shifting')]");
    public By inputNamaShift = By.xpath("//input[@placeholder='Nama jam kerja']");
    public By inputCodeShifting = By.xpath("//input[@placeholder='Code Shifting']");

    // Dropdown 'Pilih Unit'
    public By dropdownPilihUnit = By.xpath("//label[text()='Pilih Unit']/following-sibling::div/div");

    // Ikon Jam (untuk membuka clock picker)
    public By iconJamMasuk = By.xpath("//label[text()='Jam Masuk']/..//button");
    public By iconJamKeluar = By.xpath("//label[text()='Jam Keluar']/..//button");
    public By iconBreakStart = By.xpath("//label[text()='Break Start']/..//button");
    public By iconBreakEnd = By.xpath("//label[text()='Break End']/..//button");

    // Tombol di dalam Clock Picker
    private By clockOkButton = By.xpath("//button[normalize-space()='OK']"); // Ganti 'OK' jika beda

    // Tombol Modal
    public By tambahButton = By.xpath("//button[normalize-space()='Tambah']");
    public By batalButton = By.xpath("//button[normalize-space()='Batal']");
    public By closeButton = By.xpath("//button[@aria-label='close']"); // Tombol X

    // ================== CONSTRUCTOR ==================
    public AddEditShiftingModalPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ================== MODAL ACTIONS / METHODS ==================

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

    /**
     * Metode untuk memilih unit dari dropdown.
     * 
     * @param unitName Nama unit yang ingin dipilih (harus persis).
     */
    public void selectUnit(String unitName) {
        // 1. Klik dropdown untuk membuka list
        wait.until(ExpectedConditions.elementToBeClickable(dropdownPilihUnit)).click();

        // 2. Tentukan locator untuk opsinya (biasanya <li> di dalam <ul>)
        By unitOption = By.xpath(String.format("//ul[@role='listbox']//li[text()='%s']", "1 edit"));

        // 3. Tunggu opsi muncul dan klik
        wait.until(ExpectedConditions.elementToBeClickable(unitOption)).click();
    }

    /**
     * Metode internal untuk memilih jam dan menit dari clock picker.
     */
    private void selectTime(By clockIconLocator, String hour, String minute) {
        // 1. Klik ikon jam untuk membuka picker
        wait.until(ExpectedConditions.elementToBeClickable(clockIconLocator)).click();

        // 2. Tentukan locator untuk jam (angka di lingkaran)
        By clockHour = By.xpath(String.format("//span[contains(@class, 'MuiClockNumber-root')][text()='%s']", hour));

        // 3. Klik jam
        wait.until(ExpectedConditions.elementToBeClickable(clockHour)).click();

        // 4. Tentukan locator untuk menit
        By clockMinute = By
                .xpath(String.format("//span[contains(@class, 'MuiClockNumber-root')][text()='%s']", minute));

        // 5. Klik menit
        wait.until(ExpectedConditions.elementToBeClickable(clockMinute)).click();

        // 6. Klik OK untuk konfirmasi
        wait.until(ExpectedConditions.elementToBeClickable(clockOkButton)).click();
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