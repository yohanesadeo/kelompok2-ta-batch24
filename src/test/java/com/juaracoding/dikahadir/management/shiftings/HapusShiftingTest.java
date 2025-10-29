package com.juaracoding.dikahadir.management.shiftings;

import org.testng.Assert;

import com.juaracoding.dikahadir.helpers.NavigationHelper;
import com.juaracoding.dikahadir.pages.ManagementShiftingPage;
import com.juaracoding.dikahadir.utils.DriverUtil;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HapusShiftingTest {
    
    ManagementShiftingPage shiftingPage;
    String namaShifting;

    @Given("Pengguna sudah login dan berada dihalaman Shifting")
    public void userInOnShiftingPage(){
        NavigationHelper helper = new NavigationHelper(DriverUtil.getDriver());
        shiftingPage = helper.loginAndGoToShifting();
    }

    @When("Pengguna melakukan search pada bagian halaman Shifting {string}")
    public void searchShifting(String nama){
        this.namaShifting = nama;
        shiftingPage.setSearch(nama);
        shiftingPage.clickSearch();
    }

    @And("Pengguna klik ikon titik tiga pada halaman Shifting di sebelah kanan dan memilih opsi Hapus")
    public void clickHapus(){
        shiftingPage.clickButtonHapus(namaShifting);
    }

    @And("Pengguna mendapatkan konfirmasi Ya penghapusan")
    public void confirmationHapus(){
        shiftingPage.clickYaConfirm();
    }

    @Then("Pengguna mendapatkan popup berisi {string}")
    public void getPopupMessage(String expected){
        String actual = shiftingPage.getPopupBerhasilHapusShifting();
        Assert.assertEquals(actual, expected, "Pesan popup tidak sesuai!");
    }
}
