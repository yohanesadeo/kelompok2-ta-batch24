package com.juaracoding.dikahadir.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.juaracoding.dikahadir.utils.DriverUtil;

public class HeaderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HeaderPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this.driver);
        
    }

    public void setSearch(String value) {
        WebElement inputNama = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
        inputNama.clear();
        inputNama.sendKeys(value);
    }

    public void clickSearch() {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and normalize-space(text())='Search']")));
        js.executeScript("arguments[0].scrollIntoView(true);", button);
        js.executeScript("arguments[0].click();", button);
    }

    public void clickReset() {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();

        // Tunggu tombol reset clickable
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-reset")));

        // Scroll ke tombol
        js.executeScript("arguments[0].scrollIntoView(true);", button);

        // Klik tombol via JS
        js.executeScript("arguments[0].click();", button);
    }

    public String getSearchBoxText() {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
        return input.getAttribute("value");
    }
}
