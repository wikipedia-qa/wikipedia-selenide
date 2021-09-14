package org.wikiqa.pages.web.searchresults;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DidYouMeanWidget {
  public final SelenideElement header = $(".searchdidyoumean");
}
