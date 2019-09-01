package org.wikiqa.pages;

class iOSSearchPageObject extends SearchPageObject {
  static {
    SEARCH_INIT_ELEMENT = "//XCUIElementTypeSearchField[@name='Search Wikipedia']";
    SEARCH_INPUT = "//XCUIElementTypeApplication[@name='Wikipedia']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeSearchField";
    SEARCH_CANCEL_BUTTON = "Close";
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//XCUIElementTypeLink[contains(@name, '{SUBSTRING}')]";
    SEARCH_RESULT_ELEMENT = "//XCUIElementTypeLink";
    SEARCH_EMPTY_RESULT_ELEMENT = "//XCUIElementTypeStaticText[@name='No results found']";

    SEARCH_RESULTS_EMPTY_MESSAGE = "org.wikipedia:id/search_empty_message";
    SEARCH_TITLE_AND_DESCRIPTION_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{TITLE}')]/../*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION}']/..";
  }
}
