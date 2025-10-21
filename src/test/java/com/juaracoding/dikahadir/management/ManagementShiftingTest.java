package com.juaracoding.dikahadir.management; // Adjust package name if needed

import com.juaracoding.dikahadir.pages.DashboardPage;
import com.juaracoding.dikahadir.pages.LogInPage;
import com.juaracoding.dikahadir.pages.ManagementShiftingPage;
import com.juaracoding.dikahadir.providers.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*; // Using TestNG annotations as they are common

import java.time.Duration;

/**
 * Test class for Management -> Shifting page functionalities.
 */
public class ManagementShiftingTest extends BaseTest {

    private LogInPage logInPage;
    private DashboardPage dashboardPage;
    private ManagementShiftingPage managementShiftingPage;
    private WebDriverWait wait;

    // ================== SETUP & TEARDOWN ==================

    @BeforeMethod
    public void setUpTest() { // Renamed to avoid conflict with BaseTest's @BeforeMethod
        // Initialize WebDriverWait for this test class
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));

        // Initialize Page Objects
        logInPage = new LogInPage(getDriver());
        dashboardPage = new DashboardPage(getDriver()); // Pass driver to DashboardPage
        managementShiftingPage = new ManagementShiftingPage(getDriver());

        // Navigate to login page (BaseTest already navigates to baseURL, but if you need a specific login URL)
        // getDriver().get("https://magang.dikahadir.com/authentication/login");

        // Perform login using LogInPage methods
        logInPage.login("admin@hadir.com", "MagangSQA_JC@123");

        // Wait for dashboard/management menu to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.buttonMenuManagement));

        // Navigate to Management -> Shifting using DashboardPage methods
        dashboardPage.clickButtonMenuManagement();
        dashboardPage.clickSubMenuShifting();

        // Verify we are on the Shifting page
        wait.until(ExpectedConditions.visibilityOfElementLocated(managementShiftingPage.headerTitle));
        Assert.assertEquals(managementShiftingPage.getHeaderTitle(), "Shifting",
                "Failed to navigate to Shifting page.");
    }

    // ================== TEST CASES ==================

    @Test(priority = 1)
    public void testSearchShifting() {
        System.out.println("Running test: testSearchShifting");
        String searchKeyword = "Shift Test"; // Replace with an actual shift name that exists

        managementShiftingPage.inputSearch(searchKeyword);
        managementShiftingPage.clickSearchButton();

        // Add verification: Check if the table now only shows results containing the
        // keyword
        // Example (you might need a more robust check, e.g., check all visible rows):
        org.openqa.selenium.WebElement firstRowShiftName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tbody/tr[1]/td[2]/p") // Adjust XPath based on actual table structure
        ));
        Assert.assertTrue(firstRowShiftName.getText().contains(searchKeyword), "Search result does not match keyword.");

        managementShiftingPage.clickResetButton(); // Clean up search
        System.out.println("Finished test: testSearchShifting");
    }

    @Test(priority = 2)
    public void testAddShiftingButton() {
        System.out.println("Running test: testAddShiftingButton");
        // This test just clicks the button. You'll need another Page Object for the
        // Add/Edit modal.
        managementShiftingPage.clickTambahButton();

        // Add verification: Check if the Add/Edit modal/form is displayed
        // Example: Wait for a specific element in the modal
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modal-title")));
        // // Replace with actual locator
        System.out.println("Finished test: testAddShiftingButton");
        // Add steps to close the modal if needed for subsequent tests
    }

    @Test(priority = 3)
    public void testEditShiftingButton() {
        System.out.println("Running test: testEditShiftingButton");
        String shiftToEdit = "Shift Test"; // Replace with an actual shift name that exists

        managementShiftingPage.clickEditButtonByName(shiftToEdit);

        // Add verification: Check if the Add/Edit modal/form is displayed with data
        // Example: Wait for the modal title and check if a field has the existing value
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modal-title")));
        // Assert.assertEquals(driver.findElement(By.id("shift-name-input")).getAttribute("value"),
        // shiftToEdit); // Replace locator
        System.out.println("Finished test: testEditShiftingButton");
        // Add steps to close the modal
    }

    @Test(priority = 4)
    public void testDeleteShiftingButton() {
        System.out.println("Running test: testDeleteShiftingButton");
        String shiftToDelete = "Shift Test"; // Replace with an actual shift name to delete

        managementShiftingPage.clickDeleteButtonByName(shiftToDelete);

        // Add verification: Check for confirmation dialog/popup
        // Example: Wait for a confirmation button and click it
        // WebElement confirmDeleteButton =
        // wait.until(ExpectedConditions.elementToBeClickable(By.id("confirm-delete")));
        // confirmDeleteButton.click();

        // Verify success message (using method from Page Object)
        // Assert.assertTrue(managementShiftingPage.getSuccessMessage().contains("berhasil
        // dihapus")); // Adjust expected message
        System.out.println("Finished test: testDeleteShiftingButton");
    }

    @Test(priority = 5)
    public void testPagination() {
        System.out.println("Running test: testPagination");
        // Assuming there are multiple pages
        // You might want to check if the next button is enabled first

        // Get text of current pagination display (e.g., "1-10 of 61")
        String initialDisplay = getDriver().findElement(By.cssSelector(".MuiTablePagination-displayedRows")).getText(); // Use
                                                                                                                        // a
                                                                                                                        // more
                                                                                                                        // specific
                                                                                                                        // locator
                                                                                                                        // if
                                                                                                                        // needed

        managementShiftingPage.clickNextPage();

        // Add a short wait or wait for table content to change
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Get text of pagination display again and assert it changed
        String nextDisplay = getDriver().findElement(By.cssSelector(".MuiTablePagination-displayedRows")).getText();
        Assert.assertNotEquals(initialDisplay, nextDisplay, "Pagination did not change after clicking next.");

        managementShiftingPage.clickPreviousPage();

        // Add wait
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Assert it returned to the initial state
        String previousDisplay = getDriver().findElement(By.cssSelector(".MuiTablePagination-displayedRows")).getText();
        Assert.assertEquals(initialDisplay, previousDisplay, "Pagination did not return after clicking previous.");
        System.out.println("Finished test: testPagination");
    }
}