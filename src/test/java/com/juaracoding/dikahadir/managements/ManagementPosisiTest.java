package com.juaracoding.dikahadir.managements;

import com.juaracoding.dikahadir.pages.LogInPage;
import com.juaracoding.dikahadir.pages.ManagementPosisiPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class ManagementPosisiTest {
    private WebDriver driver;
    private LogInPage logInPage;
    private ManagementPosisiPage managementPosisi;

    @BeforeMethod
    public void setUp() {
        // Konfigurasi ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1366,768");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Akses URL
        driver.get("https://magang.dikahadir.com/login");

        // Login ke sistem
        logInPage = new LogInPage(driver);
        logInPage.login("admin@dikahadir.com", "123456"); // ganti dengan akun valid

        // Inisialisasi halaman Management Posisi
        managementPosisi = new ManagementPosisiPage(driver);
    }

    @Test(priority = 1)
    public void testOpenManagementPosisiPage() {
        managementPosisi.goToManagementPosisi();
        Assert.assertTrue(managementPosisi.isManagementPosisiPageDisplayed(),
                "Halaman Management Posisi tidak tampil!");
    }

    @Test(priority = 2)
    public void testAddNewPosisi() {
        managementPosisi.goToManagementPosisi();
        managementPosisi.clickTambahPosisi();
        managementPosisi.fillNamaPosisi("Software Engineer QA");
        managementPosisi.clickSimpanPosisi();

        // Verifikasi bahwa posisi baru berhasil ditambahkan
        Assert.assertTrue(managementPosisi.isPosisiDisplayed("Software Engineer QA"),
                "Posisi baru tidak ditemukan di tabel!");
    }

    @Test(priority = 3)
    public void testSearchPosisi() {
        managementPosisi.goToManagementPosisi();
        managementPosisi.searchPosisi("Software Engineer QA");
        Assert.assertTrue(managementPosisi.isPosisiDisplayed("Software Engineer QA"),
                "Posisi tidak ditemukan saat pencarian!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
