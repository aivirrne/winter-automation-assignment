package com.shop;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserRegistrationPage {

    WebDriver driver;


    By createAnAccountLinkLocator = By.xpath("//header/div[1]/div/ul/li[3]/a");
    By firstNameLocator = By.id("firstname");
    By lastNameLocator = By.name("lastname");
    By emailLocator = By.xpath("//input[@title='Email']");
    By passwordLocator = By.id("password");
    By confirmPasswordLocator = By.id("password-confirmation");
    By createAccountButtonLocator = By.className("submit");
    By afterRegistrationTitleLocator = By.className("base");
    By firstNameValidationLocator = By.xpath("//div[@for='firstname']");
    By lastNameValidationLocator = By.xpath("//div[@for='lastname']");
    By emailValidationLocator = By.xpath("//div[@for='email_address']");
    By passwordValidationLocator = By.xpath("//div[@for='password']");
    By passwordConfirmationValidationLocator = By.xpath("//div[@for='password-confirmation']");

    public UserRegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openCreateAccountPage() throws InterruptedException {
        WebElement createAnAccountLink = waitForElementToBeVisible(createAnAccountLinkLocator);
        createAnAccountLink.click();
    }

    public void enterFirstName(String firstName) {
        WebElement firstNameInput = waitForElementToBeVisible(firstNameLocator);
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        WebElement lastNameInput = waitForElementToBeVisible(lastNameLocator);
        lastNameInput.sendKeys(lastName);
    }

    public void enterRandomEmail() {
        WebElement emailInput = waitForElementToBeVisible(emailLocator);
        emailInput.sendKeys(randomEmail());
    }
    public void enterEmail(String email) throws InterruptedException {
//        Thread.sleep(5000);
        WebElement emailInput = waitForElementToBeVisible(emailLocator);
        emailInput.sendKeys(email);
    }
    public void enterPassword(String password) {
        WebElement passwordInput = waitForElementToBeVisible(passwordLocator);
        passwordInput.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        WebElement confirmPasswordInput = waitForElementToBeVisible(confirmPasswordLocator);
        confirmPasswordInput.sendKeys(password);
    }

    public void clickCreateAccountButton() {
        WebElement createAccount = waitForElementToBeVisible(createAccountButtonLocator);
        createAccount.click();
    }

    public String getPageTitleAfterRegistration() {
        WebElement pageTitleAfterRegistration = waitForElementToBeVisible(afterRegistrationTitleLocator);
        return pageTitleAfterRegistration.getText();
    }

    public static String randomEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public String getFirstNameValidation() {
        WebElement firstNameValidationMessage = waitForElementToBeVisible(firstNameValidationLocator);
        return firstNameValidationMessage.getText();
    }

    public String getLastNameValidation() {
        WebElement lastNameValidationMessage = waitForElementToBeVisible(lastNameValidationLocator);
        return lastNameValidationMessage.getText();
    }

    public String getEmailValidation() {
        WebElement emailValidationMessage = waitForElementToBeVisible(emailValidationLocator);
        return emailValidationMessage.getText();
    }

    public String getPasswordValidation() {
        WebElement passwordValidationMessage = waitForElementToBeVisible(passwordValidationLocator);
        return passwordValidationMessage.getText();
    }

    public String getPasswordConfirmationValidation() {
        WebElement passwordConfirmationValidationMessage = waitForElementToBeVisible(passwordConfirmationValidationLocator);
        return passwordConfirmationValidationMessage.getText();
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


}
