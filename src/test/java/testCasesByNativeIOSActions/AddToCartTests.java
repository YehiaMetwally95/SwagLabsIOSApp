package testCasesByNativeIOSActions;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pagesByNativeIOSActions.LoginPage;
import yehiaEngine.managers.JsonManager;

import static yehiaEngine.driverManager.AppiumFactory.getDriver;

@Epic("SwagLabs Android App")
@Feature("Cart")
@Story("Verify Add Products To Cart")
public class AddToCartTests extends BaseTest {
    //Variables
    String jsonFilePathForAddToCart = "src/test/resources/TestDataJsonFiles/AddToCartTestData.json";
    JsonManager json = new JsonManager(jsonFilePathForAddToCart);

    @Test
    public void addProductToCartByButton() throws InterruptedException {
        new LoginPage(getDriver(isolatedDriver))
                .loginWithValidUser(json.getData("Users[0].Username"),json.getData("Users[0].Password"))
                .verifyProductsPageIsOpened()

                .addProductToCartByButton(json.getData("Products[1].Name"))
                .openCartPageFromHeader()
                .assertProductIsAddedToCart(json.getData("Products[1].Name"));
    }

    @Test
    public void addProductToCartByDragAndDrop() throws InterruptedException {
        new LoginPage(getDriver(isolatedDriver))
                .loginWithValidUser(json.getData("Users[0].Username"),json.getData("Users[0].Password"))
                .verifyProductsPageIsOpened()

                .addProductToCartByDragAndDrop(json.getData("Products[0].Name"))
                .openCartPageFromHeader()
                .assertProductIsAddedToCart(json.getData("Products[0].Name"));
    }

    @Test
    public void addMultipleProductsToCart() throws InterruptedException {
        new LoginPage(getDriver(isolatedDriver))
                .loginWithValidUser(json.getData("Users[0].Username"),json.getData("Users[0].Password"))
                .verifyProductsPageIsOpened()

                .addProductToCartByButton(json.getData("Products[0].Name"))
                .addProductToCartByDragAndDrop(json.getData("Products[1].Name"))
                .addProductToCartByDragAndDrop(json.getData("Products[2].Name"))
                .openCartPageFromHeader()
                .assertProductIsAddedToCart(json.getData("Products[2].Name"))
                .assertProductIsAddedToCart(json.getData("Products[0].Name"))
                .assertProductIsAddedToCart(json.getData("Products[1].Name"));
    }

    @Test
    public void addProductToCartFromInsideProductDetailsPage() throws InterruptedException {
        //test comment22
        new LoginPage(getDriver(isolatedDriver))
                .loginWithValidUser(json.getData("Users[0].Username"),json.getData("Users[0].Password"))
                .verifyProductsPageIsOpened()

                .openProductDetailsPage(json.getData("Products[1].Name"))
                .zoomInProductPicture()
                .zoomOutProductPicture()
                .verifyProductDetails(json.getData("Products[1].Name"),json.getData("Products[1].Description"),json.getData("Products[1].Price"))
                .addProductToCart()
                .openCartPageFromHeader()
                .assertProductIsAddedToCart(json.getData("Products[1].Name"));
    }
}
