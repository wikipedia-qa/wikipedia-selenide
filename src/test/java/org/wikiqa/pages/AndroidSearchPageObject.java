package org.wikiqa.pages;

import org.openqa.selenium.By;

class AndroidSearchPageObject extends SearchPageObject {
  static {
    SEARCH_INIT_ELEMENT = By.xpath("//*[contains(@text, 'Search Wikipedia')]");
    SEARCH_INPUT = By.xpath("//*[contains(@text, 'Search…')]");
    SEARCH_CANCEL_BUTTON = By.id("org.wikipedia:id/search_close_btn");
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
    SEARCH_RESULT_ELEMENT = By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']");
    SEARCH_EMPTY_RESULT_ELEMENT = By.xpath("//*[@text='No results found']");
    SEARCH_RESULTS_EMPTY_MESSAGE = By.id("org.wikipedia:id/search_empty_message");
    SEARCH_TITLE_AND_DESCRIPTION_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{TITLE}')]/../*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION}']/..";
  }
}
