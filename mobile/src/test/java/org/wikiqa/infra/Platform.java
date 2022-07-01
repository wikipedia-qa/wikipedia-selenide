package org.wikiqa.infra;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Capabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Platform {
  private static final String PLATFORM_IOS = "ios";
  private static final String PLATFORM_ANDROID = "android";

  private static final Platform instance = new Platform();
  private final String name;

  public static Platform getInstance() {
    return instance;
  }

  private Platform() {
    name = System.getenv("PLATFORM");
  }

  AppiumDriver getDriver() throws MalformedURLException {
    URL URL = new URL("http://127.0.0.1:4723/wd/hub");
    if (isAndroid()) {
      return new AndroidDriver(URL, getAndroidDesiredCapabilities());
    }
    else if (isIOS()) {
      return new IOSDriver(URL, getIOSDesiredCapabilities());
    }
    else {
      throw new IllegalArgumentException("Cannot detect type of the Driver. Platform value: " + name);
    }
  }

  public boolean isAndroid() {
    return PLATFORM_ANDROID.equals(name);
  }

  public boolean isIOS() {
    return PLATFORM_IOS.equals(name);
  }

  private Capabilities getAndroidDesiredCapabilities() {
    UiAutomator2Options options = new UiAutomator2Options();
    options.setCapability("platformName", "Android");
    options.setCapability("deviceName", "Android Emulator");
    options.setCapability("version", "4.4.2");
    options.setCapability("automationName", "Appium");
    options.setCapability("appPackage", "org.wikipedia");
    options.setCapability("appActivity", ".main.MainActivity");
    options.setCapability("app", resourcePath("apks/org.wikipedia.apk"));
    options.setCapability("newCommandTimeout", 11);
    return options;
  }

  private Capabilities getIOSDesiredCapabilities() {
    XCUITestOptions options = new XCUITestOptions();
    options.setCapability("platformName", "iOS");
    // Hint: run `xcodebuild -showsdks` to see the list of available SDKs
    options.setCapability("deviceName", "iPhone Simulator");
    // Hint: run `xcrun simctl list runtimes` to get available runtimes
    options.setCapability("platformVersion", "15.5");
    options.setCapability("app", resourcePath("apks/Wikipedia.app"));
    options.setCapability("automationName", "XCUITest");
    options.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20);
    options.setCapability("autoAcceptAlerts", true);
    options.setFullReset(false);
    return options;
  }

  private String resourcePath(String file) {
    URL resource = Thread.currentThread().getContextClassLoader().getResource(file);
    assertNotNull(resource, "Resource not found in classpath: " + file);
    return resource.getFile();
  }
}
