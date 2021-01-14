package org.wikiqa.pages.web.searchresults;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AdvancedSearchWidget {

  public SelenideElement form = $("form#search"),
          searchField = form.$("#searchText input");
}
