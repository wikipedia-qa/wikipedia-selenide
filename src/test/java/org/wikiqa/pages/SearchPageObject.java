package org.wikiqa.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.wikiqa.infra.Platform;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

abstract public class SearchPageObject extends MainPageObject {
  static By SEARCH_INIT_ELEMENT;
  static By SEARCH_INPUT;
  static By SEARCH_CANCEL_BUTTON;
  static String SEARCH_RESULT_BY_SUBSTRING_TPL;
  static By SEARCH_RESULT_ELEMENT;
  static By SEARCH_EMPTY_RESULT_ELEMENT;
  static By SEARCH_RESULTS_EMPTY_MESSAGE;
  static String SEARCH_TITLE_AND_DESCRIPTION_TPL;

  private static String getResultSearchElement(String substring) {
    return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
  }

  private static String getResultSearchElementWithTitleAndDescription(String title, String description) {
    return SEARCH_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
  }

  public void initSearchInput() {
    $(SEARCH_INIT_ELEMENT).shouldBe(visible.because("Search init element should be visible and clickable")).click();
    if (Platform.getInstance().isIOS()) {
      $(SEARCH_INIT_ELEMENT).should(disappear);
    }
  }

  public void waitForCancelButtonToAppear() {
    $(SEARCH_CANCEL_BUTTON).shouldBe(visible);
  }

  public void waitForCancelButtonToDisappear() {
    $(SEARCH_CANCEL_BUTTON).shouldNotBe(visible);
  }

  public void clickCancelSearch() {
    $(SEARCH_CANCEL_BUTTON).shouldBe(visible).click();
  }

  public void typeSearchLine(String search_line) {
    $(SEARCH_INPUT).sendKeys(search_line);
  }

  public void waitForSearchResult(String substring) {
    $(By.xpath(getResultSearchElement(substring))).shouldBe(visible);
  }

  public void clickByArticleWithSubstring(String substring) {
    $(By.xpath(getResultSearchElement(substring))).shouldBe(visible).click();
  }

  public ElementsCollection getFoundArticles() {
    return $$(SEARCH_RESULT_ELEMENT);
  }

  public void waitForEmptyResultsLabel() {
    $(SEARCH_EMPTY_RESULT_ELEMENT).shouldBe(visible);
  }

  public void assertThereIsNoResultOfSearch() {
    $$(SEARCH_RESULT_ELEMENT).shouldHave(size(0).because("We supposed not to find any results"));
  }

  public void waitForSearchResultsToDisappear() {
    $(SEARCH_RESULTS_EMPTY_MESSAGE).shouldBe(visible);
  }

  public void waitForElementByTitleAndDescription(String title, String description) {
    $(By.xpath(getResultSearchElementWithTitleAndDescription(title, description))).shouldBe(visible);
  }
}
