package org.wikiqa.tests;

import org.junit.jupiter.api.Test;
import org.wikiqa.infra.CoreTestCase;
import org.wikiqa.pages.SearchPageObject;
import org.wikiqa.pages.SearchPageObjectFactory;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;

class SearchTests extends CoreTestCase {
  @Test
  void search() {
    SearchPageObject searchPage = SearchPageObjectFactory.get();

    searchPage.initSearchInput();
    searchPage.typeSearchLine("Java");
    searchPage.waitForSearchResult("Object-oriented programming language");
  }

  @Test
  void cancelSearch() {
    SearchPageObject searchPage = SearchPageObjectFactory.get();

    searchPage.initSearchInput();
    searchPage.waitForCancelButtonToAppear();
    searchPage.clickCancelSearch();
    searchPage.waitForCancelButtonToDisappear();
  }

  @Test
  void amountOfNonEmptySearch() {
    SearchPageObject searchPage = SearchPageObjectFactory.get();

    searchPage.initSearchInput();
    searchPage.typeSearchLine("Linkin Park Discography");

    searchPage.getFoundArticles().shouldHave(sizeGreaterThan(0));
  }

  @Test
  void amountOfEmptySearch() {
    SearchPageObject searchPage = SearchPageObjectFactory.get();

    searchPage.initSearchInput();
    searchPage.typeSearchLine("xvhuishvius");
    searchPage.waitForEmptyResultsLabel();
    searchPage.assertThereIsNoResultOfSearch();
  }
}
