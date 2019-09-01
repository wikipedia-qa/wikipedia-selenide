package org.wikiqa.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

abstract public class SearchPageObject extends MainPageObject {
  static String
      SEARCH_INIT_ELEMENT,
      SEARCH_INPUT,
      SEARCH_CANCEL_BUTTON,
      SEARCH_RESULT_BY_SUBSTRING_TPL,
      SEARCH_RESULT_ELEMENT,
      SEARCH_EMPTY_RESULT_ELEMENT,
      SEARCH_RESULTS_EMPTY_MESSAGE,
      SEARCH_TITLE_AND_DESCRIPTION_TPL;

  private static String getResultSearchElement(String substring) {
    return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
  }

  private static String getResultSearchElementWithTitleAndDescription(String title, String description) {
    return SEARCH_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
  }

  public void initSearchInput() {
    $(By.xpath(SEARCH_INIT_ELEMENT)).shouldBe(visible.because("Search init element should be visible and clickable")).click();
    $(By.xpath(SEARCH_INIT_ELEMENT)).should(disappear);
  }

  public void waitForCancelButtonToAppear() {
    $(By.id(SEARCH_CANCEL_BUTTON)).shouldBe(visible);
  }

  public void waitForCancelButtonToDisappear() {
    $(By.id(SEARCH_CANCEL_BUTTON)).shouldNotBe(visible);
  }

  public void clickCancelSearch() {
    $(By.id(SEARCH_CANCEL_BUTTON)).shouldBe(visible).click();
  }

  public void typeSearchLine(String search_line) {
    $(By.xpath(SEARCH_INPUT)).sendKeys(search_line);
  }

  public void waitForSearchResult(String substring) {
    $(By.xpath(getResultSearchElement(substring))).shouldBe(visible);
  }

  public void clickByArticleWithSubstring(String substring) {
    $(By.xpath(getResultSearchElement(substring))).shouldBe(visible).click();
  }

  public ElementsCollection getFoundArticles() {
    return $$(By.xpath(SEARCH_RESULT_ELEMENT));
  }

  /**
   * @deprecated use getFoundArticles()
   */
  public int getAmountOfFoundArticles() {
    return $$(By.xpath(SEARCH_RESULT_ELEMENT)).size();
  }

  public void waitForEmptyResultsLabel() {
    $(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT)).shouldBe(visible);
  }

  public void assertThereIsNoResultOfSearch() {
    $$(By.xpath(SEARCH_RESULT_ELEMENT)).shouldHave(size(0).because("We supposed not to find any results"));
  }

  public void waitForSearchResultsToDisappear() {
    $(By.id(SEARCH_RESULTS_EMPTY_MESSAGE)).shouldBe(visible);
  }

  public void waitForElementByTitleAndDescription(String title, String description) {
    $(By.xpath(getResultSearchElementWithTitleAndDescription(title, description))).shouldBe(visible);
  }
}
