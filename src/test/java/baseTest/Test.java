package baseTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class Test {

    @org.testng.annotations.Test
    public void test1() throws URISyntaxException, MalformedURLException {
        AppiumDriver driver = new AndroidDriver(new URI("dsa").toURL(), new DesiredCapabilities());

        KeyEvent key = new KeyEvent();

    }
}
