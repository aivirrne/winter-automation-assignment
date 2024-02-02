package com.shop;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactUsPage {

    WebDriver driver;
    By contactUsLinkLocator = By.xpath("//footer/div/div/div/ul/li[3]/a");
    By emailAddressLocator = By.xpath("//div/p/a");


    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
    }


    public String checkEmailOnContactsPage() {
        WebElement contactUsLink = waitForElementToBeVisible(contactUsLinkLocator);
        contactUsLink.click();
        WebElement emailAddress = waitForElementToBeVisible(emailAddressLocator);
        String emailAddressText = emailAddress.getText();
        return emailAddressText;
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
