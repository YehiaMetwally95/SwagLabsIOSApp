package pagesByNativeIOSActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yehiaEngine.assertions.CustomSoftAssert;

import static yehiaEngine.elementActions.NativeIOSActions.LocatorType.*;
import static yehiaEngine.elementActions.NativeIOSActions.ScrollDirection.*;

public class ProductsPage extends HomePage {

    //Variables

    //Locators
    By productItem;
    By dragButton;
    By dropButton = AppiumBy.accessibilityId("test-Cart drop zone");
    By addToCardButton;
    By removeFromCartButton;

    //Locator Texts
    String productItemText;

    //Constructor
    public ProductsPage(AppiumDriver driver)
    {
        super(driver);
    }

    //Actions
    private void defineLocatorsByProductName(String productName)
    {
        this.productItemText = productName;
        productItem = AppiumBy.iOSNsPredicateString("name == \"test-Item title\" AND label == '"+productName+"'");
        addToCardButton = AppiumBy.xpath("//XCUIElementTypeOther[@name='test-Item' and contains(@label,'"+productName+"')] // XCUIElementTypeOther [@name = 'ADD TO CART']");
        removeFromCartButton= AppiumBy.xpath("//XCUIElementTypeOther[@name='test-Item' and contains(@label,'"+productName+"')] // XCUIElementTypeOther [@name = 'REMOVE']");
        dragButton =AppiumBy.xpath("//XCUIElementTypeOther[@name='test-Item' and contains(@label,'"+productName+"')] // XCUIElementTypeOther [@name = 'test-Drag Handle']");
    }

    @Step("Add Product To Cart By Drag & Drop")
    public ProductsPage addProductToCartByDragAndDrop(String productName)
    {
        defineLocatorsByProductName(productName);
        action.dragAndDrop(dragButton,dropButton,VERTICAL);
        return this;
    }

    @Step("Add Product To Cart By Button")
    public ProductsPage addProductToCartByButton(String productName)
    {
        defineLocatorsByProductName(productName);
        action.tap(addToCardButton,VERTICAL);
        return this;
    }

    @Step("Remove Product From Cart By Button")
    public ProductsPage removeProductFromCartByButton(String productName)
    {
        defineLocatorsByProductName(productName);
        action.tap(removeFromCartButton,VERTICAL);
        return this;
    }

    @Step("Open Product Details Page")
    public ProductDetailsPage openProductDetailsPage(String productName)
    {
        defineLocatorsByProductName(productName);
        action.tap(productItem,VERTICAL);
        return new ProductDetailsPage(driver);
    }

    //Validation
    @Step("Verify Products Page is Opened")
    public ProductsPage verifyProductsPageIsOpened() {
        CustomSoftAssert.assertTrue(action.isElementDisplayed(menuIcon));
        return this;
    }
}
