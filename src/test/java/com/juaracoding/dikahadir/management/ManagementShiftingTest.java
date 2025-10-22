package com.juaracoding.dikahadir.management; // Package sudah disesuaikan

import com.juaracoding.dikahadir.pages.AddEditShiftingModalPage;
import com.juaracoding.dikahadir.pages.DashboardPage;
import com.juaracoding.dikahadir.pages.LogInPage;
import com.juaracoding.dikahadir.pages.ManagementShiftingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    private String testShiftName; // Nama shift untuk E2E

    // ================== SETUP & TEARDOWN ==================

    @BeforeClass
    public static void setUp() {
        // Driver path dihapus, asumsi driver ada di PATH atau dikelola WebDriverManager
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        logInPage = new LogInPage(driver);
        dashboardPage = new DashboardPage(driver);
        managementShiftingPage = new ManagementShiftingPage(driver);
        addEditShiftingModalPage = new AddEditShiftingModalPage(driver); // Pastikan ini versi JavascriptExecutor
    }

    @BeforeMethod
    public void loginAndNavigateToShifting() {
        driver.get("https://magang.dikahadir.com/authentication/login");

        // --- MENGGUNAKAN METHOD LOGIN YANG LEBIH SINGKAT ---
        logInPage.login("admin@hadir.com", "MagangSQA_JC@123");
        // ---------------------------------------------------

        // Tunggu menu management muncul setelah login
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.buttonMenuManagement));

        // Navigasi ke Management -> Shifting
        WebElement menuManagement = driver.findElement(dashboardPage.buttonMenuManagement);
        menuManagement.click();

        WebElement subMenuShifting = wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.subMenuShifting));
        subMenuShifting.click();

        // Verifikasi sudah di halaman Shifting
        wait.until(ExpectedConditions.visibilityOfElementLocated(managementShiftingPage.headerTitle));
        Assert.assertEquals(managementShiftingPage.getHeaderTitle(), "Shifting", "Gagal navigasi ke halaman Shifting.");
    }

    // ================== END-TO-END TEST CASE ==================

    @Test(priority = 1, description = "Test E2E: Tambah, Verifikasi, Edit, Verifikasi, Hapus, Verifikasi Shift")
    public void testShiftingE2E_AddEditDelete() {

        // --- DATA UNTUK TEST ---
        String namaUnit = "1 edit"; // Pastikan unit ini ada
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
        addEditShiftingModalPage.selectUnit(namaUnit);
        addEditShiftingModalPage.selectJamMasuk("8", "0");
        addEditShiftingModalPage.selectJamKeluar("17", "0");
        addEditShiftingModalPage.selectBreakStart("12", "0");
        addEditShiftingModalPage.selectBreakEnd("13", "0");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        addEditShiftingModalPage.clickTambahButton();

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
        managementShiftingPage.clickEditButtonByName(namaShiftAdd);
        Assert.assertTrue(addEditShiftingModalPage.isModalDisplayed(), "Modal Edit Shift tidak muncul.");
        WebElement namaInput = wait
                .until(ExpectedConditions.visibilityOfElementLocated(addEditShiftingModalPage.inputNamaShift));
        namaInput.clear();
        namaInput.sendKeys(namaShiftEdit);
        WebElement codeInput = wait
                .until(ExpectedConditions.visibilityOfElementLocated(addEditShiftingModalPage.inputCodeShifting));
        codeInput.clear();
        codeInput.sendKeys(codeShiftEdit);
        addEditShiftingModalPage.selectJamMasuk("9", "30");
        addEditShiftingModalPage.selectJamKeluar("18", "30");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        addEditShiftingModalPage.clickTambahButton(); // Tombol di modal edit bisa jadi "Simpan"

        // --- 4. Verify Edit ---
        System.out.println("Running E2E Step 4: Verify Edited Shift");
        String successMsgEdit = managementShiftingPage.getSuccessMessage();
        Assert.assertTrue(successMsgEdit.contains("berhasil diubah"), "Pesan sukses edit shift tidak sesuai.");
        managementShiftingPage.inputSearch(namaShiftEdit);
        managementShiftingPage.clickSearchButton();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//tbody/tr"), 1));
        WebElement editedShiftRow = driver
                .findElement(By.xpath(String.format("//tbody//p[text()='%s']/ancestor::tr", namaShiftEdit)));
        Assert.assertTrue(editedShiftRow.isDisplayed(), "Shift editan tidak ditemukan.");
        Assert.assertEquals(editedShiftRow.findElement(By.xpath("./td[3]/p")).getText(), "09:30:00",
                "Jam masuk editan tidak sesuai.");
        Assert.assertEquals(editedShiftRow.findElement(By.xpath("./td[4]/p")).getText(), "18:30:00",
                "Jam selesai editan tidak sesuai.");
        managementShiftingPage.clickResetButton();
        System.out.println("Shift berhasil diedit menjadi '" + namaShiftEdit + "'.");
        testShiftName = namaShiftEdit;

        // --- 5. Delete Shift ---
        System.out.println("Running E2E Step 5: Delete Shift");
        managementShiftingPage.clickDeleteButtonByName(testShiftName);
        By confirmationDialogTitle = By.xpath("//h2[contains(text(),'Hapus Data')]"); // GANTI LOCATOR JIKA PERLU
        By confirmDeleteButton = By.xpath("//button[normalize-space()='Hapus']"); // GANTI LOCATOR JIKA PERLU
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
        }
        boolean isShiftPresent;
        try {
            driver.findElement(By.xpath(String.format("//tbody//p[text()='%s']", testShiftName)));
            isShiftPresent = true;
        } catch (NoSuchElementException e) {
            isShiftPresent = false;
        }
        Assert.assertFalse(isShiftPresent, "Shift masih ditemukan setelah dihapus.");
        managementShiftingPage.clickResetButton();
        System.out.println("Shift '" + testShiftName + "' berhasil dihapus.");
    }

    // --- Test Lain (Search & Pagination) ---
    @Test(priority = 7, description = "Test search functionality separately")
    public void testSearchFunctionality() {
        System.out.println("Running test: testSearchFunctionality");
        String searchKeyword = "Shift Pagi"; // GANTI dengan nama shift yang pasti ada

        managementShiftingPage.inputSearch(searchKeyword);
        managementShiftingPage.clickSearchButton();

        // Tunggu hingga setidaknya satu baris hasil muncul di tabel DOM
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//tbody/tr"), 0));

        // --- PERBAIKI XPATH DAN TAMBAHKAN SCROLL ---
        By firstResultLocator = By.xpath("//tbody/tr[1]/td[2]//p"); // Locator untuk hasil pertama

        try {
            WebElement firstRowShiftNameElement = driver.findElement(firstResultLocator);

            // Scroll elemen ke view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstRowShiftNameElement);

            // Beri jeda singkat setelah scroll (opsional tapi bisa membantu)
            Thread.sleep(500);

            // Sekarang elemen seharusnya visible, ambil teksnya dan assert
            String actualShiftName = firstRowShiftNameElement.getText();
            Assert.assertTrue(actualShiftName.contains(searchKeyword),
                    "Hasil search (" + actualShiftName + ") tidak sesuai dengan keyword (" + searchKeyword + ").");

        } catch (NoSuchElementException e) {
            Assert.fail("Elemen hasil search tidak ditemukan dengan locator: " + firstResultLocator);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Assert.fail("Thread sleep terinterupsi saat menunggu scroll.");
        }

        managementShiftingPage.clickResetButton();
        System.out.println("Finished test: testSearchFunctionality");
    }

    @Test(priority = 8, description = "Test pagination functionality separately")
    public void testPaginationFunctionality() {
        System.out.println("Running test: testPaginationFunctionality");
        WebElement paginationDisplay = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".MuiTablePagination-displayedRows")));
        String initialDisplay = paginationDisplay.getText();
        WebElement nextButton = driver.findElement(dashboardPage.nextPageButton);

        if (!nextButton.isEnabled()) {
            System.out.println("Skipping pagination test: Only one page detected.");
            return;
        }

        managementShiftingPage.clickNextPage();
        wait.until(
                ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(paginationDisplay, initialDisplay)));
        String nextDisplay = paginationDisplay.getText();
        Assert.assertNotEquals(initialDisplay, nextDisplay, "Pagination tidak berubah setelah klik Next.");
        Assert.assertTrue(driver.findElement(dashboardPage.previousPageButton).isEnabled(),
                "Tombol Previous seharusnya enable.");
        System.out.println("Pindah ke halaman berikutnya: " + nextDisplay);

        managementShiftingPage.clickPreviousPage();
        wait.until(ExpectedConditions.textToBePresentInElement(paginationDisplay, initialDisplay));
        String previousDisplay = paginationDisplay.getText();
        Assert.assertEquals(initialDisplay, previousDisplay, "Pagination tidak kembali ke awal.");
        Assert.assertFalse(driver.findElement(dashboardPage.previousPageButton).isEnabled(),
                "Tombol Previous seharusnya disable lagi.");
        System.out.println("Kembali ke halaman pertama: " + previousDisplay);
        System.out.println("Finished test: testPaginationFunctionality");
    }

    // ================== CLEANUP ==================

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}