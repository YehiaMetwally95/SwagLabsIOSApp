package pagesByW3cTouchActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yehiaEngine.assertions.CustomAssert;
import yehiaEngine.assertions.CustomSoftAssert;
import yehiaEngine.elementActions.W3CFingerActions.Direction;

import static yehiaEngine.elementActions.W3CFingerActions.Direction.DOWN;
import static yehiaEngine.elementActions.W3CFingerActions.Direction.LEFT;

public class CartCheckOutOverviewPage extends HomePage{
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
    private void defineLocatorsByProductName(String productName)
    {
        this.productName = productName;
        productItem = AppiumBy.iOSNsPredicateString("name == \"test-Item title\" AND label == '"+productName+"'");
        productPriceLocator = AppiumBy.xpath("//XCUIElementTypeStaticText[@name='"+productName+"']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther//XCUIElementTypeStaticText");
        productDescriptionLocator = AppiumBy.xpath("//XCUIElementTypeStaticText[@name='"+productName+"']/following-sibling::XCUIElementTypeStaticText");
        productQuantityLocator = AppiumBy.xpath("//XCUIElementTypeStaticText[@name='"+productName+"']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/preceding-sibling::XCUIElementTypeOther//XCUIElementTypeStaticText");
        removeFromCartSwipe = AppiumBy.xpath("//XCUIElementTypeStaticText[@name='"+productName+"']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther//XCUIElementTypeOther[@name='test-Delete']");
    }

    @Step("Return Back to Products Page")
    public ProductsPage returnBackToProductsPage()
    {
        action.tap(backToProductsButton);
        return new ProductsPage(driver);
    }

    @Step("Remove Product From Cart By Swipe")
    public CartCheckOutOverviewPage removeProductFromCartBySwipe(String productName)
    {
        defineLocatorsByProductName(productName);
        action.tap(removeFromCartSwipe,LEFT,productItem);
        return this;
    }

    @Step("Finish Cart Checkout")
    public CartCheckOutCompletePage finishCartCheckOut()
    {
        action.tap(finishButton);
        return new CartCheckOutCompletePage(driver);
    }

    //Validations
    @Step("Assert Product Is Added To Cart")
    public CartCheckOutOverviewPage assertProductIsAddedToCart(String productName)
    {
        defineLocatorsByProductName(productName);
        CustomAssert.assertTrue(action.isElementDisplayed(productItem,DOWN));
        return this;
    }

    @Step("Assert Product Is Removed From Cart")
    public CartCheckOutOverviewPage assertProductIsRemovedFromCart(String productName)
    {
        defineLocatorsByProductName(productName);
        CustomAssert.assertTrue(action.isElementNotDisplayed(productItem));
        return this;
    }

    @Step("Verify Product Info")
    public CartCheckOutOverviewPage verifyProductInfo(String productName,String price,String quantity,String description)
    {
        verifyProductPrice(productName,price)
                .verifyProductQuantity(productName,quantity)
                .verifyProductDescription(productName,description);
        return this;
    }

    @Step("Verify Product Price")
    private CartCheckOutOverviewPage verifyProductPrice(String productName,String price) {
        defineLocatorsByProductName(productName);
        String actualPrice = action.readText(productPriceLocator).split("\\$",2)[1];
        CustomSoftAssert.assertEquals(actualPrice,price);
        return this;
    }

    @Step("Verify Product Quantity")
    private CartCheckOutOverviewPage verifyProductQuantity(String productName,String quantity) {
        defineLocatorsByProductName(productName);
        CustomSoftAssert.assertEquals(action.readText(productQuantityLocator),quantity);
        return this;
    }

    @Step("Verify Product Description")
    private CartCheckOutOverviewPage verifyProductDescription(String productName,String description) {
        defineLocatorsByProductName(productName);
        CustomSoftAssert.assertEquals(action.readText(productDescriptionLocator),description);
        return this;
    }

    @Step("Verify Total Price of Products")
    public CartCheckOutOverviewPage verifyTotalPriceOfProducts(double totalPrice) {
        double actualTotalPrice = Double.parseDouble(action.readText(totalPriceLocator).split("\\$",2)[1]);
        CustomSoftAssert.assertEquals(actualTotalPrice,totalPrice);
        return this;
    }

    @Step("Verify the Payment Info")
    public CartCheckOutOverviewPage verifyPaymentInfo(String paymentInfo){
        CustomSoftAssert.assertEquals(action.readText(paymentInfoLocator),paymentInfo);
        return this;
    }

    @Step("Verify the Shipping Method")
    public CartCheckOutOverviewPage verifyShippingMethod(String shippingMethod) {
        CustomSoftAssert.assertEquals(action.readText(shippingInfoLocator),shippingMethod);
        return this;
    }

    //Scrolling
    @Step("Scroll to Product")
    public CartCheckOutOverviewPage scrollToProduct(String productName, Direction direction)
    {
        defineLocatorsByProductName(productName);
        action.swipeIntoScreen(productItem,direction);
        return this;
    }

    @Step("Scroll to Cancel Button")
    public CartCheckOutOverviewPage scrollToCancelButton(Direction direction)
    {
        action.swipeIntoScreen(backToProductsButton,direction);
        return this;
    }

    @Step("Scroll to Finish Button")
    public CartCheckOutOverviewPage scrollToFinishButton(Direction direction)
    {
        action.swipeIntoScreen(finishButton,direction);
        return this;
    }

    @Step("Scroll to Total Price View")
    public CartCheckOutOverviewPage scrollToTotalPriceView(Direction direction)
    {
        action.swipeIntoScreen(totalPriceLocator,direction);
        return this;
    }

    @Step("Scroll to Payment Info View")
    public CartCheckOutOverviewPage scrollToPaymentInfoView(Direction direction)
    {
        action.swipeIntoScreen(paymentInfoLocator,direction);
        return this;
    }

    @Step("Scroll to Shipping Info View")
    public CartCheckOutOverviewPage scrollToShippingInfoView(Direction direction)
    {
        action.swipeIntoScreen(shippingInfoLocator,direction);
        return this;
    }
}
