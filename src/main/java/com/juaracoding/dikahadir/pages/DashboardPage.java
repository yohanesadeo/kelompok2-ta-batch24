package com.juaracoding.dikahadir.pages;

import org.openqa.selenium.By;

public class DashboardPage {
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

    // Elements inside Laporan pages (example for search)
    private By searchBar = By.xpath("//input[@id='search']");
    private By searchButton = By.xpath("//button[normalize-space()='Search']");

}
