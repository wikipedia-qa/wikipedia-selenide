package org.wikiqa.infra;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

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
      return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
    }
    else if (isIOS()) {
      return new IOSDriver(URL, this.getIOSDesiredCapabilities());
    }
    else {
      throw new IllegalArgumentException("Cannot detect type of the Driver. Platform value: " + name);
    }
  }

  public boolean isAndroid() {
    return PLATFORM_ANDROID.equals(name);
  }

  boolean isIOS() {
    return PLATFORM_IOS.equals(name);
  }

  private DesiredCapabilities getAndroidDesiredCapabilities() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("deviceName", "Android Emulator");
    capabilities.setCapability("version", "4.4.2");
    capabilities.setCapability("automationName", "Appium");
    capabilities.setCapability("appPackage", "org.wikipedia");
    capabilities.setCapability("appActivity", ".main.MainActivity");
    capabilities.setCapability("app", resourcePath("apks/org.wikipedia.apk"));
    return capabilities;
  }

  private DesiredCapabilities getIOSDesiredCapabilities() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformName", "iOS");
    capabilities.setCapability("deviceName", "iPhone 6");
    capabilities.setCapability("platformVersion", "12.4");
    capabilities.setCapability("app", resourcePath("apks/Wikipedia.app"));
    return capabilities;
  }

  private String resourcePath(String file) {
    URL resource = Thread.currentThread().getContextClassLoader().getResource(file);
    assertNotNull(resource, "Resource not found in classpath: " + file);
    return resource.getFile();
  }
}
