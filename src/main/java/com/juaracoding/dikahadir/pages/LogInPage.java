package com.juaracoding.dikahadir.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInPage {
    // Locator
    private By username = By.id("email");
    private By password = By.id("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor

    public LogInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method

    public void fillUsername(String username) {
        driver.findElement(this.username).sendKeys(username);
    }

    public void fillPassword(String password) {
        driver.findElement(this.password).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(this.loginButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
    }

    public void login(String username, String password) {
        fillUsername(username);
        fillPassword(password);
        clickLoginButton();
        // Menunggu sampai URL berubah ke halaman dashboard setelah login
        wait.until(ExpectedConditions.urlToBe("https://magang.dikahadir.com/dashboards/pending"));
    }
}
