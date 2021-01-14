package org.wikiqa.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.wikiqa.infra.Platform;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.name;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

abstract public class SearchPageObject extends MainPageObject {
  @AndroidFindBy(xpath = "//*[contains(@text, 'Search Wikipedia')]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeSearchField[@name=\"Search Wikipedia\"]")
  private SelenideElement SEARCH_INIT_ELEMENT;

  @AndroidFindBy(xpath = "//*[contains(@text, 'Search…')]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name='Wikipedia']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeSearchField")
  private SelenideElement SEARCH_INPUT;

  @AndroidFindBy(id = "org.wikipedia:id/search_close_btn")
  @iOSXCUITFindBy(id = "Close")
  private SelenideElement SEARCH_CANCEL_BUTTON;

  @AndroidFindBy(xpath = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeLink")
  private ElementsCollection SEARCH_RESULT_ELEMENT;

  @AndroidFindBy(xpath = "//*[@text='No results found']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='No results found']")
  private SelenideElement SEARCH_EMPTY_RESULT_ELEMENT;

  @AndroidFindBy(id = "org.wikipedia:id/search_empty_message")
  @iOSXCUITFindBy(id = "org.wikipedia:id/search_empty_message")
  private SelenideElement SEARCH_RESULTS_EMPTY_MESSAGE;

  public void initSearchInput() {
    SEARCH_INIT_ELEMENT.shouldHave(Platform.getInstance().isIOS() ? name("Search Wikipedia") : text("Search Wikipedia"));

    SEARCH_INIT_ELEMENT.shouldBe(visible.because("Search init element should be visible and clickable")).click();
    if (Platform.getInstance().isIOS()) {
      SEARCH_INIT_ELEMENT.should(disappear);
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

  private String getResultSearchElement(String substring) {
    String pathTemplate = Platform.getInstance().isIOS() ?
        "//XCUIElementTypeLink[contains(@name, '{SUBSTRING}')]" :
        "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
    return pathTemplate.replace("{SUBSTRING}", substring);
  }

  public ElementsCollection getFoundArticles() {
    return SEARCH_RESULT_ELEMENT;
  }

  public void waitForEmptyResultsLabel() {
    $(SEARCH_EMPTY_RESULT_ELEMENT).shouldBe(visible);
  }

  public void assertThereIsNoResultOfSearch() {
    getFoundArticles().shouldHave(size(0).because("We supposed not to find any results"));
  }

  public void waitForSearchResultsToDisappear() {
    $(SEARCH_RESULTS_EMPTY_MESSAGE).shouldBe(visible);
  }

  public void waitForElementByTitleAndDescription(String title, String description) {
    String xpathTemplate = "//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
        "//*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{TITLE}')]/" +
        "../*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION}']" +
        "/..";
    $(By.xpath(xpathTemplate.replace("{TITLE}", title).replace("{DESCRIPTION}", description))).shouldBe(visible);
  }
}
