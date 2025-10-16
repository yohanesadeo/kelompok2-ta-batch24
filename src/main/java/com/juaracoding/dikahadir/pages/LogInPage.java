package com.juaracoding.dikahadir.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInPage {
    //Locator
    private By username = By.id("email");
    private By password = By.id("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private WebElement element;

    //Constructor

    public LogInPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));
        js = (JavascriptExecutor) driver;
    }

    //Method
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
    }
}
