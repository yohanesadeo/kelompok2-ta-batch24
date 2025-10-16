package com.juaracoding.dikahadir.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    public DashboardPage() {
    }
    

    private WebDriver driver;
    private WebDriverWait wait;
    
    // Main Menus
    public By buttonMenuManagement = By
            .xpath("//p[text()='Management']/ancestor::div[contains(@class, 'sidebar__wrapper')]");
    public By buttonMenuLaporan = By
            .xpath("//p[text()='Laporan']/ancestor::div[contains(@class, 'sidebar__wrapper')]");

    // Management Sub-Menus
    public By subMenuPosisi = By.xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Posisi']");
    public By subMenuAbsenPoint = By.xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Absen Point']");
    public By subMenuJabatan = By.xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Jabatan']");
    public By subMenuShifting = By.xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Shifting']");
    public By subMenuJadwal = By.xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Jadwal']");
    public By subMenuKalender = By.xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Kalender']");
    public By subMenuDayOff = By.xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Day Off']");

    // Laporan Sub-Menus
    public By subMenuLaporanSemua = By.xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Semua']");
    public By subMenuLaporanKehadiran = By.xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Kehadiran']");
    public By subMenuLaporanIzinTerlambat = By
            .xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Izin Terlambat']");
    public By subMenuLaporanPulangCepat = By
            .xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Pulang Cepat']");

    // Elemen Umum
    public By searchBar = By.xpath("//input[@placeholder='Search']");
    public By searchButton = By.xpath("//button[normalize-space()='Search']");
    public By resetButton = By.xpath("//button[normalize-space()='Reset']");
    public By tambahButton = By.xpath("//button[normalize-space()='Tambahkan']");

    // Tombol di dalam menu aksi (setelah klik ikon tiga titik)
    public By actionEdit = By.xpath("//li[normalize-space()='Edit']");
    public By actionDelete = By.xpath("//li[normalize-space()='Delete']");

    // Kontrol Halaman (Pagination)
    public By nextPageButton = By.xpath("//button[@aria-label='Go to next page']");
    public By previousPageButton = By.xpath("//button[@aria-label='Go to previous page']");

    public By specificPageButton(int pageNumber) {
        return By.xpath("//button[@aria-label='Go to page " + pageNumber + "']");
    }

    public By rowsPerPageDropdown = By
            .xpath("//div[contains(@class, 'MuiTablePagination-selectLabel')]/following-sibling::div");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickButtonMenuManagement() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonMenuManagement)).click();
    }

    public void clickSubMenuPosisi() {
        wait.until(ExpectedConditions.elementToBeClickable(subMenuPosisi)).click();
    }

    public void clickSubMenuAbsenPoint() {
        wait.until(ExpectedConditions.elementToBeClickable(subMenuAbsenPoint)).click();
    }

    public void clickSubMenuJabatan() {
        wait.until(ExpectedConditions.elementToBeClickable(subMenuJabatan)).click();
    }

    public void clickSubMenuShifting() {
        wait.until(ExpectedConditions.elementToBeClickable(subMenuShifting)).click();
    }

    public void clickSubMenuJadwal() {
        wait.until(ExpectedConditions.elementToBeClickable(subMenuJadwal)).click();
    }

    public void clickSubMenuKalender() {
        wait.until(ExpectedConditions.elementToBeClickable(subMenuKalender)).click();
    }

    public void clickSubMenuDayOff() {
        wait.until(ExpectedConditions.elementToBeClickable(subMenuDayOff)).click();
    }

    public void clickButtonMenuLaporan() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonMenuLaporan)).click();
    }

    public void clickSubMenuLaporanSemua() {
        wait.until(ExpectedConditions.elementToBeClickable(subMenuLaporanSemua)).click();
    }

    public void clickSubMenuLaporanKehadiran() {
        wait.until(ExpectedConditions.elementToBeClickable(subMenuLaporanKehadiran)).click();
    }

    public void clickSubMenuLaporanIzinTerlambat() {
        wait.until(ExpectedConditions.elementToBeClickable(subMenuLaporanIzinTerlambat)).click();
    }

    public void clickSubMenuLaporanPulangCepat() {
        wait.until(ExpectedConditions.elementToBeClickable(subMenuLaporanPulangCepat)).click();    
    }

    public void fillSearchBar(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar)).sendKeys(text);
    }

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void clickNextPageButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextPageButton)).click();
    }

    public void clickPreviousPageButton() {
        wait.until(ExpectedConditions.elementToBeClickable(previousPageButton)).click();
    }

    public void clickSpecificPageButton(int pageNumber) {
        wait.until(ExpectedConditions.elementToBeClickable(specificPageButton(pageNumber))).click();
    }

    public void selectRowsPerPage(String rowsPerPage) {
        wait.until(ExpectedConditions.elementToBeClickable(rowsPerPageDropdown)).click();
        driver.findElement(By.xpath("//li[text()='" + rowsPerPage + "']")).click();
    }
}
