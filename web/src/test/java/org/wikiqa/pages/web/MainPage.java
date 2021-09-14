package org.wikiqa.pages.web;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

  private final ElementsCollection featuredLanguages = $$(".central-featured-lang");

  private final SelenideElement revealLanguagesBtn = $("#js-lang-list-button");

  public ElementsCollection getTopFeaturedLanguages() {
    return featuredLanguages;
  }

  public void revealLanguages() {
    revealLanguagesBtn.click();
  }

  public ElementsCollection getLanguagesToHeader(String header) {
    return $(".lang-list-container").$(withText(header)).closest("h2").$x("./following-sibling::div").$$("li").filter(visible);
  }
}
