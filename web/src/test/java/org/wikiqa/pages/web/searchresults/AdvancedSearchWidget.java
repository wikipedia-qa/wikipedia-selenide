package org.wikiqa.pages.web.searchresults;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AdvancedSearchWidget {
  public final SelenideElement form = $("form#search");
  public final SelenideElement searchField = form.$("#searchText input");
}
