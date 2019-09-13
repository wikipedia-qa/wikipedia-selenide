package org.wikiqa.tests.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.wikiqa.pages.web.Article;
import org.wikiqa.pages.web.LanguagesWidget;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

@Tag("web")
public class Languages {
  @BeforeEach
  void openArticlePage(){
    open("/wiki/Selenide");
  }

  @Test
  void shouldChangeLanguage(){
     new LanguagesWidget().chooseLanguage("Русский");
     new Article().title.shouldHave(exactText("Селениды"));
     new Article().content.shouldHave(text("соединения селена"));
  }
}
