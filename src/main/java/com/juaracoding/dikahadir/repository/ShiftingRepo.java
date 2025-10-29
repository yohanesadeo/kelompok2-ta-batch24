package com.juaracoding.dikahadir.repository;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ShiftingRepo {
    // elemen submenu shifthing
    @FindBy(xpath = "//p[normalize-space()='Shifting']")
    public WebElement clikSubMenuShifting;

    // elemen tambahkan
    @FindBy(xpath = "//button[normalize-space()='Tambahkan']")
    public WebElement clickTambahShifting;

    // elemen name
    @FindBy(id = "name")
    public WebElement name;

    // elemen pilih unit
    @FindBy(id = "job_departement")
    public WebElement pilihUnitKerja;

    // elemen opsi pilih unit
    @FindBy(xpath = "//li[@role='option']")
    public List<WebElement> opsiUnitKeja;

    // elemen icon jam Masuk dan jam keluar
    @FindBy(xpath = "//button[.//*[name()='svg' and @data-testid='AccessAlarmIcon']]")
    public List<WebElement> iconJamList;

    // elemen code shifting
    @FindBy(id = "code")
    public WebElement kodeShifting;

    //klik tombol tambah
    @FindBy(xpath = "//button[@type='submit' and normalize-space(text())='Tambah']")
    public WebElement btnTambah;

    @FindBy (id = "search")
    public WebElement searchBox;

    @FindBy (xpath = "//button[@type='submit' and normalize-space(text())='Search']")
    public WebElement searchButton;

    @FindBy(xpath = "//button[@type='submit' and normalize-space(text())='Reset']")
    public WebElement resetButton;

     // popup pesan data berhasil dihapus
    @FindBy(xpath = "//div[contains(@class,'MuiSnackbarContent-message') and normalize-space(text())='Berhasil Delete Shifting']")
    public WebElement popupBerhasilHapus;

    // elemen tabel keseluruhan
    @FindBy(css = "tbody.MuiTableBody-root tr")
    public List<WebElement> tableRows;
}
