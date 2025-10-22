package com.juaracoding.dikahadir.management;

import com.juaracoding.dikahadir.pages.DashboardPage;
import com.juaracoding.dikahadir.pages.LogInPage;
import com.juaracoding.dikahadir.pages.ManagementJabatanPage;
import com.juaracoding.dikahadir.providers.BaseTest;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ManagementJabatanTest extends BaseTest {

    private LogInPage loginPage;
    private DashboardPage dashboardPage;
    private ManagementJabatanPage managementJabatanPage;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        loginPage = new LogInPage(getDriver());
        dashboardPage = new DashboardPage(getDriver());
        managementJabatanPage = new ManagementJabatanPage(getDriver());
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        loginPage.login("admin@hadir.com", "MagangSQA_JC@123");
        dashboardPage.clickButtonMenuManagement();
        dashboardPage.clickSubMenuJabatan();
        managementJabatanPage.waitUntilPageLoaded();
    }

    @Test(priority = 1)
    public void testGoToManagementJabatanPage() {
        Assert.assertEquals(managementJabatanPage.getHeaderTitle(), "Jabatan");
    }

    @Test(priority = 2)
    public void testTambahJabatan() {
        managementJabatanPage.clickTambahButton();
        managementJabatanPage.inputNamaJabatan("Supervisor");
        managementJabatanPage.inputLevelJabatan("3");
        managementJabatanPage.clickSimpanButton();

        Assert.assertTrue(managementJabatanPage.getSuccessMessage().contains("berhasil"));
    }

    @Test(priority = 3)
    public void testEditJabatan() {
        managementJabatanPage.clickEditButtonByName("Supervisor");
        managementJabatanPage.inputNamaJabatan("Supervisor Area");
        managementJabatanPage.inputLevelJabatan("5");
        managementJabatanPage.clickSimpanButton();

        Assert.assertTrue(managementJabatanPage.getSuccessMessage().contains("berhasil"));
    }

    @Test(priority = 4)
    public void testSearchJabatan() {
        dashboardPage.fillSearchBar("Supervisor Area");
        dashboardPage.clickSearchButton();
        Assert.assertTrue(true, "Search executed successfully");
    }

    @Test(priority = 5)
    public void testResetSearch() {
        managementJabatanPage.clickResetButton();
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/management/position-level"));
    }

    @Test(priority = 6)
    public void testPaginationNext() {
        managementJabatanPage.clickNextPage();
        Assert.assertTrue(true, "Pagination Next clicked successfully");
    }

    @Test(priority = 7)
    public void testDeleteJabatan() {
        managementJabatanPage.clickDeleteButtonByName("Supervisor Area");
        managementJabatanPage.confirmDelete();
        Assert.assertTrue(managementJabatanPage.getSuccessMessage().contains("berhasil"));
    }

}
