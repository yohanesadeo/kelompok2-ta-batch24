package com.juaracoding.dikahadir.pages;

import org.openqa.selenium.By;

public class DashboardPage {
    //Locator
    private By buttonMenuManagement = By.xpath("//div[contains(@class, 'sidebar__wrapper')][.//p[text()='Management']]");
    private By buttonMenuUser = By.xpath("//div[contains(@class, 'sidebar__wrapper')][.//p[text()='User']]");
    private By searchBar = By.xpath("//input[@id='search']");
    private By searchButton = By.xpath("//button[normalize-space()='Search']");

}
