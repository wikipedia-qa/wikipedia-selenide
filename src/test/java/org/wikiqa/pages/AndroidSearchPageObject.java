package org.wikiqa.pages;

class AndroidSearchPageObject extends SearchPageObject {
  static {
    SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]";
    SEARCH_INPUT = "//*[contains(@text, 'Search…')]";
    SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn";
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
    SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
    SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']";
    SEARCH_RESULTS_EMPTY_MESSAGE = "org.wikipedia:id/search_empty_message";
    SEARCH_TITLE_AND_DESCRIPTION_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{TITLE}')]/../*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION}']/..";
  }
}
