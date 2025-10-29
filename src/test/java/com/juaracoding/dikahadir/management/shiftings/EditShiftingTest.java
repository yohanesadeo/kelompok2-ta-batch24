package com.juaracoding.dikahadir.management.shiftings;

import org.testng.Assert;

import com.juaracoding.dikahadir.helpers.NavigationHelper;
import com.juaracoding.dikahadir.pages.ManagementShiftingPage;
import com.juaracoding.dikahadir.utils.DriverUtil;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EditShiftingTest {
    ManagementShiftingPage shiftingPage;

    @Given("Pengguna sudah melakukan login dan berada di halaman Shifting")
    public void userIsOnShiftingPage(){
        NavigationHelper helper = new NavigationHelper(DriverUtil.getDriver());
        shiftingPage = helper.loginAndGoToShifting();
    }

    @And("Shifting bernama {string} sudah ada")
    public void verifyDataShifting(String nama){
        shiftingPage.setSearch(nama);
        shiftingPage.clickSearch();
        boolean exists = shiftingPage.isShiftingExist(nama);
        Assert.assertTrue(exists, "Shifting " + nama + " tidak ditemukan!");
    }

    @When("Pengguna mencari {string} di bagian search pada halaman shifting")
    public void SearchShifting(String nama){
        shiftingPage.setSearch(nama);
        shiftingPage.clickSearch();
    }

    @And("Pengguna klik ikon titik tiga pada halaman Shifting di sebelah kanan dan memilih opsi Edit")
    public void clikEdit(){
        shiftingPage.clikButtonEdit();;
    }

    @And("Pengguna mengubah kode shifting {string}")
    public void editKodeShifting(String kode){
        shiftingPage.editShifting(kode);
    }

    @And("Pengguna melakukan klik tombol simpan")
    public void clickSaveData(){
        shiftingPage.buttonSimpan();
    }
    

    @Then("Cek pada tabel shifting bernama {string} untuk melihat apakah kode shifting {string} sudah terganti")
    public void confirmDataInTable(String nama, String expectedkode){
        shiftingPage.setSearch(nama);
        shiftingPage.clickSearch();
        shiftingPage.getShiftingCode(expectedkode);

        String actualKode = shiftingPage.getShiftingCode(expectedkode);

        // Assertion untuk validasi hasil edit
        Assert.assertEquals(actualKode, expectedkode);
    }
}

