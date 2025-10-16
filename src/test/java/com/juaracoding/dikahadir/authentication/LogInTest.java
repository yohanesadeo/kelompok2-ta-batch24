package com.juaracoding.dikahadir.authentication;
 
import com.juaracoding.dikahadir.pages.LogInPage;
import com.juaracoding.dikahadir.providers.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LogInTest extends BaseTest {


    @Test
    public void testLoginValid() throws InterruptedException{
        LogInPage loginPage = new LogInPage(getDriver());
        loginPage.login("admin@hadir.com", "MagangSQA_JC@123");
        Thread.sleep(10000);


        String actualURL = getDriver().getCurrentUrl();
        String expectedURL = "https://magang.dikahadir.com/dashboards/pending";

        Assert.assertEquals(actualURL, expectedURL);
    }
}


