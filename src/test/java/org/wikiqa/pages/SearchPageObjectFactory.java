package org.wikiqa.pages;

import org.wikiqa.infra.Platform;

import static com.codeborne.selenide.appium.ScreenObject.screen;

public class SearchPageObjectFactory {
  public static SearchPageObject get() {
      return Platform.getInstance().isAndroid() ?
          screen(AndroidSearchPageObject.class) :
          screen(iOSSearchPageObject.class);
  }
}
