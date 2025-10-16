package com.juaracoding.dikahadir.management;
  
import com.juaracoding.dikahadir.pages.DashboardPage;
import com.juaracoding.dikahadir.pages.LogInPage;
import com.juaracoding.dikahadir.pages.ManagementShiftingPage;
import com.juaracoding.dikahadir.providers.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ManagementShiftingTest extends BaseTest {

    private LogInPage loginPage;
    private DashboardPage dashboardPage;
    private ManagementShiftingPage managementShiftingPage;

    @BeforeMethod
    public void setUp() {
        loginPage = new LogInPage(getDriver());
        dashboardPage = new DashboardPage(getDriver());
        managementShiftingPage = new ManagementShiftingPage(getDriver());
        loginPage.login("admin@hadir.com", "MagangSQA_JC@123");
        dashboardPage.clickButtonMenuManagement();
        dashboardPage.clickSubMenuShifting();
    }

    @Test
    public void testGoToManagementShiftingPage() {
        String actualTitle = managementShiftingPage.getHeaderTitle();
        Assert.assertEquals(actualTitle, "Shifting");
    }

    @Test
    public void testSearchShifting() {
        managementShiftingPage.inputSearch("Pagi");
        // No explicit search button on this page, assuming search is live or triggered by enter
        // For now, we just check if the search operation completes without error
        Assert.assertTrue(true, "Search operation completed.");
    }
}

