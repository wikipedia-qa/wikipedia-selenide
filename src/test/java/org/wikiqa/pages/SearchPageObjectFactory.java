package org.wikiqa.pages;

import org.wikiqa.infra.Platform;

import static com.codeborne.selenide.Selenide.page;

public class SearchPageObjectFactory {
  public static SearchPageObject get() {
      return Platform.getInstance().isAndroid() ?
          page(AndroidSearchPageObject.class) :
          page(iOSSearchPageObject.class);
  }
}
