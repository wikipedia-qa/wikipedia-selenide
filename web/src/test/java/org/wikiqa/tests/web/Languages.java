package org.wikiqa.tests.web;

import com.automation.remarks.junit5.VideoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.wikiqa.pages.web.Article;
import org.wikiqa.pages.web.LanguagesWidget;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

@Tag("web")
@ExtendWith({WebTestsSetup.class, VideoExtension.class})
public class Languages {
  @BeforeEach
  void openArticlePage() {
    open("/wiki/Selenide");
  }

  @Test
  void shouldChangeLanguage() {
    new LanguagesWidget().chooseLanguage("Русский");

    Article article = new Article();
    article.title.shouldHave(exactText("Селениды"));
    article.content.shouldHave(text("соединения селена"));
  }
}
