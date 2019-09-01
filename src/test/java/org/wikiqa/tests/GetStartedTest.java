package org.wikiqa.tests;

import org.junit.jupiter.api.Test;
import org.wikiqa.infra.CoreTestCase;
import org.wikiqa.infra.Platform;
import org.wikiqa.pages.WelcomePageObject;

import static org.junit.jupiter.api.Assumptions.assumeFalse;

class GetStartedTest extends CoreTestCase {

  @Test
  void passThroughWelcome() {
    assumeFalse(Platform.getInstance().isAndroid());

    WelcomePageObject page = new WelcomePageObject();

    page.waitForLearnMoreLink();
    page.clickNextButton();

    page.waitForNewWaysToExploreText();
    page.clickNextButton();

    page.waitForAddOrEditPreferredLangText();
    page.clickNextButton();

    page.waitForLearnMoreAboutDataCollectedText();
    page.clickGetStartedButton();
  }
}
