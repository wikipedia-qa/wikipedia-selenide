package org.wikiqa.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class WelcomePageObject extends MainPageObject {
  private static final By
      STEP_LEARN_MORE_LINK = By.id("Learn more about Wikipedia"),
      STEP_NEW_WAYS_TO_EXPLORE_TEXT = By.id("New ways to explore"),
      STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = By.id("Add or edit preferred languages"),
      STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = By.id("Learn more about data collected"),
      NEXT_LINK = By.id("Next"),
      GET_STARTED_BUTTON = By.id("Get started"),
      SKIP_BUTTON = By.id("Skip");

  public void waitForLearnMoreLink() {
    $(STEP_LEARN_MORE_LINK).shouldBe(visible);
  }

  public void waitForNewWaysToExploreText() {
    $(STEP_NEW_WAYS_TO_EXPLORE_TEXT).shouldBe(visible);
  }

  public void waitForAddOrEditPreferredLangText() {
    $(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK).shouldBe(visible);
  }

  public void waitForLearnMoreAboutDataCollectedText() {
    $(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK).shouldBe(visible);
  }

  public void clickNextButton() {
    $(NEXT_LINK).shouldBe(visible).click();
  }

  public void clickGetStartedButton() {
    $(GET_STARTED_BUTTON).shouldBe(visible).click();
  }

  public void clickSkip() {
    $(SKIP_BUTTON).shouldBe(visible).click();
  }
}
