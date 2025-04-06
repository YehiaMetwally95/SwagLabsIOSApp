package pagesByW3cTouchActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CartCheckOutCompletePage extends HomePage {
    //Locators
    By backHomeLocator = AppiumBy.accessibilityId("test-BACK HOME");
    By successfulMassage = AppiumBy.xpath("(//XCUIElementTypeOther[@name=\"BACK HOME\"]/preceding-sibling::XCUIElementTypeOther/preceding-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText)[1]");

    //Constructor
    public CartCheckOutCompletePage(AppiumDriver driver) {
        super(driver);
    }

    //Actions
    @Step("Return Back to Products Page")
    public ProductsPage returnBackToProductsPage()
    {
        action.tap(backHomeLocator);
        return new ProductsPage(driver);
    }

    //Validations
    @Step("Assert CheckOut Successful Massage")
    public CartCheckOutCompletePage assertCheckoutSuccessfulMessage(String text) {
        Assert.assertEquals(action.readText(successfulMassage),text);
        return this;
    }
}
