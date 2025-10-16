package com.juaracoding.dikahadir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private WebDriver driver;

    // Main Menus
    private By buttonMenuManagement = By
            .xpath("//p[text()='Management']/ancestor::div[contains(@class, 'sidebar__wrapper')]");
    private By buttonMenuLaporan = By
            .xpath("//p[text()='Laporan']/ancestor::div[contains(@class, 'sidebar__wrapper')]");

    // Management Sub-Menus
    private By subMenuPosisi = By.xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Posisi']");
    private By subMenuAbsenPoint = By.xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Absen Point']");
    private By subMenuJabatan = By.xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Jabatan']");
    private By subMenuShifting = By.xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Shifting']");
    private By subMenuJadwal = By.xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Jadwal']");
    private By subMenuKalender = By.xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Kalender']");
    private By subMenuDayOff = By.xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Day Off']");

    // Laporan Sub-Menus
    private By subMenuLaporanSemua = By.xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Semua']");
    private By subMenuLaporanKehadiran = By.xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Kehadiran']");
    private By subMenuLaporanIzinTerlambat = By
            .xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Izin Terlambat']");
    private By subMenuLaporanPulangCepat = By
            .xpath("//div[contains(@class, 'MuiCollapse-root')]//p[text()='Pulang Cepat']");

    // Elemen Umum
    private By searchBar = By.xpath("//input[@placeholder='Search']");
    private By searchButton = By.xpath("//button[normalize-space()='Search']");

    // Kontrol Halaman (Pagination)
    private By nextPageButton = By.xpath("//button[@aria-label='Go to next page']");
    private By previousPageButton = By.xpath("//button[@aria-label='Go to previous page']");
   
    private By specificPageButton(int pageNumber) {
        return By.xpath("//button[@aria-label='Go to page " + pageNumber + "']");
    }

    private By rowsPerPageDropdown = By
            .xpath("//div[contains(@class, 'MuiTablePagination-selectLabel')]/following-sibling::div");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonMenuManagement() {
        driver.findElement(buttonMenuManagement).click();
    }

    public void clickSubMenuPosisi() {
        driver.findElement(subMenuPosisi).click();
    }

    public void clickSubMenuAbsenPoint() {
        driver.findElement(subMenuAbsenPoint).click();
    }

    public void clickSubMenuJabatan() {
        driver.findElement(subMenuJabatan).click();
    }

    public void clickSubMenuShifting() {
        driver.findElement(subMenuShifting).click();
    }

    public void clickSubMenuJadwal() {
        driver.findElement(subMenuJadwal).click();
    }

    public void clickSubMenuKalender() {
        driver.findElement(subMenuKalender).click();
    }

    public void clickSubMenuDayOff() {
        driver.findElement(subMenuDayOff).click();
    }

    public void clickButtonMenuLaporan() {
        driver.findElement(buttonMenuLaporan).click();
    }

    public void clickSubMenuLaporanSemua() {
        driver.findElement(subMenuLaporanSemua).click();
    }

    public void clickSubMenuLaporanKehadiran() {
        driver.findElement(subMenuLaporanKehadiran).click();
    }

    public void clickSubMenuLaporanIzinTerlambat() {
        driver.findElement(subMenuLaporanIzinTerlambat).click();
    }

    public void clickSubMenuLaporanPulangCepat() {
        driver.findElement(subMenuLaporanPulangCepat).click();
    }

    public void fillSearchBar(String text) {
        driver.findElement(searchBar).sendKeys(text);
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    public void clickNextPageButton() {
        driver.findElement(nextPageButton).click();
    }

    public void clickPreviousPageButton() {
        driver.findElement(previousPageButton).click();
    }

    public void clickSpecificPageButton(int pageNumber) {
        driver.findElement(specificPageButton(pageNumber)).click();
    }

    public void selectRowsPerPage(String rowsPerPage) {
        driver.findElement(rowsPerPageDropdown).click();
        driver.findElement(By.xpath("//li[text()='" + rowsPerPage + "']")).click();
    }
}
