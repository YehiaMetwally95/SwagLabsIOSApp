package pagesByNativeIOSActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yehiaEngine.assertions.CustomAssert;
import yehiaEngine.elementActions.NativeIOSActions;

import static yehiaEngine.elementActions.NativeIOSActions.LocatorType.*;
import static yehiaEngine.elementActions.NativeIOSActions.ScrollDirection.*;

public class CartPage extends HomePage {

    //Variables
    String productName;

    //Locators
    By removeFromCartButton;
    By backToProductsButton = AppiumBy.accessibilityId("test-CONTINUE SHOPPING");
    By checkOutButton = AppiumBy.accessibilityId("test-CHECKOUT");
    By productItem;
    By removeFromCartSwipe;

    //Constructor
    public CartPage(AppiumDriver driver) {
        super(driver);
    }

    //Actions
    private void defineLocatorsByProductName(String productName) {
        this.productName = productName;
        productItem = AppiumBy.iOSNsPredicateString("name == '"+productName+"'");
        removeFromCartButton = AppiumBy.xpath("//XCUIElementTypeStaticText[@name='"+productName+"']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther//XCUIElementTypeOther[@name = 'test-REMOVE']");
        removeFromCartSwipe = AppiumBy.xpath("//XCUIElementTypeStaticText[@name='"+productName+"']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther//XCUIElementTypeOther[@name='test-Delete']");
    }

    @Step("Remove Product From Cart By Button")
    public CartPage removeProductFromCartByButton(String productName) {
        defineLocatorsByProductName(productName);
        action.tap(removeFromCartButton,VERTICAL);
        return this;
    }

    @Step("Remove Product From Cart By Swipe")
    public CartPage removeProductFromCartBySwipe(String productName) {
        defineLocatorsByProductName(productName);
        action
                .swipeIntoScreen(productItem,VERTICAL)
                .singleSwipe(productItem, NativeIOSActions.Direction.LEFT)
                .tap(removeFromCartSwipe);
        return this;
    }

    @Step("Proceed To Fill User Info")
    public CartCheckOutInfoPage proceedToFillUserInfo() {
        action.tap(checkOutButton, VERTICAL);
        return new CartCheckOutInfoPage(driver);
    }

    @Step("Return Back to Products Page")
    public ProductsPage returnBackToProductPage() {
        action.tap(backToProductsButton, VERTICAL);
        return new ProductsPage(driver);
    }

    //Validations
    @Step("Assert Product Is Added To Cart")
    public CartPage assertProductIsAddedToCart(String productName) {
        defineLocatorsByProductName(productName);
        CustomAssert.assertTrue(action.isElementDisplayed(productItem, VERTICAL));
        return this;
    }

    @Step("Assert Product Is Removed To Cart")
    public CartPage assertProductIsRemovedFromCart(String productName) {
        defineLocatorsByProductName(productName);
        CustomAssert.assertTrue(action.isElementNotDisplayed(productItem, VERTICAL));
        return this;
    }
}
