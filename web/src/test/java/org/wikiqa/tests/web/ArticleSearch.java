package org.wikiqa.tests.web;

import com.automation.remarks.junit5.VideoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.wikiqa.pages.web.Article;
import org.wikiqa.pages.web.SearchWidget;
import org.wikiqa.pages.web.searchresults.AdvancedSearchWidget;
import org.wikiqa.pages.web.searchresults.DidYouMeanWidget;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

@Tag("web")
@ExtendWith({WebTestsSetup.class, VideoExtension.class})
public class ArticleSearch {

  @BeforeEach
  void openStartPage() {
    open("/wiki");
  }

  @Test
  void shouldFindArticle() {
    new SearchWidget().search("Selenide");
    new Article().title.shouldHave(exactText("Selenide"));
    new Article().content.shouldHave(text("chemical compound"));
  }

  @Test
  void shouldRedirectForNotCommonlySpelledArticle() {
    new SearchWidget().search("Belorussia");
    new Article().title.shouldHave(exactText("Belarus"));
    new Article().content.shouldHave(text("Republic of Belarus"));
  }

  @Test
  void shouldShowAdvancedSearchIfNoArticleFound() {
    new SearchWidget().search("ljaklasvj2");
    new AdvancedSearchWidget().form.shouldBe(visible);
    new AdvancedSearchWidget().searchField.shouldHave(value("ljaklasvj2"));
  }

  @Test
  void shouldShowAdvancedSearchIfMisspelled() {
    new SearchWidget().search("Selinide");
    new AdvancedSearchWidget().form.shouldBe(visible);
    new AdvancedSearchWidget().searchField.shouldHave(value("Selinide"));
    new DidYouMeanWidget().header.shouldHave(text("Showing results for selenide"))
        .shouldHave(text("No results found for Selinide."));
  }
}
