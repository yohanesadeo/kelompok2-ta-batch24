package com.juaracoding.dikahadir.management.shiftings;

import org.testng.Assert;

import com.juaracoding.dikahadir.helpers.NavigationHelper;
import com.juaracoding.dikahadir.pages.ManagementShiftingPage;
import com.juaracoding.dikahadir.utils.DriverUtil;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ResetShiftingTest {
    ManagementShiftingPage shiftingPage;

    @Given("Pengguna melakukan login dan pegguna sudah berada di halaman Shifting")
    public void userIsOnShiftingPage(){
        NavigationHelper helper = new NavigationHelper(DriverUtil.getDriver());
        shiftingPage = helper.loginAndGoToShifting();
    }

    @When("Masukkan nama Shifting {string} pada kolom pencarian")
    public void userInputName(String nama){
        shiftingPage.setSearch(nama);
    }

    @And("Klik tombol Reset pada halaman Shifting")
    public void userClicksResetButton(){
        shiftingPage.clickReset();
    }

    @Then("Kolom pencarian kosong pada halaman Shifting")
    public void searchBoxShouldBeEmpty(){
        String actual = shiftingPage.getSearchBoxText();
        Assert.assertEquals(actual, "", "Kolom pencarian seharusnya kosong setelah reset!");
    }
}
