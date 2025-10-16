package com.juaracoding.dikahadir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    public DashboardPage() {
    }

    private WebDriver driver;

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
