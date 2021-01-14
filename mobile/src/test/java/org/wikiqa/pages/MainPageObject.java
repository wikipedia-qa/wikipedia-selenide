package org.wikiqa.pages;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import org.openqa.selenium.Dimension;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.temporal.ChronoUnit.MILLIS;

public class MainPageObject {
  public void swipeUp(int timeOfSwipeMs) {
    TouchAction action = new TouchAction((PerformsTouchActions) getWebDriver());
    Dimension size = getWebDriver().manage().window().getSize();
    int x = size.width / 2;
    int start_y = (int) (size.height * 0.8);
    int end_y = (int) (size.height * 0.2);


    action
        .press(point(x, start_y))
        .waitAction(WaitOptions.waitOptions(Duration.of(timeOfSwipeMs, MILLIS)))
        .moveTo(point(x, end_y))
        .release()
        .perform();
  }

  public void swipeUpQuick() {
    swipeUp(2000);
  }

/*
  public void swipeUpToFindElement(By locator, String error_message, int max_swipes) {
    int already_swiped = 0;
    while (driver.findElements(locator).size() == 0) {
      if (already_swiped > max_swipes) {
        waitForElementPresent(locator, "Cannot find element by swiping up. \n" + error_message, 0);
        return;
      }
      swipeUpQuick();
      ++already_swiped;
    }
  }
*/

/*
  public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes) {
    int already_swiped = 0;
    while (!this.isElementLocatedOnScreen(locator)) {
      if (already_swiped > max_swipes) {
        Assert.assertTrue(error_message, this.isElementLocatedOnScreen(locator));
      }
      swipeUpQuick();
      ++already_swiped;
    }
  }
*/

/*
  public boolean isElementLocatedOnScreen(String locator) {
    int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator", 1)
        .getLocation()
        .getY();
    int screen_size_by_y = driver.manage().window().getSize().getHeight();
    return element_location_by_y < screen_size_by_y;
  }
*/

/*
  public void clickElementToTheRightUpperCorner(String locator, String error_message) {
    WebElement element = this.waitForElementPresent(locator + "/..", error_message);
    int right_x = element.getLocation().getX();
    int upper_y = element.getLocation().getY();
    int lower_y = upper_y + element.getSize().getHeight();
    int middle_y = (upper_y + lower_y) / 2;
    int width = element.getSize().getWidth();

    int point_to_click_x = (right_x + width) - 3;
    int point_to_click_y = middle_y;

    TouchAction action = new TouchAction(driver);
    action.tap(point_to_click_x, point_to_click_y).perform();
  }
*/

/*
  public void swipeElementToLeft(String locator, String error_message) {
    WebElement element = waitForElementPresent(locator, error_message, 10);
    int left_x = element.getLocation().getX();
    int right_x = left_x + element.getSize().getWidth();
    int upper_y = element.getLocation().getY();
    int lower_y = upper_y + element.getSize().getHeight();
    int middle_y = (upper_y + lower_y) / 2;

    TouchAction action = new TouchAction(driver);
    action.press(right_x, middle_y);
    action.waitAction(300);

    if (Platform.getInstance().isAndroid()) {
      action.moveTo(left_x, middle_y);
    }
    else {
      int offset_x = (-1 * element.getSize().getWidth());
      action.moveTo(offset_x, 0);
    }

    action.release();
    action.perform();
  }
*/
}
