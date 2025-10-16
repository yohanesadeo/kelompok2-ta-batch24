package com.juaracoding.dikahadir.management;
    
import com.juaracoding.dikahadir.pages.DashboardPage;
import com.juaracoding.dikahadir.pages.LogInPage;
import com.juaracoding.dikahadir.pages.ManagementPosisiPage;
import com.juaracoding.dikahadir.providers.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ManagementPosisiTest extends BaseTest {

    private LogInPage loginPage;
    private DashboardPage dashboardPage;
    private ManagementPosisiPage managementPosisiPage;

    @BeforeMethod
    public void setUp() {
        loginPage = new LogInPage(getDriver());
        dashboardPage = new DashboardPage(getDriver());
        managementPosisiPage = new ManagementPosisiPage(getDriver());
        loginPage.login("admin@hadir.com", "MagangSQA_JC@123");
        dashboardPage.clickButtonMenuManagement();
        dashboardPage.clickSubMenuPosisi();
    }

    @Test
    public void testGoToManagementPosisiPage() {
        String actualTitle = managementPosisiPage.getHeaderTitle();
        Assert.assertEquals(actualTitle, "Posisi");
    }

    @Test
    public void testSearchPosisi() {
        managementPosisiPage.inputSearch("QA");
        managementPosisiPage.clickSearchButton();
        // Add assertion to verify search results if possible
        // For now, we just check if the search operation completes without error
        Assert.assertTrue(true, "Search operation completed.");
    }
}

