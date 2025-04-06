package baseTest;

import com.github.javafaker.App;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.output.AppendableOutputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;
import pagesByW3cTouchActions.LoginPage;
import yehiaEngine.elementActions.NativeIOSActions;
import yehiaEngine.elementActions.W3CFingerActions;

import java.util.HashMap;
import java.util.Map;

import static yehiaEngine.driverManager.AppiumFactory.getDriver;
import static yehiaEngine.elementActions.Helpers.NativeAndroidActionsHelper.getElementSize;
import static yehiaEngine.elementActions.Helpers.WaitsManager.getFluentWait;

public class Test1 extends BaseTest {

    @Test
    public void test() throws InterruptedException {
        AppiumDriver driver = getDriver(isolatedDriver);
        By message1 = AppiumBy.xpath("(//XCUIElementTypeCollectionView[@name=\"ConversationList\"]/XCUIElementTypeCell)[1]");
        By pin = AppiumBy.iOSNsPredicateString("name == \"Pin\"");
        By plus = AppiumBy.iOSNsPredicateString("name == \"Apps\"");
        By image = AppiumBy.iOSClassChain("**/XCUIElementTypeImage[`name == \"PXGGridLayout-Info\"`][1]");

        NativeIOSActions action = new NativeIOSActions(driver);
        action.longTap(message1);
        action.tap(pin);
        action.tap(message1);
        action.longTap(plus);
        Thread.sleep(5000);
        action.doubleTap(image);

    }
}
