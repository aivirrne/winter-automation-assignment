package com.shop;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductsPage {

    WebDriver driver;

    By womenLinkLocator = By.className("nav-2");
    By jacketsLinkLocator = By.xpath("//a[text()='Jackets']");
    By sizeButtonLocator = By.xpath("(//*[@id='option-label-size-143-item-168'])[2]");
    By colorButtonLocator = By.xpath("(//*[@id='option-label-color-93-item-53'])[1]");
    By addToCartButtonLocator = By.xpath("//div[3]/div[1]/div[4]/ol/li[2]/div/div/div[4]/div/div[1]/form/button");
    By showCartLocator = By.className("showcart");
    By viewCartLocator = By.className("viewcart");
    By priceOnProductsPageLocator = By.xpath("//div[2]/div[2]/div/span/span");
//    By topsLinkLocator = By.className("nav-2-1");
//    By jacketsLinkLocator = By.className("nav-2-1-1");
    //By womenLinkLocator = xpath("//a[@href='https://magento.softwaretestingboard.com/women.html']//span");
    //By topsLinkLocator = By.xpath("//a[@href='https://magento.softwaretestingboard.com/women/tops-women.html']");
    //By jacketsLinkLocator = By.xpath("//a[@href='https://magento.softwaretestingboard.com/women/tops-women/jackets-women.html']");
    //By womenJacketButtonLocator = By.xpath("//a[@href='https://magento.softwaretestingboard.com/women/tops-women/jackets-women.html']");
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openWomenJacketPage() {
        WebElement womenLink = waitForElementToBeVisible(womenLinkLocator);
        womenLink.click();
        WebElement jacketsLink = waitForElementToBeVisible(jacketsLinkLocator);
        jacketsLink.click();

//        Actions actions = new Actions(driver);
//        actions.moveToElement(womenLink).build().perform();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
//        WebElement topsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(topsLinkLocator));
//        //WebElement topsLink = driver.findElement(topsLinkLocator);
//        actions.moveToElement(topsLink).perform();
//        WebElement jacketsLink = driver.findElement(jacketsLinkLocator);
//        jacketsLink.click();
    }

    public int countProductsOnPage() {
        List<WebElement> productCards = driver.findElements(By.className("product-item"));
        return productCards.size();
    }

    public void addProductToCart() {
        WebElement sizeButton = waitForElementToBeVisible(sizeButtonLocator);
        sizeButton.click();
        WebElement colorButton = waitForElementToBeVisible(colorButtonLocator);
        colorButton.click();
        WebElement addToCartButton = waitForElementToBeVisible(addToCartButtonLocator);
        addToCartButton.click();
    }

    public String openCart() throws InterruptedException {
        Thread.sleep(5000);
        WebElement showCart = waitForElementToBeVisible(showCartLocator);
        showCart.click();
        WebElement priceOnProductsPage = waitForElementToBeVisible(priceOnProductsPageLocator);
        String priceOnProductsPageText = priceOnProductsPage.getText();
        WebElement viewCart = waitForElementToBeVisible(viewCartLocator);
        viewCart.click();
        return priceOnProductsPageText;
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
