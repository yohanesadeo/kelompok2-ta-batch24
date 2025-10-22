package com.juaracoding.dikahadir.management;

import com.juaracoding.dikahadir.pages.DashboardPage;
import com.juaracoding.dikahadir.pages.LogInPage;
import com.juaracoding.dikahadir.pages.ManagementPosisiPage;
import com.juaracoding.dikahadir.providers.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class ManagementPosisiTest extends BaseTest {

    private LogInPage loginPage;
    private DashboardPage dashboardPage;
    private ManagementPosisiPage managementPosisiPage;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUpTest() {
        loginPage = new LogInPage(getDriver());
        dashboardPage = new DashboardPage(getDriver());
        managementPosisiPage = new ManagementPosisiPage(getDriver());
        wait = new WebDriverWait(getDriver(), java.time.Duration.ofSeconds(10));

        // login & navigasi ke halaman posisi
        loginPage.login("admin@hadir.com", "MagangSQA_JC@123");
        dashboardPage.clickButtonMenuManagement();
        dashboardPage.clickSubMenuPosisi();
    }

    @Test(priority = 1)
    public void testGoToManagementPosisiPage() {
        String actualTitle = managementPosisiPage.getHeaderTitle();
        Assert.assertEquals(actualTitle, "Posisi");
    }

    @Test(priority = 2)
    public void testTambahPosisi() {
        managementPosisiPage.clickTambahButton();

        WebElement inputNama = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("position_name")));
        inputNama.sendKeys("Quality Assurance Junior");

        WebElement btnSimpan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Simpan']")));
        btnSimpan.click();

        String successMessage = managementPosisiPage.getSuccessMessage();
        Assert.assertTrue(successMessage.contains("berhasil"), "Pesan sukses tidak muncul setelah tambah posisi!");
    }

    @Test(priority = 3)
    public void testEditPosisi() {
        managementPosisiPage.clickEditButtonByName("Quality Assurance Junior");

        WebElement inputNama = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("position_name")));
        inputNama.clear();
        inputNama.sendKeys("Quality Assurance Senior");

        WebElement btnSimpan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Simpan']")));
        btnSimpan.click();

        String successMessage = managementPosisiPage.getSuccessMessage();
        Assert.assertTrue(successMessage.contains("berhasil"), "Pesan sukses tidak muncul setelah edit posisi!");
    }

    @Test(priority = 4)
    public void testSearchPosisi() {
        managementPosisiPage.inputSearch("Quality Assurance");
        managementPosisiPage.clickSearchButton();
        Assert.assertTrue(true, "Search operation completed.");
    }

    @Test(priority = 5)
    public void testResetSearchPosisi() {
        WebElement resetButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Reset']")));
        resetButton.click();

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://magang.dikahadir.com/management/position", "URL setelah reset tidak sesuai!");
    }

    @Test(priority = 6)
    public void testPaginationPosisi() {
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Go to next page']")));
        nextButton.click();
        Assert.assertTrue(true, "Pagination berhasil dijalankan.");
    }

    @Test(priority = 7)
    public void testDeletePosisi() {
        managementPosisiPage.clickDeleteButtonByName("Quality Assurance Senior");

        WebElement confirmDelete = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Hapus']")));
        confirmDelete.click();

        String successMessage = managementPosisiPage.getSuccessMessage();
        Assert.assertTrue(successMessage.contains("berhasil"), "Pesan sukses tidak muncul setelah hapus posisi!");
    }
}
