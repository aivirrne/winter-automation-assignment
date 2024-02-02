package com.shop;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;

public class CartPaymentPage {

    WebDriver driver;

    By priceOnCartPageLocator = By.xpath("//*[@id='shopping-cart-table']/tbody/tr[1]/td[4]/span/span/span");
    By estimateShippingButtonLocator = By.id("block-shipping-heading");
    By countryDropdownLocator = By.xpath("//fieldset/div[1]/div/select");
    By orderTotalPriceLocator = By.xpath("//*[@id='cart-totals']/div/table/tbody/tr[3]/td/strong/span");
    By proceedToCheckoutButtonLocator = By.xpath("//li/button[@title='Proceed to Checkout']");
    By firstNameFieldLocator = By.name("firstname");
    By lastNameFieldLocator = By.name("lastname");
    By streetFieldLocator = By.name("street[0]");
    By cityFieldLocator = By.name("city");
    By stateDropdownLocator = By.name("region_id");
    By postalCodeFieldLocator = By.name("postcode");
    By phoneNumberFieldLocator = By.name("telephone");
    By emailFieldLocator = By.id("customer-email");
    By nextButtonLocator = By.xpath("//*[@id='shipping-method-buttons-container']/div/button");
    By placeOrderButtonLocator = By.xpath("//button[@title='Place Order']");
    By orderNumberLocator = By.xpath("//div/div[3]/p[1]/span");

    public CartPaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    public String checkPriceInCart() {
        WebElement priceInCart = waitForElementToBeVisible(priceOnCartPageLocator);
        return priceInCart.getText();
    }

    public String changeShippingAddress() throws InterruptedException {
        WebElement estimateShippingButton = waitForElementToBeVisible(estimateShippingButtonLocator);
        estimateShippingButton.click();
//        Thread.sleep(5000);
        WebElement countryDropdown = waitForElementToBeVisible(countryDropdownLocator);
        Select dropdown = new Select(countryDropdown);
        countryDropdown.click();
        dropdown.selectByVisibleText("Poland");
        Thread.sleep(5000);
        WebElement priceAfterChooseShipping = waitForElementToBeVisible(orderTotalPriceLocator);
        return priceAfterChooseShipping.getText();
    }

    public void proceedToCheckout() throws InterruptedException {
        WebElement proceedToCheckoutButton = waitForElementToBeVisible(proceedToCheckoutButtonLocator);
        proceedToCheckoutButton.click();
//        Thread.sleep(5000);
    }

    private static String[] readShippingAddressFromFile(String filePath) {
        String[] shippingAddress = new String[7];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (int i = 0; i < 7; i++) {
                shippingAddress[i] = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return shippingAddress;
    }

    public static String randomEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public void fillShippingAddress() throws InterruptedException {
        String[] shippingAddress = readShippingAddressFromFile("C://Users//mishchez//IdeaProjects//winter-automation-assignment//adress_data.txt");
//        Thread.sleep(5000);
        WebElement firstNameField = waitForElementToBeVisible(firstNameFieldLocator);
        firstNameField.sendKeys(shippingAddress[0]);
        WebElement lastNameField = waitForElementToBeVisible(lastNameFieldLocator);
        lastNameField.sendKeys(shippingAddress[1]);
        WebElement streetField = waitForElementToBeVisible(streetFieldLocator);
        streetField.sendKeys(shippingAddress[2]);
        WebElement cityField = waitForElementToBeVisible(cityFieldLocator);
        cityField.sendKeys(shippingAddress[3]);
        WebElement stateDropdown = waitForElementToBeVisible(stateDropdownLocator);
        Select dropdown = new Select(stateDropdown);
        stateDropdown.click();
        dropdown.selectByValue(shippingAddress[4]);
        WebElement postalCodeField = waitForElementToBeVisible(postalCodeFieldLocator);
        postalCodeField.sendKeys(shippingAddress[5]);
        WebElement phoneNumberField = waitForElementToBeVisible(phoneNumberFieldLocator);
        phoneNumberField.sendKeys(shippingAddress[6]);
        WebElement emailField = waitForElementToBeVisible(emailFieldLocator);
        emailField.sendKeys(randomEmail());
        WebElement nextButton = waitForElementToBeVisible(nextButtonLocator);
        nextButton.click();
    }

    public void placeOrder() throws InterruptedException {
        Thread.sleep(5000);
        WebElement placeOrderButton = waitForElementToBeVisible(placeOrderButtonLocator);
        placeOrderButton.click();

    }

    public String getOrderNumber() throws InterruptedException {
//        Thread.sleep(5000);
        WebElement orderNumber = waitForElementToBeVisible(orderNumberLocator);
        return orderNumber.getText();

    }

    public void saveOrderNumberToFile(String filePath, String orderNumber) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(orderNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }



}
