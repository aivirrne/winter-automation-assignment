package com.shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners({ScreenshotListener.class})
public class CheckoutOrderTest extends BaseTest {

    private ProductsPage productsPage;
    private CartPaymentPage cartPage;
    @BeforeMethod
    public void initializePageObject() {
        productsPage = new ProductsPage(driver);
        cartPage = new CartPaymentPage(driver);
        System.out.println("Inside initialize");
    }


    @Test(groups = "Smoke")
    public void checkoutOrder() throws InterruptedException {
        productsPage.openWomenJacketPage();
        productsPage.addProductToCart();
        productsPage.openCart();
        String priceOnCartPageText = cartPage.checkPriceInCart();
        String priceAfterChooseShippingText = cartPage.changeShippingAddress();
        Assert.assertNotEquals(priceOnCartPageText, priceAfterChooseShippingText);
        cartPage.proceedToCheckout();
        cartPage.fillShippingAddress();
        cartPage.placeOrder();
        String orderNumber = cartPage.getOrderNumber();
        cartPage.saveOrderNumberToFile("C://Users//mishchez//IdeaProjects//winter-automation-assignment//order_data.txt", orderNumber);

    }
}
