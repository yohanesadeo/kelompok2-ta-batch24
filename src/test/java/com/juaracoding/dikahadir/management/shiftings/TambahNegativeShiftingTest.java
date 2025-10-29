package com.juaracoding.dikahadir.management.shiftings;

import org.testng.Assert;

import com.juaracoding.dikahadir.helpers.NavigationHelper;
import com.juaracoding.dikahadir.pages.ManagementShiftingPage;
import com.juaracoding.dikahadir.utils.DriverUtil;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TambahNegativeShiftingTest {
    ManagementShiftingPage shiftingPage;

    @Given("Pengguna sudah login ke aplikasi dan pengguna berada di halaman Shifting")
    public void userIsOnShiftingPage(){
        NavigationHelper helper = new NavigationHelper(DriverUtil.getDriver());
        shiftingPage = helper.loginAndGoToShifting();
    }

    @And("Pengguna mengisi form Tambah Shifting")
    public void clickAddShifting(){
        shiftingPage.clickTambahSfitting();
    }

    @When("Pengguna melakukan pengisian data, kosongkan {string}, {string}, {string}, {int}, {string}, {int}, {string}, {string}, {int}, {string}, {int} dan klik simpan")
    public void performShiftingWithoutName(String nama, String pilihUnit, String jamMasuk, int menitMasuk,
                             String jamKeluar, int menitKeluar, String codeShifting,
                             String jamBreakStart, int menitBreakStart,
                             String jamBreakEnd, int menitBreakEnd){
        shiftingPage.performShifting(nama, pilihUnit, jamMasuk, menitMasuk, jamKeluar, menitKeluar, codeShifting, jamBreakStart, menitBreakStart, jamBreakEnd, menitBreakEnd);
    }

    @Then("Sistem menampilkan pesan error {string} pada field Nama pada halaman shifting")
    public void getMessageName(String expectedMessage){
        String actualMessage = shiftingPage.getNamaShiftingErrorMessage();

        Assert.assertEquals(actualMessage, expectedMessage, "Pesan validasi tidak sesuai!");
    }

    // @When("Pengguna melakukan pengisian data {string}, {string}, {string}, {int}, {string}, {string}, {int}, {string}, {int} dan klik simpan")
    // public void performShiftingWithoutJamMasuk(String nama, String pilihUnit,
    //                          String jamKeluar, int menitKeluar, String codeShifting,
    //                          String jamBreakStart, int menitBreakStart,
    //                          String jamBreakEnd, int menitBreakEnd) {
    // shiftingPage.performShiftingNegativeJamMasuk(nama, pilihUnit, jamKeluar, menitKeluar, codeShifting, jamBreakStart, menitBreakStart, jamBreakEnd, menitBreakEnd);
    // }

    // @Then("Sistem menampilkan pesan error {string} pada field Jam Masuk")
    // public void getMessageJamMasuk(String expectedMessage){
    //     String actualMessage = shiftingPage.getJamMasukShiftingErrorMessage();
    //     Assert.assertEquals(actualMessage, expectedMessage, "Pesan Validasi tidak sesuai!");
    // }

    @When("Pengguna melakukan pengisian data {string}, {string}, {string}, {int}, {string}, {int}, kosongkan {string}, {string}, {int}, {string}, {int} dan klik simpan")
    public void performShiftingWithoutCodeShifting(String nama, String pilihUnit, String jamMasuk, int menitMasuk,
                             String jamKeluar, int menitKeluar, String codeShifting,
                             String jamBreakStart, int menitBreakStart,
                             String jamBreakEnd, int menitBreakEnd){
        shiftingPage.performShifting(nama, pilihUnit, jamMasuk, menitMasuk, jamKeluar, menitKeluar, codeShifting, jamBreakStart, menitBreakStart, jamBreakEnd, menitBreakEnd);
    }

    @Then("Sistem menampilkan pesan error {string} pada field Code Shifting")
    public void getMessageCodeShifting(String expectedMessage){
        String actualMessage = shiftingPage.getCodeShiftingShiftingErrorMessage();

        Assert.assertEquals(actualMessage, expectedMessage, "Pesan validasi tidak sesuai!");
    }
    

    

    



    
}
