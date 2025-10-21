package com.juaracoding.dikahadir.management;

import com.juaracoding.dikahadir.pages.AddEditShiftingModalPage;
import com.juaracoding.dikahadir.pages.DashboardPage;
import com.juaracoding.dikahadir.pages.LogInPage;
import com.juaracoding.dikahadir.pages.ManagementShiftingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class ManagementShiftingTest {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static LogInPage logInPage;
    private static DashboardPage dashboardPage;
    private static ManagementShiftingPage managementShiftingPage;
    private static AddEditShiftingModalPage addEditShiftingModalPage;

    private String testShiftName;

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        logInPage = new LogInPage(driver);
        dashboardPage = new DashboardPage(driver);
        managementShiftingPage = new ManagementShiftingPage(driver);
        addEditShiftingModalPage = new AddEditShiftingModalPage(driver);
    }

    @BeforeMethod
    public void loginAndNavigateToShifting() {
        driver.get("https://magang.dikahadir.com/authentication/login");
        logInPage.login("admin@hadir.com", "MagangSQA_JC@123");

        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.buttonMenuManagement)).click();
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.subMenuShifting)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(managementShiftingPage.headerTitle));
        Assert.assertEquals(managementShiftingPage.getHeaderTitle(), "Shifting",
                "Failed to navigate to Shifting page.");
    }

    @Test(priority = 1, description = "Test End-to-End: Add, Verify, Edit, Verify, Delete, Verify")
    public void testShiftingE2E_AddEditDelete() {

        // --- DATA UNTUK TEST ---
        // PENTING: Ganti "Nama Unit Testing" dengan nama unit yang valid dari dropdown
        String namaUnit = "1Juara";
        String namaShiftAdd = "Shift E2E " + System.currentTimeMillis();
        String codeShiftAdd = "E2E-" + System.currentTimeMillis() / 1000;

        String namaShiftEdit = namaShiftAdd + " (Edited)";
        String codeShiftEdit = codeShiftAdd + "-ED";

        // --- 1. Add New Shift ---
        System.out.println("Running E2E Step 1: Add New Shift");
        managementShiftingPage.clickTambahButton();
        Assert.assertTrue(addEditShiftingModalPage.isModalDisplayed(), "Modal Tambah Shift tidak muncul.");

        addEditShiftingModalPage.inputNamaShift(namaShiftAdd);
        addEditShiftingModalPage.inputCodeShifting(codeShiftAdd);
        addEditShiftingModalPage.selectUnit(namaUnit); // Menggunakan metode dropdown
        addEditShiftingModalPage.selectJamMasuk("8", "00"); // Klik jam 8, lalu menit 00
        addEditShiftingModalPage.selectJamKeluar("17", "00"); // Klik jam 17, lalu menit 00
        addEditShiftingModalPage.selectBreakStart("12", "00");
        addEditShiftingModalPage.selectBreakEnd("13", "00");

        addEditShiftingModalPage.clickTambahButton(); // Klik tombol 'Tambah' di modal

        // --- 2. Verify Add ---
        System.out.println("Running E2E Step 2: Verify Added Shift");
        String successMsgAdd = managementShiftingPage.getSuccessMessage();
        Assert.assertTrue(successMsgAdd.contains("berhasil ditambahkan"), "Pesan sukses tambah shift tidak sesuai.");

        managementShiftingPage.inputSearch(namaShiftAdd);
        managementShiftingPage.clickSearchButton();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//tbody/tr"), 1));
        WebElement addedShiftRow = driver
                .findElement(By.xpath(String.format("//tbody//p[text()='%s']/ancestor::tr", namaShiftAdd)));
        Assert.assertTrue(addedShiftRow.isDisplayed(), "Shift yang baru ditambahkan tidak ditemukan.");
        Assert.assertEquals(addedShiftRow.findElement(By.xpath("./td[3]/p")).getText(), "08:00:00",
                "Jam masuk tidak sesuai.");
        Assert.assertEquals(addedShiftRow.findElement(By.xpath("./td[4]/p")).getText(), "17:00:00",
                "Jam selesai tidak sesuai.");
        managementShiftingPage.clickResetButton();
        System.out.println("Shift '" + namaShiftAdd + "' berhasil ditambahkan.");

        // --- 3. Edit Shift ---
        System.out.println("Running E2E Step 3: Edit Shift");
        managementShiftingPage.clickEditButtonByName(namaShiftAdd); // Menggunakan nama shift yang baru dibuat
        Assert.assertTrue(addEditShiftingModalPage.isModalDisplayed(), "Modal Edit Shift tidak muncul.");

        WebElement namaInput = wait
                .until(ExpectedConditions.visibilityOfElementLocated(addEditShiftingModalPage.inputNamaShift));
        namaInput.clear();
        namaInput.sendKeys(namaShiftEdit);

        WebElement codeInput = wait
                .until(ExpectedConditions.visibilityOfElementLocated(addEditShiftingModalPage.inputCodeShifting));
        codeInput.clear();
        codeInput.sendKeys(codeShiftEdit);

        // Mengubah jam
        addEditShiftingModalPage.selectJamMasuk("9", "30");
        addEditShiftingModalPage.selectJamKeluar("18", "30");

        addEditShiftingModalPage.clickTambahButton(); // Tombol di modal edit mungkin "Simpan" atau "Update"

        // --- 4. Verify Edit ---
        System.out.println("Running E2E Step 4: Verify Edited Shift");
        String successMsgEdit = managementShiftingPage.getSuccessMessage();
        Assert.assertTrue(successMsgEdit.contains("berhasil diubah"), "Pesan sukses edit shift tidak sesuai.");

        managementShiftingPage.inputSearch(namaShiftEdit); // Cari dengan nama baru
        managementShiftingPage.clickSearchButton();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//tbody/tr"), 1));
        WebElement editedShiftRow = driver
                .findElement(By.xpath(String.format("//tbody//p[text()='%s']/ancestor::tr", namaShiftEdit)));
        Assert.assertTrue(editedShiftRow.isDisplayed(), "Shift yang diedit tidak ditemukan.");
        Assert.assertEquals(editedShiftRow.findElement(By.xpath("./td[3]/p")).getText(), "09:30:00",
                "Jam masuk setelah edit tidak sesuai.");
        Assert.assertEquals(editedShiftRow.findElement(By.xpath("./td[4]/p")).getText(), "18:30:00",
                "Jam selesai setelah edit tidak sesuai.");
        managementShiftingPage.clickResetButton();
        System.out.println("Shift berhasil diedit menjadi '" + namaShiftEdit + "'.");
        testShiftName = namaShiftEdit; // Update nama shift untuk langkah delete

        // --- 5. Delete Shift ---
        System.out.println("Running E2E Step 5: Delete Shift");
        managementShiftingPage.clickDeleteButtonByName(testShiftName);

        By confirmationDialogTitle = By.xpath("//h2[contains(text(),'Hapus Data')]"); // GANTI LOCATOR
        By confirmDeleteButton = By.xpath("//button[normalize-space()='Hapus']"); // GANTI LOCATOR
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationDialogTitle));
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();

        // --- 6. Verify Delete ---
        System.out.println("Running E2E Step 6: Verify Deleted Shift");
        String successMsgDelete = managementShiftingPage.getSuccessMessage();
        Assert.assertTrue(successMsgDelete.contains("berhasil dihapus"), "Pesan sukses hapus shift tidak sesuai.");

        managementShiftingPage.inputSearch(testShiftName);
        managementShiftingPage.clickSearchButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } // Tunggu search

        boolean isShiftPresent;
        try {
            driver.findElement(By.xpath(String.format("//tbody//p[text()='%s']", testShiftName)));
            isShiftPresent = true;
        } catch (NoSuchElementException e) {
            isShiftPresent = false;
        }
        Assert.assertFalse(isShiftPresent, "Shift masih ditemukan di tabel setelah dihapus.");

        managementShiftingPage.clickResetButton();
        System.out.println("Shift '" + testShiftName + "' berhasil dihapus.");
    }

    // ... Test pagination dan search lainnya ...

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}