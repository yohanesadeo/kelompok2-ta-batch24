package com.juaracoding.dikahadir.management.shiftings;

import org.testng.Assert;

import com.juaracoding.dikahadir.helpers.NavigationHelper;
import com.juaracoding.dikahadir.pages.ManagementShiftingPage;
import com.juaracoding.dikahadir.utils.DriverUtil;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PaginationShiftingTest {
    ManagementShiftingPage shiftingPage;

    @Given("Pengguna sudah malakukan login dan pengguna di halaman Shifting")
    public void userIsOnShiftingPage(){
        NavigationHelper helper = new NavigationHelper(DriverUtil.getDriver());
        shiftingPage = helper.loginAndGoToShifting();
    }

    @When("Pengguna klik dropdown dan memilih jumlah data per halaman {string} pada halaman Shifting")
    public void klikDropdownPerPage(String page){
        shiftingPage.setRowsPerPage(page);
    }

    @Then("Jumlah Absen point yang ditampilkan per halaman berubah menjadi {int} pada halaman Shifting")
    public void expectedPage(int expected){
        Assert.assertTrue(shiftingPage.verifyRowsPerPage(expected));
    }

    // =======================================================================
    @When("Pengguna klik tombol next  pada halaman Shifting")
    public void clickNext(){
        shiftingPage.clickNextPage();
    }

    @And("Pengguna klik tombol previous pada halaman Shifting")
    public void clickPreviousPage(){
        shiftingPage.clickPreviousPage();
    }

    @Then("Sistem menampilkan halaman Shifting berikutnya dan mendapatkan data dibaris pertama")
    public void getPageNext(){
        Assert.assertTrue(shiftingPage.isDataChangedAfterNext());
    }

    // @And("Sistem menampilkan halaman Shifting sebelumnya dan mendapatkan data dibaris pertama")
    // public void getPageProvious(){
    //      Assert.assertTrue(shiftingPage.isDataChangedAfterPrevious());
    // }

    // =================================================================================
    @When("Pengguna klik tombol last pada halaman Shifting")
    public void clickLastPage(){
        shiftingPage.clickLastPage();
    }

    @And("Pengguna klik tombol first pada halaman Shifting")
    public void clickFirstPage(){
        shiftingPage.clickFirstPage();
    }

    @Then("Sistem menampilkan halaman Shifting terakhir serta mendapatkan data dibaris pertama")
    public void getLastPage(){
        Assert.assertTrue(shiftingPage.isDataChangedAfterLast());
    }

    // @And("Sistem menampilkan halaman Shifting pertama serta mendapatkan data dibaris pertama")
    // public void getFisrtsPage(){
    //     Assert.assertTrue(shiftingPage.isDataChangedAfterFirst());
    // }

}
