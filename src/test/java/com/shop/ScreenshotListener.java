package com.shop;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        WebDriver driver = BaseTest.getDriver();
        // Allure ScreenShot and SaveTestLog
        if (driver instanceof WebDriver) {
//            System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
            saveFailureScreenshot(driver);
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveFailureScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
