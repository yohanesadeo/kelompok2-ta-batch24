package com.juaracoding.dikahadir.management.shiftings;

import org.testng.Assert;

import com.juaracoding.dikahadir.helpers.NavigationHelper;
import com.juaracoding.dikahadir.pages.HeaderPage;
import com.juaracoding.dikahadir.pages.ManagementShiftingPage;
import com.juaracoding.dikahadir.utils.DriverUtil;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TambahShiftingTest {
   ManagementShiftingPage shiftingPage;
   HeaderPage headerPage;

    @Given("Pengguna sudah melakukan login dan pengguna sudah berada di halaman Shifting")
    public void userIsOnShiftingPage(){
        NavigationHelper helper = new NavigationHelper(DriverUtil.getDriver());
        shiftingPage = helper.loginAndGoToShifting();
    }

    @When("Pengguna melakukan klik tombol Tambahkan pada halaman Shifting")
    public void clickAddShifting(){
        shiftingPage.clickTambahSfitting();
    }

    @And("Pengguna mengisi data {string}, {string}, {string}, {int}, {string}, {int}, {string}, {string}, {int}, {string}, {int} dan klik simpan")
    public void fillShiftingData(String nama, String pilihUnit, String jamMasuk, int menitMasuk,
                             String jamKeluar, int menitKeluar, String codeShifting,
                             String jamBreakStart, int menitBreakStart,
                             String jamBreakEnd, int menitBreakEnd){
        shiftingPage.performShifting(nama, pilihUnit, jamMasuk, menitMasuk, jamKeluar, menitKeluar, codeShifting, jamBreakStart, menitBreakStart, jamBreakEnd, menitBreakEnd);
    }

    
    @Then("Data berhasil ditambahkan dengan validasi bahwa {string} telah muncul pada tabel halaman Shifting")
    public void verifyDataAdded(String nama){
        shiftingPage.setSearch(nama);
        shiftingPage.clickSearch();
        boolean exists = shiftingPage.isShiftingExist(nama);
        Assert.assertTrue(exists, "Absen Point " + nama + " tidak ditemukan!");
    }
}
