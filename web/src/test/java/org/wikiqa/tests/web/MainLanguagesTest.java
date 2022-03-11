package org.wikiqa.tests.web;

import com.automation.remarks.junit5.VideoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.wikiqa.pages.web.MainPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.textsInAnyOrder;
import static com.codeborne.selenide.Selenide.open;

@Tag("web")
@ExtendWith({WebTestsSetup.class, VideoExtension.class})
public class MainLanguagesTest {
  private final MainPage page = new MainPage();

  @BeforeEach
  void openMainPage() {
    open("/");
  }

  @Test
  void shouldPresent10Languages() {
    page.getTopFeaturedLanguages().shouldHave(size(10));
  }

  @Test
  void twoTopLanguagesShouldBeENandDE() {
    page.getTopFeaturedLanguages()
        .first(5)
        .shouldHave(textsInAnyOrder("English", "日本語", "Deutsch", "Español", "Русский"));
  }

  @Test
  void shouldRevealLanguageGroupedByNumberOfArticles() {
    page.revealLanguages();
    page.getLanguagesToHeader("1 000 000+").shouldHave(sizeGreaterThan(10));
    page.getLanguagesToHeader("100 000+").shouldHave(sizeGreaterThan(10));
    page.getLanguagesToHeader("10 000+").shouldHave(sizeGreaterThan(10));
    page.getLanguagesToHeader("1 000+").shouldHave(sizeGreaterThan(10));
    page.getLanguagesToHeader("100+").shouldHave(sizeGreaterThan(10));
  }
}
