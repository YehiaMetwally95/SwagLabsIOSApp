package baseTest;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.*;
import prepareTestData.LoadProductsFromDB;
import prepareTestData.LoadUsersFromDB;

import java.io.IOException;
import java.sql.SQLException;

import static yehiaEngine.driverManager.AppiumFactory2.getDriver;

public class BaseTest {

    public ThreadLocal<AppiumDriver> isolatedDriver;

    @BeforeSuite
    public void startAppiumServer() throws InterruptedException {
        AppiumFactory2.startAppiumServerOnMac();
    }

    @BeforeTest
    // Sync with Database to Load Latest Products and Users and Update Test Data Json Files
    public void prepareTestData() throws SQLException, IOException {
        if (System.getProperty("syncWithDB").equalsIgnoreCase("true")) {
            LoadProductsFromDB.loadProductsFromDB();
            LoadUsersFromDB.loadUsersFromDB();
        }
    }

    @BeforeMethod
    public void setUpAndOpenApp() {

        //Open App
        isolatedDriver = AppiumFactory2.openApp();
    }

    @AfterMethod
    public void closeApp(){
        //Close App after every test
        AppiumFactory2.closeApp(getDriver(isolatedDriver));

        //Remove the Isolated Driver from Memory
        AppiumFactory2.removeIsolatedDriver(isolatedDriver);
    }

    @AfterSuite
    public void stopAppiumServer(){
        AppiumFactory2.stopAppiumServer();
    }
}
