package com.shop;

import org.openqa.selenium.Architecture;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners({ScreenshotListener.class})
public class ContactEmailTest extends BaseTest {

    private ContactUsPage contactUsPage;
    @BeforeMethod
    public void initializePageObject() {
        contactUsPage = new ContactUsPage(driver);
    }


    @Test(groups = "Regression")
    public void checkEmailOnContactsPageTest() {
        String emailOnContactPageText = contactUsPage.checkEmailOnContactsPage();
        Assert.assertEquals(emailOnContactPageText, "hello@softwaretestingboard.com");
    }


}
