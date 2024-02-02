package com.shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

@Listeners({ScreenshotListener.class})
public class ProductTest extends BaseTest {

    private ProductsPage productsPage;
    private CartPaymentPage cartPage;
    @BeforeMethod
    public void initializePageObject() {
        productsPage = new ProductsPage(driver);
        cartPage = new CartPaymentPage(driver);
    }

    @Test(groups = "Regression")
    public void countProducts() throws InterruptedException {
        productsPage.openWomenJacketPage();
        Thread.sleep(5000);
        int productCardsNumber = productsPage.countProductsOnPage();
        Assert.assertEquals(productCardsNumber, 12);
    }

    @Test(groups = "Smoke")
    public void addProductToCart() throws InterruptedException {
        productsPage.openWomenJacketPage();
        productsPage.addProductToCart();
        String priceOnProductsPageText = productsPage.openCart();
        String priceOnCartPageText = cartPage.checkPriceInCart();
        Assert.assertEquals(priceOnProductsPageText, priceOnCartPageText);
    }
}
