package org.wikiqa.infra;

import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.remote.SupportsRotation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.ScreenOrientation;
import org.wikiqa.pages.WelcomePageObject;

import java.net.MalformedURLException;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

@Tag("mobile")
public class CoreTestCase {

    private AppiumDriver driver;

    @BeforeEach
    void setUp() throws MalformedURLException {
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        WebDriverRunner.setWebDriver(driver);
        this.skipWelcomePageForIOSApp();
    }

    @AfterEach
    void tearDown() {
        WebDriverRunner.closeWebDriver();
        driver.quit();
    }

    protected void rotateScreenPortrait() {
        ((SupportsRotation) driver).rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        ((SupportsRotation) driver).rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds) {
        ((InteractsWithApps) driver).runAppInBackground(Duration.of(seconds, SECONDS));
    }

    private void skipWelcomePageForIOSApp() {
        if (Platform.getInstance().isIOS()) {
            WelcomePageObject WelcomePageObject = new WelcomePageObject();
            WelcomePageObject.clickSkip();
        }
    }
}
