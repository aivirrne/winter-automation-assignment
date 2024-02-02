package com.shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners({ScreenshotListener.class})
public class UserRegistrationTest extends BaseTest  {

    private UserRegistrationPage userRegistrationPage;
    @BeforeMethod
    public void initializePageObject() {
        userRegistrationPage = new UserRegistrationPage(driver);
    }


    @Test(groups = "Smoke")
    public void testRegistration() throws InterruptedException {
        userRegistrationPage.openCreateAccountPage();

        String firstName = "Jane";
        String lastName = "Doe";
        String password = "Password123";

        userRegistrationPage.enterFirstName(firstName);
        userRegistrationPage.enterLastName(lastName);
        userRegistrationPage.enterRandomEmail();
        userRegistrationPage.enterPassword(password);
        userRegistrationPage.enterConfirmPassword(password);
        userRegistrationPage.clickCreateAccountButton();

        String actualTitleText = userRegistrationPage.getPageTitleAfterRegistration();
        Assert.assertEquals(actualTitleText, "My Account");
    }

    @Test(groups = "Regression")
    public void testRegistrationValidationMessage() throws InterruptedException {
        userRegistrationPage.openCreateAccountPage();

        String email = "64588743567";
        String password = "12345";
        String passwordConfirmation = "1234567";

        userRegistrationPage.enterEmail(email);
        userRegistrationPage.enterPassword(password);
        userRegistrationPage.enterConfirmPassword(passwordConfirmation);
        userRegistrationPage.clickCreateAccountButton();

        String actualFirstNameValidationMessage = userRegistrationPage.getFirstNameValidation();
        Assert.assertEquals(actualFirstNameValidationMessage, "This is a required field.");

        String actualLastNameValidationMessage = userRegistrationPage.getLastNameValidation();
        Assert.assertEquals(actualLastNameValidationMessage, "This is a required field.");

        String actualEmailValidationMessage = userRegistrationPage.getEmailValidation();
        Assert.assertEquals(actualEmailValidationMessage, "Please enter a valid email address (Ex: johndoe@domain.com).");

        String actualPasswordValidationMessage = userRegistrationPage.getPasswordValidation();
        Assert.assertEquals(actualPasswordValidationMessage, "Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.");

        String actualPasswordConfirmationValidationMessage = userRegistrationPage.getPasswordConfirmationValidation();
        Assert.assertEquals(actualPasswordConfirmationValidationMessage, "Please enter the same value again.");
    }

}
