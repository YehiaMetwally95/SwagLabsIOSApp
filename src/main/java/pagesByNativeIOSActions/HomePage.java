package pagesByNativeIOSActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yehiaEngine.elementActions.NativeIOSActions;

public class HomePage {
    //Variables
    AppiumDriver driver;
    NativeIOSActions action;

    By menuIcon = AppiumBy.accessibilityId("test-Menu");
    By toggleIcon = AppiumBy.accessibilityId("test-Toggle");
    By cartIcon = AppiumBy.xpath("//XCUIElementTypeOther[@name=\"test-Cart\"]");
    By footer = AppiumBy.accessibilityId("new UiSelector().text(\"© 2024 Sauce Labs. All Rights Reserved.\")");


    //Constructor
    public HomePage(AppiumDriver driver) {
        this.driver = driver;
        action = new NativeIOSActions(driver);
    }

    //Actions
    @Step("Open Menu From Header")
    public MenuPage openMenuFromHeader()
    {
        action.tap(menuIcon);
        return new MenuPage(driver);
    }

    public CartPage openCartPageFromHeader() throws InterruptedException {
        action.tap(cartIcon);
        Thread.sleep(5000);
        return new CartPage(driver);
    }

    @Step("Open Twitter From Footer")
    public void openTwitterPageFromFooter()
    {

    }

    @Step("Open Facebook From Footer")
    public void openFacebookPageFromFooter()
    {

    }

    @Step("Open GooglePlus From Footer")
    public void openGooglePlusPageFromFooter()
    {

    }

    @Step("Open Linkedin From Footer")
    public void openLinkedinPageFromFooter()
    {

    }
}
