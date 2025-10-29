package com.juaracoding.dikahadir.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.juaracoding.dikahadir.repository.ShiftingRepo;
import com.juaracoding.dikahadir.utils.DriverUtil;

public class ManagementShiftingPage {
    private WebDriver driver;
    private ShiftingRepo repo;
    private WebDriverWait wait;

    public ManagementShiftingPage(WebDriver driver) {
        this.driver = driver;
        repo = new ShiftingRepo();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this.repo);
    }

    // ========================= Submenu =========================
    public void clikSubMenuShifting() {
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(repo.clikSubMenuShifting)).click();
    }

    // ========================= CRUD =========================
    public void clickTambahSfitting() {
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(repo.clickTambahShifting)).click();
    }

    // set name
    public void setName(String value) {
        repo.name.sendKeys(value);
    }

    public void setOpsiUnitKerja(String unitKerja) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        if (unitKerja != null && !unitKerja.trim().isEmpty()) {
            repo.pilihUnitKerja.click();

            wait.until(ExpectedConditions.visibilityOfAllElements(repo.opsiUnitKeja));

            for (WebElement opsi : repo.opsiUnitKeja) {
                if (opsi.getText().contains(unitKerja)) {
                    opsi.click();
                    break;
                }
            }
        }
    }

    public void setkodeShifting(String value) {
        repo.kodeShifting.sendKeys(value);
    }

    public void clickButtonTambah() {
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(10));

        // Tunggu tombol Tambah bisa diklik
        By tambahButtonLocator = By.xpath("//button[@type='submit' and normalize-space(text())='Tambah']");
        WebElement tambahButton = wait.until(ExpectedConditions.elementToBeClickable(tambahButtonLocator));

        // Klik pakai JS biar lebih stabil (hindari ripple/overlay MUI)
        ((JavascriptExecutor) DriverUtil.getDriver()).executeScript("arguments[0].click();", tambahButton);
    }

    public void performShifting() {
        setName("budi");
        setOpsiUnitKerja("BCA Biro Keuangan dan SDM");
        clickIconJamMasuk();
        clickIconJamKeluar();
        setkodeShifting("kode Test");
        clickIconBreakStart();
        clickButtonTambah();

    }

    public void performShifting(String name,
            String unitKerja,
            String jamMasukInput,
            int menitMasukInput,
            String jamkKeluarInput,
            int menitKeluarInput,
            String kodeShifting,
            String jamBreakStartInput,
            int menitBreakStartInput,
            String jamBreakEndInput,
            int menitBreakEndInput) {
        setName(name);
        setOpsiUnitKerja(unitKerja);
        clickIconJamMasuk();
        pilihJam(jamMasukInput);
        pilihMenit(menitMasukInput);
        clickIconJamKeluar();
        pilihJam(jamkKeluarInput);
        pilihMenit(menitKeluarInput);
        setkodeShifting(kodeShifting);
        clickIconBreakStart();
        pilihJam(jamBreakStartInput);
        pilihMenit(menitBreakStartInput);
        clickIconBreakEnd();
        pilihJam(jamBreakEndInput);
        pilihMenit(menitBreakEndInput);
        ;
        clickButtonTambah();
    }

    // ============================ AKSES JAM =================================
    // ==================== KLIK LOGO JAM KELUAR (INDEX 0) ====================
    public void clickIconJamMasuk() {
        WebElement jamMasuk = wait.until(ExpectedConditions.elementToBeClickable(repo.iconJamList.get(0)));
        wait.until(ExpectedConditions.elementToBeClickable(jamMasuk));
        jamMasuk.click();
    }

    // ==================== KLIK LOGO JAM KELUAR (INDEX 1) ====================
    public void clickIconJamKeluar() {
        WebElement jamKeluar = wait.until(ExpectedConditions.elementToBeClickable(repo.iconJamList.get(1)));
        jamKeluar.click();
    }

    // ==================== KLIK LOGO BREAK START (INDEX 2) ====================
    public void clickIconBreakStart() {
        WebElement jamBreakStart = wait.until(ExpectedConditions.elementToBeClickable(repo.iconJamList.get(2)));
        jamBreakStart.click();
    }

    // ==================== KLIK LOGO BREAK START (INDEX 3) ====================
    public void clickIconBreakEnd() {
        WebElement jamBreakEnd = wait.until(ExpectedConditions.elementToBeClickable(repo.iconJamList.get(3)));
        jamBreakEnd.click();
    }

    // ==================== SET JAM ====================
    public void pilihJam(String jamInput) {
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(10));

        By jamLocator = By.cssSelector("span[aria-label='" + jamInput + " hours']");
        WebElement jamElement = wait.until(ExpectedConditions.visibilityOfElementLocated(jamLocator));

        Actions actions = new Actions(DriverUtil.getDriver());
        actions.moveToElement(jamElement).click().perform();
    }

    // ==================== SET MENIT ====================
    public void pilihMenit(int menitInput) {
        if (menitInput < 0 || menitInput > 59) {
            throw new IllegalArgumentException("Menit harus di antara 0–59");
        }

        WebDriver driver = DriverUtil.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        if (menitInput % 5 == 0) {
            // untuk kelipatan 5 → pakai format 2 digit
            String menitStr = String.format("%02d", menitInput);

            By menitLocator = By.xpath(
                    "//span[contains(@class,'MuiClockNumber-root') and @role='option' and @aria-label='"
                            + menitStr + " minutes']");

            WebElement menitElement = wait.until(
                    ExpectedConditions.elementToBeClickable(menitLocator));

            new Actions(driver).moveToElement(menitElement).click().perform();
        } else {
            // Kalau bukan kelipatan 5 → geser pointer manual
            WebElement pointer = driver.findElement(
                    By.xpath("(//div[contains(@class,'MuiClockPointer-root')])[last()]"));
            double degree = menitInput * 6; // 1 menit = 6 derajat

            String script = "arguments[0].style.transform='rotateZ(" + degree + "deg)';";
            js.executeScript(script, pointer);

            new Actions(driver).click(pointer).release().perform();
        }
    }

    // ========================= Search / Reset =========================
    public void setSearch(String value) {
        WebElement inputNama = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
        inputNama.clear();
        inputNama.sendKeys(value);

        // By searchBox = By.id("search");
        // WebElement inputNama =
        // wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        // inputNama.clear();
        // inputNama.sendKeys(value);
    }

    public void clickSearch() {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        WebElement button = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[@type='submit' and normalize-space(text())='Search']")));
        js.executeScript("arguments[0].click();", button);
    }

    public void clickReset() {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();

        // Tunggu tombol reset clickable
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.btn-reset")));

        // Scroll ke tombol
        js.executeScript("arguments[0].scrollIntoView(true);", button);

        // Klik tombol via JS
        js.executeScript("arguments[0].click();", button);
    }

    public String getSearchBoxText() {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
        return input.getAttribute("value");
    }

    // ========================= Hapus =================================
    public void clickButtonHapus(String nama) {
        // 1. Cari baris yang sesuai nama (di dalam <h6>)
        WebElement row = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//tr[td/h6[normalize-space(text())='" + nama + "']]")));

        // 2. Klik tombol "more" di baris yang benar
        WebElement moreLogo = row.findElement(By.cssSelector("svg.feather.feather-more-vertical"));
        wait.until(ExpectedConditions.elementToBeClickable(moreLogo));
        moreLogo.click();

        // 3. Tunggu menu Delete muncul
        WebElement hapusBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(@class,'MuiMenuItem-root') and normalize-space(text())='Delete']")));
        wait.until(ExpectedConditions.elementToBeClickable(hapusBtn));

        // 4. Klik tombol Delete via JS agar aman jika ada overlay
        ((JavascriptExecutor) DriverUtil.getDriver()).executeScript("arguments[0].click();", hapusBtn);
    }

    // ========================= Edit =================================
    public void editShifting(String value) {
        WebElement editDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("code")));
        editDescription.clear();
        editDescription.sendKeys(value);
    }

    public void clikButtonEdit() {
        // Klik logo "more" (SVG) -> pakai Selenium click
        WebElement moreLogo = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("svg.feather.feather-more-vertical")));
        moreLogo.click();

        // Tunggu menu Edit muncul
        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[contains(@class,'MuiMenuItem-root') and normalize-space(text())='Edit']")));

        // Klik tombol Edit (via JS agar aman)
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        js.executeScript("arguments[0].click();", editBtn);
    }

    // ========================= Confirm =============================
    public void clickYaConfirm() {
        WebElement clickYaButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class,'MuiButton-containedPrimary') and normalize-space(text())='Ya']")));
        clickYaButton.click();
    }

    // ========================= Simpan =========================

    public void buttonSimpan() {
        WebElement clickButtonSimpan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//button[normalize-space(text())='Simpan']")));
        clickButtonSimpan.click();
    }

    // ========================= Message PopUp =========================
    public String getPopupBerhasilHapusShifting() {
        WebElement popUp = wait.until(ExpectedConditions.visibilityOf(repo.popupBerhasilHapus));
        return popUp.getText();
    }

    // ========================= Table / Row =========================
    public void waitForTableHeaders(String nameToWait) {
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(10));

        // Tunggu elemen <h6> dengan teks sesuai parameter
        WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[normalize-space(text())='" + nameToWait + "']")));

        // Cetak teks untuk verifikasi
        System.out.println("Nama muncul: " + nameElement.getText());
    }

    public String getDataBarisPertama(String expectedNama) {
        int retries = 3;

        while (retries > 0) {
            try {
                // Tunggu baris pertama muncul
                WebElement firstRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("tbody.MuiTableBody-root tr:first-child")));

                // Ambil kolom pertama (nama) langsung
                WebElement firstCell = firstRow.findElement(By.cssSelector("td:first-child"));

                // Tunggu sampai teks sesuai (case-insensitive)
                wait.until(driver -> firstCell.getText().equalsIgnoreCase(expectedNama));

                String actualNama = firstCell.getText();
                System.out.println("Nama di baris pertama: " + actualNama);
                return actualNama;

            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                retries--;
                System.out.println("Retrying due to stale element, attempts left: " + retries);
            }
        }

        throw new RuntimeException("Failed to get first row data due to stale element.");
    }

    public boolean isNamaExist(String expectedNama) {
        // Ambil semua row di table
        List<WebElement> rows = DriverUtil.getDriver().findElements(
                By.cssSelector("tbody.MuiTableBody-root tr"));

        for (WebElement row : rows) {
            try {
                // Ambil kolom pertama (nama)
                WebElement namaCell = row.findElement(By.cssSelector("td:nth-child(1) h6"));
                String actualNama = namaCell.getText().trim();

                if (actualNama.equalsIgnoreCase(expectedNama)) {
                    return true; // ketemu
                }
            } catch (Exception e) {
                // kalau ada row kosong atau tidak sesuai format, skip aja
                continue;
            }
        }
        return false; // tidak ditemukan
    }

    // ========================= Pagination =========================
    public void setRowsPerPage(String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // target hidden input
        WebElement inputHidden = driver.findElement(
                By.cssSelector("input.MuiSelect-nativeInput"));

        // set value ke hidden input
        js.executeScript("arguments[0].value='" + value + "';", inputHidden);

        // trigger event change supaya table reload
        js.executeScript(
                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                inputHidden);

        // tunggu sampai row table berubah sesuai value (atau kurang dari value)
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.numberOfElementsToBeLessThan(
                        By.cssSelector("tbody.MuiTableBody-root tr"),
                        Integer.parseInt(value) + 1 // toleransi kalau data kurang dari value
                ));
    }

    public int getTableRowCount() {
        return repo.tableRows.size();
    }

    public boolean verifyRowsPerPage(int expected) {
        int actual = getTableRowCount();
        return actual <= expected;
    }

    public boolean isShiftingExist(String nama) {
        int retries = 3;

        while (retries > 0) {
            try {
                // tunggu semua baris tabel muncul
                wait.until(ExpectedConditions.visibilityOfAllElements(repo.tableRows));

                for (WebElement row : repo.tableRows) {
                    // ambil kolom nama (misal kolom pertama)
                    WebElement namaCell = row.findElement(By.cssSelector("td:first-child"));

                    // tunggu teks tersedia
                    wait.until(driver -> !namaCell.getText().isEmpty());

                    if (namaCell.getText().trim().equalsIgnoreCase(nama)) {
                        return true;
                    }
                }

                return false; // jika tidak ada yang cocok

            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                retries--; // retry jika elemen stale
            }
        }

        // jika semua retry gagal karena stale element
        throw new RuntimeException("Failed to verify absen point due to stale element.");
    }

    public String getShiftingCode(String expectedText) {
        WebDriver driver = DriverUtil.getDriver();
        // Cari elemen <h6> dengan teks sesuai expectedText
        By locator = By.xpath("//td//h6[normalize-space(text())='" + expectedText + "']");

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        // return teks yang ditemukan
        return element.getText();
    }

    // Klik tombol "Next Page"
    public void clickNextPage() {
        By nextBtn = By.xpath("//button[@title='Go to next page']");
        WebElement next = wait.until(ExpectedConditions.elementToBeClickable(nextBtn));
        next.click();
        waitForTableReload();
    }

    // Utility tunggu table reload (row pertama hadir ulang)
    private void waitForTableReload() {
        By firstRowLocator = By.xpath("//table/tbody/tr[1]/td[1]/h6");
        wait.until(ExpectedConditions.presenceOfElementLocated(firstRowLocator));
    }

    public void clickPreviousPage() {
        By prevBtn = By.xpath("//button[@title='Go to previous page']");
        WebElement prev = wait.until(ExpectedConditions.elementToBeClickable(prevBtn));
        prev.click();
        waitForTableReload();
    }

    // Ambil data baris pertama kolom pertama
    public String getFirstRowData() {
        By firstRowLocator = By.xpath("//table/tbody/tr[1]/td[1]/h6");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstRowLocator))
                .getText();
    }

    public boolean isDataChangedAfterNext() {
        String before = getFirstRowData();
        clickNextPage();

        // Tunggu sampai text row berubah dari 'before'
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(By.xpath("//table/tbody/tr[1]/td[1]/h6"), before)));

        String after = getFirstRowData();
        return !before.equals(after);
    }

    // Validasi data berubah setelah klik Previous
    public boolean isDataChangedAfterPrevious() {
        String before = getFirstRowData();
        clickPreviousPage();

        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(By.xpath("//table/tbody/tr[1]/td[1]/h6"), before)));

        String after = getFirstRowData();
        return !before.equals(after);
    }

    public void clickLastPage() {
        By lastBtn = By.xpath("//button[@title='Go to last page']");
        WebElement lastPage = wait.until(ExpectedConditions.elementToBeClickable(lastBtn));
        lastPage.click();
        waitForTableReload();
    }

    public void clickFirstPage() {
        By firstBtn = By.xpath("//button[@title='Go to first page']");
        WebElement lastPage = wait.until(ExpectedConditions.elementToBeClickable(firstBtn));
        lastPage.click();
        waitForTableReload();
    }

    // Validasi data berubah setelah klik last page
    public boolean isDataChangedAfterLast() {
        String before = getFirstRowData();
        clickLastPage();

        // Tunggu sampai text row berubah dari 'before'
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(By.xpath("//table/tbody/tr[1]/td[1]/h6"), before)));

        String after = getFirstRowData();
        return !before.equals(after);
    }

    // Validasi data berubah setelah klik first page
    public boolean isDataChangedAfterFirst() {
        String before = getFirstRowData();
        clickFirstPage();

        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(By.xpath("//table/tbody/tr[1]/td[1]/h6"), before)));

        String after = getFirstRowData();
        return !before.equals(after);
    }

    // ========================= getMassageError =========================

    public String getNamaShiftingErrorMessage() {
        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("name-helper-text")));
        return errorMessage.getText().trim();
    }

    public String getJamMasukShiftingErrorMessage() {

        WebElement errorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//p[contains(@class,'MuiFormHelperText-root') and text()='Jam masuk harus diisi!']")));

        return errorElement.getText();
    }

    public void performShiftingNegativeJamMasuk(String name,
            String unitKerja,
            String jamkKeluarInput,
            int menitKeluarInput,
            String kodeShifting,
            String jamBreakStartInput,
            int menitBreakStartInput,
            String jamBreakEndInput,
            int menitBreakEndInput) {
        setName(name);
        setOpsiUnitKerja(unitKerja);
        clickIconJamKeluar();
        pilihJam(jamkKeluarInput);
        pilihMenit(menitKeluarInput);
        setkodeShifting(kodeShifting);
        clickIconBreakStart();
        pilihJam(jamBreakStartInput);
        pilihMenit(menitBreakStartInput);
        clickIconBreakEnd();
        pilihJam(jamBreakEndInput);
        pilihMenit(menitBreakEndInput);
        ;
        clickButtonTambah();
    }

    public String getCodeShiftingShiftingErrorMessage() {
        WebElement errorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("code-helper-text")));
        return errorElement.getText();
    }

}
