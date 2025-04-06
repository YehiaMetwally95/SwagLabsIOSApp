package pagesByNativeIOSActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yehiaEngine.assertions.CustomAssert;
import yehiaEngine.assertions.CustomSoftAssert;
import yehiaEngine.elementActions.NativeIOSActions;

import static yehiaEngine.elementActions.NativeIOSActions.ScrollDirection.*;

public class CartCheckOutOverviewPage extends HomePage {
    //Variables
    String productName;

    //Locators
    By productItem;
    By productPriceLocator;
    By productDescriptionLocator;
    By productQuantityLocator;
    By removeFromCartSwipe;

    By backToProductsButton = AppiumBy.accessibilityId("test-CANCEL");
    By finishButton = AppiumBy.accessibilityId("test-FINISH");
    By paymentInfoLocator = AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Payment Information:\"]/following-sibling::XCUIElementTypeStaticText");
    By shippingInfoLocator = AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Shipping Information:\"]/following-sibling::XCUIElementTypeStaticText");
    By totalPriceLocator = AppiumBy.xpath("//XCUIElementTypeStaticText[contains(@value,'Total')]");

    //Constructor
    public CartCheckOutOverviewPage(AppiumDriver driver) {
        super(driver);
    }

    //Actions
    private void defineLocatorsByProductName(String productName) {

        productItem = AppiumBy.iOSNsPredicateString("name == \"test-Item title\" AND label == '"+productName+"'");
        productPriceLocator = AppiumBy.xpath("//XCUIElementTypeStaticText[@name='"+productName+"']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther//XCUIElementTypeStaticText");
        productDescriptionLocator = AppiumBy.xpath("//XCUIElementTypeStaticText[@name='"+productName+"']/following-sibling::XCUIElementTypeStaticText");
        productQuantityLocator = AppiumBy.xpath("//XCUIElementTypeStaticText[@name='"+productName+"']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/preceding-sibling::XCUIElementTypeOther//XCUIElementTypeStaticText");
        removeFromCartSwipe = AppiumBy.xpath("//XCUIElementTypeStaticText[@name='"+productName+"']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther//XCUIElementTypeOther[@name='test-Delete']");
    }

    @Step("Return Back to Products Page")
    public ProductsPage returnBackToProductsPage()
    {
        action.tap(backToProductsButton,VERTICAL);
        return new ProductsPage(driver);
    }

    @Step("Remove Product From Cart By Swipe")
    public CartCheckOutOverviewPage removeProductFromCartBySwipe(String productName)
    {
        defineLocatorsByProductName(productName);
        action
                .swipeIntoScreen(productItem,VERTICAL)
                .singleSwipe(productItem, NativeIOSActions.Direction.LEFT)
                .tap(removeFromCartSwipe);
        return this;
    }

    @Step("Finish Cart Checkout")
    public CartCheckOutCompletePage finishCartCheckOut()
    {
        action.tap(finishButton,VERTICAL);
        return new CartCheckOutCompletePage(driver);
    }

    //Validations
    @Step("Assert Product Is Added To Cart")
    public CartCheckOutOverviewPage assertProductIsAddedToCart(String productName) {
        defineLocatorsByProductName(productName);
        CustomAssert.assertTrue(action.isElementDisplayed(productItem, VERTICAL));
        return this;
    }

    @Step("Assert Product Is Removed To Cart")
    public CartCheckOutOverviewPage assertProductIsRemovedFromCart(String productName) {
        defineLocatorsByProductName(productName);
        CustomAssert.assertTrue(action.isElementNotDisplayed(productItem, VERTICAL));
        return this;
    }

    @Step("Verify Product Info")
    public CartCheckOutOverviewPage verifyProductInfo(String productName, String price, String quantity, String description)
    {
        verifyProductPrice(productName,price)
                .verifyProductQuantity(productName,quantity)
                .verifyProductDescription(productName,description);
        return this;
    }

    @Step("Verify Product Price")
    private CartCheckOutOverviewPage verifyProductPrice(String productName, String price) {
        defineLocatorsByProductName(productName);
        String actualPrice = action.readText(productPriceLocator,VERTICAL).split("\\$",2)[1];
        CustomSoftAssert.assertEquals(actualPrice,price);
        return this;
    }

    @Step("Verify Product Quantity")
    private CartCheckOutOverviewPage verifyProductQuantity(String productName, String quantity) {
        defineLocatorsByProductName(productName);
        CustomSoftAssert.assertEquals(action.readText(productQuantityLocator,VERTICAL),quantity);
        return this;
    }

    @Step("Verify Product Description")
    private CartCheckOutOverviewPage verifyProductDescription(String productName, String description) {
        defineLocatorsByProductName(productName);
        CustomSoftAssert.assertEquals(action.readText(productDescriptionLocator,VERTICAL),description);
        return this;
    }

    @Step("Verify Total Price of Products")
    public CartCheckOutOverviewPage verifyTotalPriceOfProducts(double totalPrice) {
        double actualTotalPrice = Double.parseDouble( action
                .readText(totalPriceLocator,VERTICAL)
                .split("\\$",2)[1]);
        CustomSoftAssert.assertEquals(actualTotalPrice,totalPrice);
        return this;
    }

    @Step("Verify the Payment Info")
    public CartCheckOutOverviewPage verifyPaymentInfo(String paymentInfo) {
        String actualPaymentInfo = action.readText(paymentInfoLocator,VERTICAL);
        CustomSoftAssert.assertEquals(actualPaymentInfo,paymentInfo);
        return this;
    }

    @Step("Verify the Shipping Method")
    public CartCheckOutOverviewPage verifyShippingMethod(String shippingMethod) {
        String actualShippingInfo = action
                .swipeIntoScreen(shippingInfoLocator,VERTICAL)
                .readText(shippingInfoLocator);
        CustomSoftAssert.assertEquals(actualShippingInfo,shippingMethod);
        return this;
    }
}
