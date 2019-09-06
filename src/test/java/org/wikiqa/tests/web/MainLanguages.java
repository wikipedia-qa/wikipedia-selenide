package org.wikiqa.tests.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.wikiqa.pages.web.MainPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class MainLanguages {

  @BeforeAll
  static void openMainPage(){
    open("/");
  }

  @Test
  void shouldPresent10Languages(){
    new MainPage().featuredLanguages.shouldHave(size(10));
  }

  @Test
  void twoTopLanguagesShouldBeENandDE(){
    new MainPage().getTopFeaturedLanguage(1).shouldHave(text("Deutsch"));
    new MainPage().getTopFeaturedLanguage(2).shouldHave(text("English"));
  }

  @Test
  void shouldRevealLanguageGroupedByNumberOfArticles(){
    new MainPage().revealLanguages();
    new MainPage().getLanguagesToHeader("1 000 000+").shouldHave(sizeGreaterThan(10));
    new MainPage().getLanguagesToHeader("100 000+").shouldHave(sizeGreaterThan(10));
    new MainPage().getLanguagesToHeader("10 000+").shouldHave(sizeGreaterThan(10));
    new MainPage().getLanguagesToHeader("1 000+").shouldHave(sizeGreaterThan(10));
    new MainPage().getLanguagesToHeader("100+").shouldHave(sizeGreaterThan(10));
  }


}
